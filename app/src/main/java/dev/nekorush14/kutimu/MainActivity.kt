package dev.nekorush14.kutimu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dev.nekorush14.kutimu.ui.KutimuApp
import dev.nekorush14.kutimu.ui.theme.KutimuTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KutimuTheme {
                KutimuApp()
            }
        }
    }
}