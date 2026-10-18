# UniversalPhoneOS Phone Brain AI GUI and API

## GUI

The Android app now exposes a two-tab interface:

- **Home** — OS and secure-boot status.
- **Brain AI** — runtime controls, chat/inference, sandbox permissions, and safety status.

The Brain AI screen is implemented in:

`androidApp/src/main/java/io/arkansas/universalphone/android/brainllm/PhoneAiScreen.kt`

## API facade

The shared API is intentionally transport-neutral so it can be called by Compose, a future local IPC service, or a future HTTP adapter.

### Request

`AiApiRequest(action, input, maxTokens, temperature)`

Supported actions:

- `status`
- `start`
- `stop`
- `chat`
- `infer`
- `permission`

Permission input format:

`network=true`

`files=false`

### Response

`AiApiResponse` contains:

- `ok`
- `action`
- `message`
- `status`
- optional `BrainInferenceResult`

## Security boundary

The GUI cannot grant Android OS permissions by itself. The AI sandbox only changes its internal policy state. Android runtime permissions must still be granted through Android's permission APIs.

The sandbox denies network, file, microphone and camera access by default. It also blocks raw shell, kernel write, privileged networking and credential-store capabilities.

## Runtime flow

```text
Compose GUI
   |
   v
PhoneAiApi
   |
   v
PhoneAiRuntime
   |
   v
PhoneAiSandbox
   |
   v
BrainEngine
   |
   +--> NeuralGraph
   +--> BrainMemory
   +--> BrainTokenizer
```

## Production roadmap

1. Replace `BrainEngine` with a tensor runtime.
2. Add model manifest/checkpoint loading.
3. Add embeddings and transformer attention.
4. Add KV cache and bounded sampling.
5. Add quantized CPU backend.
6. Add Android NNAPI/GPU backend.
7. Add encrypted model storage.
8. Add thermal, battery and memory governors.
9. Add watchdog and crash recovery.
10. Add real Android permission mediation and foreground-service lifecycle where required.
