package io.arkansas.universalphone.virtualnet

class VirtualNetworkGraph {
    private val links = linkedMapOf<String, MutableSet<String>>()

    fun link(a: String, b: String) {
        links.getOrPut(a) { linkedSetOf() }.add(b)
        links.getOrPut(b) { linkedSetOf() }.add(a)
    }

    fun unlink(a: String, b: String) {
        links[a]?.remove(b)
        links[b]?.remove(a)
    }

    fun neighbors(id: String): Set<String> = links[id]?.toSet() ?: emptySet()
    fun nodes(): Set<String> = links.keys.toSet()

    fun reachable(from: String, to: String): Boolean {
        if (from == to) return true
        val visited = mutableSetOf<String>()
        val queue = ArrayDeque<String>()
        queue.addLast(from)
        while (queue.isNotEmpty()) {
            val current = queue.removeFirst()
            if (!visited.add(current)) continue
            for (next in neighbors(current)) {
                if (next == to) return true
                queue.addLast(next)
            }
        }
        return false
    }
}
