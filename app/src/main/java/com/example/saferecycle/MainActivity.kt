package com.example.saferecycle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.composables.icons.lucide.Activity
import com.example.saferecycle.ui.theme.SafeRecycleTheme
import com.example.saferecycle.ui.theme.fontFamily
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.ScanSearch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SafeRecycleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        Text(
                            text = "Whereas recognition of the inherent dignity",
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.Normal
                        )
                        Text(
                            text = "Whereas recognition of the inherent dignity",
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.Medium
                        )
                        Text(
                            text = "Whereas recognition of the inherent dignity",
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.Bold
                        )
                        Icon(imageVector = Lucide.ScanSearch, contentDescription = "lucide icon")
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        fontFamily = fontFamily,
//        fontWeight = FontWeight.Bold,
        fontSize = 50.sp,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SafeRecycleTheme {
        Greeting("Android")
    }
}