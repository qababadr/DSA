package linkedlist

open class LinkedList<T> {

    private var head: Node<T>? = null
    private var tail: Node<T>? = null
    var size: Int = 0
        private set

    /**
     * Checks if the linked list is empty
     *
     * @return [Boolean]
     */
    fun isEmpty(): Boolean {
        return size == 0
    }


    /**
     * insert an element at the head of the linked list
     *
     * @param value [T]
     * @return [LinkedList]
     */
    fun insertFirst(value: T): LinkedList<T> {
        head = Node(value = value, next = head)
        if(tail == null){
            tail = head
        }
        size++
        return this
    }


    /**
     * insert an element at the end of the linked list
     *
     * @param value [T]
     */
    fun insertLast(value: T) {
        if (isEmpty()) {
            insertFirst(value = value)
            return
        }
        tail?.next = Node(value = value)
        tail = tail?.next
        size++
    }

    /**
     * finds an element at given index
     *
     * @param index [Int]
     * @return [Node]
     */
    fun elementAt(index: Int): Node<T>?{
        var currentNode = head
        var currentIndex = 0

        while(currentNode != null  && currentIndex < index){
            currentNode = currentNode.next
            currentIndex++
        }
        return currentNode
    }

    /**
     * insert an element after a given node
     *
     * @param value [T]
     * @param nextNode [Node]
     *
     * @return [Node]
     */
    fun insert(value: T, nextNode: Node<T>): Node<T> {
        if (tail == nextNode) {
            insertLast(value = value)
            return tail!!
        }
        val newNode = Node(value = value, next = nextNode.next)

        nextNode.next = newNode
        size++
        return newNode
    }

    /**
     *  Removes the value at the head of the linked list
     */
    fun removeFirst(){
        if(!isEmpty()) size--
        head = head?.next

        if(isEmpty())
            tail = null
    }

    /**
     *  Removes the value at the end of the linked list
     */
    fun removeLast(){
        if(head == null) return

        if(head?.next == null){
            removeFirst()
            return
        }

        size--

        var previousNode = head
        var currentNode = head

        var nextNode = currentNode?.next

        while(nextNode != null){
            previousNode = currentNode
            currentNode = nextNode
            nextNode = currentNode.next
        }

        previousNode?.next = null
        tail = previousNode
    }

    /**
     * Removes an element after a given node
     *
     * @param node [Node]
     */
    fun removeAfter(node: Node<T>){
        if(node.next == tail)
            tail = node

        if(node.next != null)
            size --

        node.next = node.next?.next
    }

    /**
     * prints the list in reverse
     */
    fun printInReverse(){
        elementAt(index = 0)?.printInReverse()
        println()
    }

    /**
     * get the element at the middle of the list
     *
     * @return [Node]
     */
    fun elementAtMiddle(): Node<T>?{
        //the slow var will be in the middle
        var slow = elementAt(index = 0)
        //the fast var will traverse the list until its end
        var fast = elementAt(index = 0)

        while (fast != null){
            fast = fast.next
            if (fast != null) {
                fast = fast.next
                slow = slow?.next
            }
        }
        return slow
    }

    /**
     * Reverse a list
     *
     * @return [LinkedList]
     */
    fun reverse(): LinkedList<T>{
        val reversedList = LinkedList<T>()
        val head = elementAt(index = 0)
        if(head != null){
            addElementInReverse(list = reversedList, node = head)
        }
        return reversedList
    }

    /**
     *  Returns a string representation of the linked list object
     *
     *  @return [String]
     */
    override fun toString(): String {
        return if (isEmpty()) "empty list"
        else head.toString()
    }

    private fun <T> addElementInReverse(list: LinkedList<T>, node: Node<T>) {
        val next = node.next
        //if next node is not last
        if (next != null) {
            addElementInReverse(list, next)
        }
        list.insertLast(value = node.value)
    }
}