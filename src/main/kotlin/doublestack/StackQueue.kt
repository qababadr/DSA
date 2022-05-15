package doublestack

import queue.Queue
import stack.SimpleStack

class StackQueue<T: Any> : Queue<T> {

    private val leftStack = SimpleStack<T>()
    private val rightStack = SimpleStack<T>()

    override val count: Int
        get() = leftStack.count + rightStack.count

    override val isEmpty: Boolean
        get() = leftStack.isEmpty && rightStack.isEmpty

    override fun dequeue(): T? {
        if (leftStack.isEmpty) {
            transferElements()
        }
        return leftStack.pop()
    }

    override fun peek(): T? {
        if (leftStack.isEmpty) {
            transferElements()
        }
        return leftStack.peek()
    }

    override fun enqueue(element: T) {
        rightStack.push(element)
    }

    override fun toString(): String {
        return "Left stack: \n$leftStack \n Right stack: \n$rightStack"
    }

    /**
     *  after getting the popped element from the right
     *  stack we add it to the left stack until there is no element to pop
     */
    private fun transferElements() {
        var nextElement = rightStack.pop()
        while (nextElement != null) {
            leftStack.push(nextElement)
            nextElement = rightStack.pop()
        }
    }

}