package avltree

import java.lang.Integer.max

class AVLTree<T : Comparable<T>> {

    var root: AvlNode<T>? = null

    override fun toString() = root?.toString() ?: "empty tree"

    fun insert(value: T) {
        root = insert(root, value)
    }


    private fun rightRotate(node: AvlNode<T>): AvlNode<T> {
        val pivot = node.leftChild!!
        node.leftChild = pivot.rightChild
        pivot.rightChild = node
        node.height = max(node.leftHeight, node.rightHeight) + 1
        pivot.height = max(pivot.leftHeight, pivot.rightHeight) + 1
        return pivot
    }

    private fun leftRotate(node: AvlNode<T>): AvlNode<T> {
        val pivot = node.rightChild!!
        node.rightChild = pivot.leftChild
        pivot.leftChild = node
        node.height = max(node.leftHeight, node.rightHeight) + 1
        pivot.height = max(pivot.leftHeight, pivot.rightHeight) + 1
        return pivot
    }

    private fun rightLeftRotate(node: AvlNode<T>): AvlNode<T> {
        val rightChild = node.rightChild ?: return node
        node.rightChild = rightRotate(rightChild)
        return leftRotate(node)
    }

    private fun leftRightRotate(node: AvlNode<T>): AvlNode<T> {
        val leftChild = node.leftChild ?: return node
        node.leftChild = rightRotate(leftChild)
        return rightRotate(node)
    }

    /**
     * Decides whether a node requires balance or not
     */
    private fun balanced(node: AvlNode<T>): AvlNode<T> {
        return when (node.balanceFactor) {
            2 -> {
                if (node.leftChild?.balanceFactor == -1) {
                    leftRightRotate(node)
                } else {
                    rightRotate(node)
                }
            }
            -2 -> {
                if (node.rightChild?.balanceFactor == 1) {
                    rightLeftRotate(node)
                } else {
                    leftRotate(node)
                }
            }
            else -> node
        }
    }

    private fun insert(node: AvlNode<T>?, value: T): AvlNode<T> {
        node ?: return AvlNode(value)
        if (value < node.value) {
            node.leftChild = insert(node.leftChild, value)
        } else {
            node.rightChild = insert(node.rightChild, value)
        }
        val balancedNode = balanced(node)
        balancedNode.height = max(balancedNode.leftHeight, balancedNode.rightHeight) + 1
        return balancedNode
    }
}