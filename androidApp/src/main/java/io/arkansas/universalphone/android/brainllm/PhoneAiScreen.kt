package io.arkansas.universalphone.android.brainllm

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.arkansas.universalphone.brainllm.AiApiRequest
import io.arkansas.universalphone.brainllm.AiApiResponse
import io.arkansas.universalphone.brainllm.PhoneAiApi

@Composable
fun PhoneAiScreen(
    api: PhoneAiApi = remember { PhoneAiApi() }
) {
    var prompt by remember { mutableStateOf("") }
    var response by remember { mutableStateOf<AiApiResponse?>(null) }
    var running by remember { mutableStateOf(false) }
    var network by remember { mutableStateOf(false) }
    var files by remember { mutableStateOf(false) }
    var microphone by remember { mutableStateOf(false) }
    var camera by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Text("Phone Brain AI", style = MaterialTheme.typography.headlineMedium)
        Text("Sandboxed on-device neural runtime", style = MaterialTheme.typography.bodyMedium)

        Card(Modifier.fillMaxWidth()) {
            Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Runtime", style = MaterialTheme.typography.titleLarge)
                val status = response?.status
                Text("State: ${status?.state ?: if (running) "RUNNING" else "STOPPED"}")
                Text("Model: ${status?.modelName ?: "brain-sandbox"}")
                Text("Context limit: ${status?.contextTokens ?: 4096} tokens")
                Text("Node limit: ${status?.nodeCount ?: 100000}")
                Text("Synapse limit: ${status?.synapseCount ?: 500000}")
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Button(onClick = {
                        val result = api.handle(AiApiRequest("start"))
                        response = result
                        running = result.ok
                    }) { Text("Start") }
                    Button(onClick = {
                        response = api.handle(AiApiRequest("stop"))
                        running = false
                    }) { Text("Stop") }
                    Button(onClick = { response = api.handle(AiApiRequest("status")) }) { Text("Refresh") }
                }
            }
        }

        Card(Modifier.fillMaxWidth()) {
            Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("AI Chat / Inference", style = MaterialTheme.typography.titleLarge)
                OutlinedTextField(
                    value = prompt,
                    onValueChange = { prompt = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Prompt") },
                    minLines = 4
                )
                Button(
                    enabled = running && prompt.isNotBlank(),
                    onClick = {
                        response = api.handle(AiApiRequest("chat", prompt, maxTokens = 128))
                    }
                ) { Text("Run inference") }
                response?.result?.let {
                    Divider()
                    Text("Output", style = MaterialTheme.typography.titleMedium)
                    Text(it.output)
                    Text("Confidence: ${"%.3f".format(it.confidence)}")
                    Text("Activated nodes: ${it.activatedNodes.size}")
                }
                response?.message?.let { Text(it) }
            }
        }

        Card(Modifier.fillMaxWidth()) {
            Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Sandbox permissions", style = MaterialTheme.typography.titleLarge)
                PermissionRow("Network", network) {
                    network = it
                    response = api.handle(AiApiRequest("permission", "network=$it"))
                }
                PermissionRow("Files", files) {
                    files = it
                    response = api.handle(AiApiRequest("permission", "files=$it"))
                }
                PermissionRow("Microphone", microphone) {
                    microphone = it
                    response = api.handle(AiApiRequest("permission", "microphone=$it"))
                }
                PermissionRow("Camera", camera) {
                    camera = it
                    response = api.handle(AiApiRequest("permission", "camera=$it"))
                }
                Text("Permissions are additionally constrained by the sandbox policy. OS permissions are not granted automatically.")
            }
        }

        Card(Modifier.fillMaxWidth()) {
            Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Text("Safety", style = MaterialTheme.typography.titleLarge)
                Text("Blocked: raw shell, kernel write, privileged networking, credential store")
                Text("Default: network, files, microphone and camera disabled")
                Text("This runtime is an experimental neural sandbox, not a production LLM.")
            }
        }
    }
}

@Composable
private fun PermissionRow(name: String, enabled: Boolean, onChange: (Boolean) -> Unit) {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(name)
        Switch(checked = enabled, onCheckedChange = onChange)
    }
}
