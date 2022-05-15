package tree

import queue.SimpleQueue

typealias Visitor<T> = (TreeNode<T>) -> Unit

class TreeNode<T>(val value: T) {

    private val children: MutableList<TreeNode<T>> = mutableListOf()

    /**
     *  Add child to the tree
     *
     *  @param child [TreeNode]
     *  @return [Boolean]
     */
    fun add(child: TreeNode<T>): Boolean{
        return children.add(child)
    }

    /**
     * Depth-first traversal starts at the root node and explores
     * the tree as far as possible along each branch before reaching a leaf and then backtracking.
     *
     * @param visit [Visitor]
     */
    fun forEachDepthFirst(visit: Visitor<T>) {
        visit(this)
        children.forEach {
            it.forEachDepthFirst(visit)
        }
    }

    /**
     * visits each node of the tree based on the depth of the nodes.
     * Starting at the root, every node on a level is visited before going to a lower level.
     *
     * @param visit [Visitor]
     */
    fun forEachLevelOrder(visit: Visitor<T>) {
        visit(this)
        val queue = SimpleQueue<TreeNode<T>>()
        children.forEach { queue.enqueue(it) }

        var node = queue.dequeue()
        while (node != null) {
            visit(node)
            node.children.forEach { queue.enqueue(it) }
            node = queue.dequeue()
        }
    }

    /**
     * Search a value in a tree
     *
     * @param value [T]
     * @return [TreeNode]
     */
    fun search(value: T): TreeNode<T>? {
        var result: TreeNode<T>? = null

        forEachLevelOrder {
            if (it.value == value) {
                result = it
            }
        }

        return result
    }
}
