package io.arkansas.universalphone.brainllm

class BrainModules { val perception = NeuralGraph(); val language = NeuralGraph(); val attention = NeuralGraph(); val reasoning = NeuralGraph(); val memory = NeuralGraph(); val executive = NeuralGraph(); val motor = NeuralGraph(); val safety = NeuralGraph(); fun all() = mapOf("perception" to perception, "language" to language, "attention" to attention, "reasoning" to reasoning, "memory" to memory, "executive" to executive, "motor" to motor, "safety" to safety) }
