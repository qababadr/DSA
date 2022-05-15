package queue

interface Queue<T> {

    /**
     * Size of the Queue
     */
    val count: Int

    /**
     * Checks if the queue is empty
     */
    val isEmpty: Boolean
        get() = count == 0

    /**
     * Inserts an element at the back of the queue
     *
     * @param element [T]
     */
    fun enqueue(element: T)

    /**
     * Removes the element at the front of the queue
     *
     * @return [Boolean]
     */
    fun dequeue(): T?

    /**
     *  Returns the element at the front of the queue
     *
     *  @return [T]
     */
    fun peek(): T?
}