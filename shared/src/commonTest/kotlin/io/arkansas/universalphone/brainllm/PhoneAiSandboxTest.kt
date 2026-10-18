package io.arkansas.universalphone.brainllm

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class PhoneAiSandboxTest {
    @Test
    fun sandboxStartsAndStops() {
        val sandbox = PhoneAiSandbox()
        assertFalse(sandbox.isRunning())
        assertTrue(sandbox.start().isSuccess)
        assertTrue(sandbox.isRunning())
        sandbox.stop()
        assertFalse(sandbox.isRunning())
    }

    @Test
    fun privilegedCapabilitiesRemainBlocked() {
        val sandbox = PhoneAiSandbox()
        assertTrue(sandbox.start().isSuccess)
        assertTrue(sandbox.setPermission("network", true).isFailure)
        assertTrue(sandbox.setPermission("files", true).isFailure)
        assertTrue(sandbox.setPermission("raw_shell", true).isFailure)
    }

    @Test
    fun inferenceRequiresRunningSandbox() {
        val sandbox = PhoneAiSandbox()
        val request = BrainInferenceRequest("hello", maxTokens = 8)
        assertTrue(sandbox.infer(request).isFailure)
        assertTrue(sandbox.start().isSuccess)
        assertTrue(sandbox.infer(request).isSuccess)
    }
}
