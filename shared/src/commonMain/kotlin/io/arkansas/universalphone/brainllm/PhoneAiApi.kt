package io.arkansas.universalphone.brainllm

class PhoneAiApi(private val runtime: PhoneAiRuntime = PhoneAiRuntime()) {
    fun handle(request: AiApiRequest): AiApiResponse = when (request.action.lowercase()) {
        "status" -> AiApiResponse(ok = true, action = request.action, status = runtime.status())
        "start" -> runtime.start().fold(
            onSuccess = { AiApiResponse(true, request.action, "AI sandbox started", runtime.status()) },
            onFailure = { AiApiResponse(false, request.action, it.message ?: "Unable to start", runtime.status()) }
        )
        "stop" -> {
            runtime.stop()
            AiApiResponse(true, request.action, "AI sandbox stopped", runtime.status())
        }
        "infer", "chat" -> runtime.infer(
            BrainInferenceRequest(request.input, request.maxTokens, request.temperature)
        ).fold(
            onSuccess = { AiApiResponse(true, request.action, "Inference complete", runtime.status(), it) },
            onFailure = { AiApiResponse(false, request.action, it.message ?: "Inference failed", runtime.status()) }
        )
        "permission" -> {
            val granted = request.input.substringAfter('=', "false").trim().toBooleanStrictOrNull()
                ?: return AiApiResponse(false, request.action, "Permission value must be true or false", runtime.status())
            val name = request.input.substringBefore('=', request.input).trim()
            runtime.setPermission(name, granted).fold(
                onSuccess = { AiApiResponse(true, request.action, "Permission updated", runtime.status()) },
                onFailure = { AiApiResponse(false, request.action, it.message ?: "Permission update failed", runtime.status()) }
            )
        }
        else -> AiApiResponse(false, request.action, "Unknown AI API action", runtime.status())
    }
}
