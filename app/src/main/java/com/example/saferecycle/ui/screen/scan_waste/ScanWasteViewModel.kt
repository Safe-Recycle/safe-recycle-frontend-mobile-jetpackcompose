package com.example.saferecycle.ui.screen.scan_waste

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.saferecycle.data.model.ScanWasteResponse
import com.example.saferecycle.data.network.DataResult
import com.example.saferecycle.data.repository.ScanRepository
import com.example.saferecycle.ui.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import javax.inject.Inject
import android.Manifest.permission

@HiltViewModel
class ScanWasteViewModel @Inject constructor(
    private val repository: ScanRepository
) : ViewModel() {
    private val _scanWasteState =
        MutableStateFlow<UiState<ScanWasteResponse>>(UiState.Idle)
    val scanWasteState = _scanWasteState



    fun scanWaste(file: MultipartBody.Part) {
        _scanWasteState.value = UiState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.scanWaste(file)) {
                is DataResult.Success -> {
                    _scanWasteState.value = UiState.Success(result.data)
                }

                is DataResult.Error -> {
                    _scanWasteState.value = UiState.Error(result.error)
                }

                is DataResult.Empty -> {
                    _scanWasteState.value = UiState.Empty
                }
            }
        }
    }

    fun clearState(){ _scanWasteState.value = UiState.Idle }
}