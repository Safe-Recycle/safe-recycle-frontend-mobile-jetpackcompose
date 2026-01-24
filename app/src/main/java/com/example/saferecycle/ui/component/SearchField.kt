package com.example.saferecycle.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.Search
import com.example.saferecycle.ui.theme.SafeRecycleTheme
import com.example.saferecycle.ui.theme.fontFamily

@Composable
fun SearchField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: ((String) -> Unit)? = null,
) {
    val shape =  RoundedCornerShape(13.dp)
    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value = value,
        placeholder = {
            NormalText(
                modifier = Modifier.padding(0.dp),
                "Search waste",
                color = SafeRecycleTheme.colors.textSecondary,
                fontSize = 12.sp
            )
        },
        onValueChange = {
            if (onValueChange != null) {
                onValueChange(it)
            }
        },
        enabled = onValueChange != null,
        singleLine = true,
        trailingIcon = {
            Icon(
                modifier = Modifier.size(18.dp),
                imageVector = Lucide.Search,
                contentDescription = "Search Icon",
                tint = SafeRecycleTheme.colors.textSecondary
            )
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,   // background saat fokus
            unfocusedContainerColor = Color.White, // background saat tidak fokus
            disabledContainerColor = Color.White,  // background saat disabled
            focusedIndicatorColor = SafeRecycleTheme.colors.stroke,
            unfocusedIndicatorColor = SafeRecycleTheme.colors.stroke,
            disabledIndicatorColor = SafeRecycleTheme.colors.stroke,
            cursorColor = Color.Black,
        ),
        shape = shape,
        textStyle = TextStyle(fontFamily = fontFamily, fontSize = 12.sp)
    )
}
