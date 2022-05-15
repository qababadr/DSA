package stack

interface Stack<Element> {

    /**
     * Size of the stack
     */
    val count: Int

    /**
     * Checks if the stack is empty
     */
    val isEmpty: Boolean
        get() = count == 0

    /**
     *  Add a given element to the stack (at the top of the list)
     *
     *  @param element [Element]
     */
    fun push(element: Element)

    /**
     *  Removes the last element of the stack
     *
     *  @return [Element]
     */
    fun pop(): Element?


    /**
     *  Get top element of the stack
     *
     *  @return [Element]
     */
    fun peek(): Element?
}