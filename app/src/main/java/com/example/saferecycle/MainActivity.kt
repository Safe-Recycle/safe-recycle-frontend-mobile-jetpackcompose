package com.example.saferecycle

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.saferecycle.ui.theme.SafeRecycleTheme
import com.example.saferecycle.navigation.AppNavigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        if (!hasRequiredPermission()) {
//            ActivityCompat.requestPermissions(
//                this, CAMERAX_PERMISSION, 0
//            )
//        }
        setContent {

            SafeRecycleTheme {
                AppNavigation()
            }
        }
    }

//    private fun hasRequiredPermission(): Boolean {
//        return CAMERAX_PERMISSION.all {
//            ContextCompat.checkSelfPermission(
//                applicationContext,
//                it
//            ) == PackageManager.PERMISSION_GRANTED
//        }
//
//    }
//
//    companion object {
//        private val CAMERAX_PERMISSION = arrayOf(
//            Manifest.permission.CAMERA,
//        )
//    }
}