package tree

typealias BinaryNodeVisitor<T> = (T) -> Unit

class BinaryNode<T>(var value: T) {

    var leftChild: BinaryNode<T>? = null
    var rightChild: BinaryNode<T>? = null

    val min: BinaryNode<T>
        get() = leftChild?.min ?: this



    /**
     *  Travers a binary tree in order
     *
     *  @param visit [BinaryNodeVisitor]
     */
    fun traverseInOrder(visit: BinaryNodeVisitor<T>) {
        leftChild?.traverseInOrder(visit)
        visit(value)
        rightChild?.traverseInOrder(visit)
    }

    /**
     * travers the left side then right side from
     * starting always with the top of the tree
     *
     * @param  visit [BinaryNodeVisitor]
     */
    fun traversePreOrder(visit: BinaryNodeVisitor<T>) {
        visit(value)
        leftChild?.traversePreOrder(visit)
        rightChild?.traversePreOrder(visit)
    }

    /**
     * starts from the left side of the tree from bottom to top
     *
     * @param  visit [BinaryNodeVisitor]
     */
    fun traversePostOrder(visit: BinaryNodeVisitor<T>) {
        leftChild?.traversePostOrder(visit)
        rightChild?.traversePostOrder(visit)
        visit(value)
    }

    override fun toString() = diagram(this)

    private fun diagram(node: BinaryNode<T>?,
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
