package io.arkansas.universalphone.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import io.arkansas.universalphone.PhoneConfig

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { UniversalPhoneScreen(PhoneConfig()) }
    }
}

@Composable
private fun UniversalPhoneScreen(config: PhoneConfig) {
    MaterialTheme {
        Text("${config.osName} ${config.osVersion} — ${config.modelName}")
    }
}
