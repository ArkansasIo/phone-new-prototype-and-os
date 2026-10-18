package io.arkansas.universalphone.android.network

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.arkansas.universalphone.network.NetworkHudState

@Composable
fun NetworkHud(state: NetworkHudState, modifier: Modifier = Modifier) {
    Card(modifier = modifier.fillMaxWidth().padding(12.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("NETWORK")
            Text(if (state.connected) "Connected" else "Offline")
            Text("Nodes: ${state.onlineNodeCount}/${state.nodeCount}")
            Text("Down: ${"%.1f".format(state.downloadMbps)} Mbps")
            Text("Up: ${"%.1f".format(state.uploadMbps)} Mbps")
            Text("Latency: ${state.latencyMs?.let { "$it ms" } ?: "—"}")
            Text("VPN: ${if (state.vpnActive) "Active" else "Inactive"}")
        }
    }
}
