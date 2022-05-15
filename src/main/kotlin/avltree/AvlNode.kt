package avltree

class AvlNode<T>(var value: T) {
    var leftChild: AvlNode<T>? = null
    var rightChild: AvlNode<T>? = null
    var height = 0

    val leftHeight: Int
        get() = leftChild?.height ?: -1

    val rightHeight: Int
        get() = rightChild?.height ?: -1

    /**
     * Balance factor of a node in an AVL tree is the difference between the height
     * of the left subtree and that of the right subtree of that node.
     */
    val balanceFactor: Int
        get() = leftHeight - rightHeight

    override fun toString() = diagram(this)

    private fun diagram(node: AvlNode<T>?,
                        top: String = "",
                        root: String = "",
                        bottom: String = ""): String {
        return node?.let {
            if (node.leftChild == null && node.rightChild == null) {
                "$root${node.value}\n"
            } else {
                diagram(node.rightChild, "$top ", "$top|-", "$top| ") +
                        root + "${node.value}\n" + diagram(node.leftChild, "$bottom| ", "$bottom|_", "$bottom ")
            }
        } ?: "${root}null\n"
    }
}