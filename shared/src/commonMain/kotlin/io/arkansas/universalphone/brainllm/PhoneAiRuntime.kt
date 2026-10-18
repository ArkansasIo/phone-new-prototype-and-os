package io.arkansas.universalphone.brainllm

import kotlinx.serialization.Serializable

@Serializable
data class AiRuntimeStatus(
    val state: String = "STOPPED",
    val modelName: String = "brain-sandbox",
    val contextTokens: Int = 0,
    val nodeCount: Int = 0,
    val synapseCount: Int = 0
)

class PhoneAiRuntime(
    private val sandbox: PhoneAiSandbox = PhoneAiSandbox()
) {
    fun start(): Result<Unit> = sandbox.start()
    fun stop() = sandbox.stop()
    fun status(): AiRuntimeStatus = AiRuntimeStatus(
        state = if (sandbox.isRunning()) "RUNNING" else "STOPPED",
        modelName = "brain-sandbox",
        contextTokens = sandbox.limits.maxContextTokens,
        nodeCount = sandbox.limits.maxNeuralNodes,
        synapseCount = sandbox.limits.maxSynapses
    )
    fun infer(request: BrainInferenceRequest): Result<BrainInferenceResult> = sandbox.infer(request)
    fun setPermission(name: String, granted: Boolean): Result<Unit> = sandbox.setPermission(name, granted)
}
