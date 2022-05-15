package binarysearchtree

import tree.BinaryNode

class BinarySearchTree<T : Comparable<T>> {

    var root: BinaryNode<T>? = null

    override fun toString() = root?.toString() ?: "empty tree"


    /**
     *  insert a value
     *
     *  @param value [T]
     */
    fun insert(value: T) {
        root = insert(root, value)
    }

    /**
     * Finding an element by traverse through its nodes
     *
     * @param value [T]
     * @return [Boolean]
     */
    fun contains(value: T): Boolean {
        root ?: return false

        var found = false
        root?.traverseInOrder {
            if (value == it) {
                found = true
            }
        }

        return found
    }

    /**
     * Delete a value from the tree
     *
     * @param value [T]
     */
    fun remove(value: T) {
        root = remove(root, value)
    }

    private fun remove(
        node: BinaryNode<T>?,
        value: T
    ): BinaryNode<T>? {
        node ?: return null

        when {
            value == node.value -> {
                if (node.leftChild == null && node.rightChild == null) {
                    return null
                }
                if (node.leftChild == null) {
                    return node.rightChild
                }
                if (node.rightChild == null) {
                    return node.leftChild
                }
                node.rightChild?.min?.value?.let {
                    node.value = it
                }

                node.rightChild = remove(node.rightChild, node.value)

            }
            value < node.value -> node.leftChild = remove(node.leftChild, value)
            else -> node.rightChild = remove(node.rightChild, value)
        }
        return node
    }


    //    Rules of the BST:
//    - Nodes of the left child must contain values less than the current node,
//    - Nodes of the right child must contain values greater than or equal
//    to the current node.
    private fun insert(
        node: BinaryNode<T>?,
        value: T
    ): BinaryNode<T> {
        node ?: return BinaryNode(value)
        if (value < node.value) {
            node.leftChild = insert(node.leftChild, value)
        } else {
            node.rightChild = insert(node.rightChild, value)
        }
        return node
    }
}