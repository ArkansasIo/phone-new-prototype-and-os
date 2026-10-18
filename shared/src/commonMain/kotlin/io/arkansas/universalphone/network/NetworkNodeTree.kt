package io.arkansas.universalphone.network

class NetworkNodeTree(private val nodes: MutableMap<String, NetworkNode> = linkedMapOf()) {
    fun add(node: NetworkNode): Boolean {
        if (nodes.containsKey(node.id)) return false
        if (node.parentId != null && !nodes.containsKey(node.parentId)) return false
        nodes[node.id] = node
        return true
    }

    fun upsert(node: NetworkNode) { nodes[node.id] = node }
    fun remove(id: String): NetworkNode? = nodes.remove(id)
    fun get(id: String): NetworkNode? = nodes[id]
    fun roots(): List<NetworkNode> = nodes.values.filter { it.parentId == null }
    fun childrenOf(id: String): List<NetworkNode> = nodes.values.filter { it.parentId == id }
    fun all(): List<NetworkNode> = nodes.values.toList()
    fun clear() = nodes.clear()

    fun descendantsOf(id: String): List<NetworkNode> {
        val result = mutableListOf<NetworkNode>()
        fun visit(parent: String) {
            childrenOf(parent).forEach { child ->
                result += child
                visit(child.id)
            }
        }
        visit(id)
        return result
    }
}
