package io.arkansas.universalphone.brainllm

import kotlinx.serialization.Serializable

@Serializable
data class AiApiRequest(
    val action: String,
    val input: String = "",
    val maxTokens: Int = 64,
    val temperature: Double = 0.7
)

@Serializable
data class AiApiResponse(
    val ok: Boolean,
    val action: String,
    val message: String = "",
    val status: AiRuntimeStatus? = null,
    val result: BrainInferenceResult? = null
)

@Serializable
data class AiPermissionState(
    val name: String,
    val granted: Boolean,
    val userControllable: Boolean
)

@Serializable
data class AiSandboxSettings(
    val modelName: String = "brain-sandbox",
    val maxMemoryBytes: Long = 256L * 1024L * 1024L,
    val maxContextTokens: Int = 4096,
    val maxOutputTokens: Int = 512,
    val maxInferenceMillis: Long = 10_000,
    val networkAccess: Boolean = false,
    val fileAccess: Boolean = false,
    val microphoneAccess: Boolean = false,
    val cameraAccess: Boolean = false
)
