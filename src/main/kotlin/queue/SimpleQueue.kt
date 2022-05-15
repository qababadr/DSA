package queue

class SimpleQueue<T: Any>: Queue<T> {

    private val storage = arrayListOf<T>()

    override val count: Int
        get() = storage.size

    override fun dequeue(): T? {
        return if(isEmpty) null
        else{
            return storage.removeAt(0)
        }
    }

    override fun peek(): T? {
        return storage.getOrNull(index = 0)
    }

    override fun enqueue(element: T){
       storage.add(element)
    }

    override fun toString(): String{
       return storage.toString()
    }
}