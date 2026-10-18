package io.arkansas.universalphone.brainllm

class NeuralLearning { fun stdp(s: NeuralSynapse, pre: Long, post: Long): NeuralSynapse { val d = post - pre; val c = when { d > 0 -> s.plasticity * kotlin.math.exp(-d / 20.0); d < 0 -> -s.plasticity * kotlin.math.exp(d / 20.0); else -> 0.0 }; return s.copy(weight = (s.weight + c).coerceIn(-1.0, 1.0)) }; fun hebbian(w: Double, pre: Double, post: Double, rate: Double = 0.01) = (w + rate * pre * post).coerceIn(-1.0, 1.0) }
