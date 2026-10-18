package io.arkansas.universalphone.brainllm

import kotlinx.serialization.Serializable

@Serializable
data class NeuralNode(val id: String, val layer: Int, val activation: Double = 0.0, val threshold: Double = 1.0, val bias: Double = 0.0, val tags: List<String> = emptyList())
@Serializable
data class NeuralSynapse(val id: String, val sourceId: String, val targetId: String, val weight: Double = 0.0, val plasticity: Double = 0.01, val enabled: Boolean = true)
@Serializable
data class NeuralSignal(val sourceId: String, val targetId: String, val value: Double, val timestampEpochMs: Long, val ttl: Int = 8)
@Serializable
data class BrainToken(val id: Int, val text: String, val vector: List<Double> = emptyList())
@Serializable
data class BrainInferenceRequest(val input: String, val maxTokens: Int = 64, val temperature: Double = 0.7)
@Serializable
data class BrainInferenceResult(val output: String, val tokens: List<BrainToken>, val confidence: Double, val activatedNodes: List<String>)
