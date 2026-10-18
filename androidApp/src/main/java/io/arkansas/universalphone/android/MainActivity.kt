package io.arkansas.universalphone.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.arkansas.universalphone.UniversalPhoneRuntime

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { UniversalPhoneHome() }
    }
}

@Composable
private fun UniversalPhoneHome() {
    val runtime = remember { UniversalPhoneRuntime() }
    var boot by remember { mutableStateOf(runtime.boot()) }
    val settings = runtime.settings.read()

    MaterialTheme {
        Column(
            modifier = Modifier.fillMaxSize().padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("${boot.osName}", style = MaterialTheme.typography.headlineMedium)
            Text("Version ${boot.version}")
            Text("Secure Boot: ${if (boot.secureBoot) "enabled" else "disabled"}")
            Text("Performance: ${settings.performanceMode}")
            Text("Network: ${settings.networkMode}")
            Button(onClick = {
                runtime.settings.update { it.copy(developerMode = !it.developerMode) }
                boot = runtime.boot()
            }) {
                Text(if (boot.developerMode) "Disable Developer Mode" else "Enable Developer Mode")
            }
        }
    }
}
