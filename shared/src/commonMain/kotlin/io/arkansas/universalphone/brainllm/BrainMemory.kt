package io.arkansas.universalphone.brainllm

class BrainMemory(private val capacity: Int = 1024) { private val entries = ArrayDeque<String>(); init { require(capacity > 0) }; fun remember(text: String) { if (entries.size == capacity) entries.removeFirst(); entries.addLast(text) }; fun recent(limit: Int = 16) = entries.takeLast(limit.coerceAtLeast(0)); fun clear() = entries.clear(); val size get() = entries.size }
