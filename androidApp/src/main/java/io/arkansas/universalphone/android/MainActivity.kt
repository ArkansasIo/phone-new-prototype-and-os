package io.arkansas.universalphone.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import io.arkansas.universalphone.UniversalPhoneRuntime
import io.arkansas.universalphone.android.brainllm.PhoneAiScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { UniversalPhoneApp() }
    }
}

@Composable
private fun UniversalPhoneApp() {
    var selectedTab by remember { mutableIntStateOf(0) }
    val runtime = remember { UniversalPhoneRuntime() }
    val boot = remember { runtime.boot() }

    MaterialTheme {
        Surface(Modifier.fillMaxSize()) {
            val tabs = listOf("Home", "Brain AI")
            androidx.compose.foundation.layout.Column(Modifier.fillMaxSize()) {
                TabRow(selectedTabIndex = selectedTab) {
                    tabs.forEachIndexed { index, title ->
                        Tab(
                            selected = selectedTab == index,
                            onClick = { selectedTab = index },
                            text = { Text(title) }
                        )
                    }
                }
                when (selectedTab) {
                    0 -> UniversalPhoneHome(boot.osName, boot.version, boot.secureBoot, boot.developerMode)
                    1 -> PhoneAiScreen()
                }
            }
        }
    }
}

@Composable
private fun UniversalPhoneHome(
    osName: String,
    version: String,
    secureBoot: Boolean,
    developerMode: Boolean
) {
    androidx.compose.foundation.layout.Column(
        Modifier.fillMaxSize().androidx.compose.foundation.layout.padding(androidx.compose.ui.unit.dp(24))
    ) {
        Text(osName, style = MaterialTheme.typography.headlineMedium)
        Text("Version $version")
        Text("Secure Boot: ${if (secureBoot) "enabled" else "disabled"}")
        Text("Developer Mode: ${if (developerMode) "enabled" else "disabled"}")
    }
}
