package com.example.saferecycle.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.composables.icons.lucide.Eye
import com.composables.icons.lucide.EyeOff
import com.composables.icons.lucide.Lucide
import com.example.saferecycle.ui.theme.SafeRecycleTheme
import com.example.saferecycle.ui.theme.fontFamily

@Composable
fun NormalTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: ((String) -> Unit)? = null,
    placeholder: String,
    isPassword: Boolean,
    leadingIcon: ImageVector,
    leadingIconContentDescription: String,
//    supportingText:  @Composable (() -> Unit)? = null,
) {
    var isVisible by remember { mutableStateOf(!isPassword) }
    val shape = RoundedCornerShape(13.dp)
    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value = value,
        placeholder = {
            NormalText(
                text = placeholder,
                color = SafeRecycleTheme.colors.textSecondary,
                fontSize = 14.sp
            )
        },
        onValueChange = {
            if (onValueChange != null) {
                onValueChange(it)
            }
        },
        enabled = onValueChange != null,
        singleLine = true,
        leadingIcon = {
            Icon(
                imageVector = leadingIcon,
                contentDescription = leadingIconContentDescription,
                tint = SafeRecycleTheme.colors.textSecondary
            )
        },
        trailingIcon = {
            if (isPassword)
                IconButton(onClick = { isVisible = !isVisible }) {
                    Icon(
                        if (isVisible)
                            Lucide.Eye
                        else Lucide.EyeOff,
                        contentDescription = "Visibility Icon",
                        tint = SafeRecycleTheme.colors.textSecondary
                    )
                }
        },
        visualTransformation = if (isVisible) VisualTransformation.None else PasswordVisualTransformation(),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,   // background saat fokus
            unfocusedContainerColor = Color.White, // background saat tidak fokus
            disabledContainerColor = Color.White,  // background saat disabled
            focusedIndicatorColor = SafeRecycleTheme.colors.stroke,
            unfocusedIndicatorColor = SafeRecycleTheme.colors.stroke,
            disabledIndicatorColor = SafeRecycleTheme.colors.stroke,
            errorContainerColor = SafeRecycleTheme.colors.elementBackground ,
            cursorColor = Color.Black,
        ),
        shape = shape,
        textStyle = TextStyle(
            fontFamily = fontFamily,
            fontSize = 14.sp,
            color = SafeRecycleTheme.colors.foreground
        ),
//        supportingText = supportingText
    )
}
