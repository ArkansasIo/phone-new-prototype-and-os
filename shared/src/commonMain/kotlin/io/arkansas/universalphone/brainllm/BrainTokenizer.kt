package io.arkansas.universalphone.brainllm

class BrainTokenizer { fun encode(text: String) = text.trim().split(Regex("\\s+")).filter { it.isNotEmpty() }.mapIndexed { i, w -> BrainToken(i, w, w.take(16).map { it.code / 128.0 }) }; fun decode(tokens: List<BrainToken>) = tokens.joinToString(" ") { it.text } }
