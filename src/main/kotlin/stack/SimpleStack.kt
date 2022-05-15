package stack

class SimpleStack<T: Any>: Stack<T> {

    private val storage = arrayListOf<T>()

    override fun push(element: T) {
        storage.add(element = element)
    }

    override val count: Int
        get() = storage.size

    override fun pop(): T? {
        if(isEmpty){
            return null
        }
        return storage.removeAt(count - 1)
    }

    override fun peek(): T? {
        return storage.lastOrNull()
    }

    override fun toString(): String {
        return buildString {
            appendLine("----Top of the stack----")
            storage.asReversed().forEach {
                appendLine("$it")
            }
            appendLine("----Bottom of the stack -------")
        }
    }
}