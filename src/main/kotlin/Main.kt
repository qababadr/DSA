import linkedlist.LinkedList
import linkedlist.Node

fun main(){

    val link1 = Node(value = 0)
    val link2 = Node(value = 1)
    val link3 = Node(value = 2)

    //connecting link1 to link2
    link1.next = link2
    //connecting link2 to link3
    link2.next = link3

    //displaying the link1
    println(link1)

    val linkedList = LinkedList<Int>()
    linkedList.insertFirst(value = 1)
        .insertFirst(value = 2)
        .insertFirst(value = 3)

    val linkedList2 = LinkedList<String>()
    linkedList2.insertLast(value = "A")
    linkedList2.insertLast(value = "B")
    linkedList2.insertLast(value = "C")
    linkedList2.insertLast(value = "E")

    //displaying the linked lists
    println(linkedList)
    println(linkedList2)

    //inserting element at index 2
    linkedList2.insert(value = "D", nextNode = linkedList2.elementAt(index = 2)!!)
    println(linkedList2)

    //deleting first element
    linkedList2.removeFirst()
    println(linkedList2)

    //deleting the last element
    linkedList2.removeLast()
    println(linkedList2)

    //deleting element at the index 1
    linkedList2.removeAfter(node = linkedList2.elementAt(index = 1)!!)
    println(linkedList2)

    //displaying in reverse

    linkedList2.printInReverse()


    //displaying the middle element
    println(linkedList.elementAtMiddle()?.value)

    //reversing the list 1
    val reversedList1 = linkedList.reverse()

    //displaying the reversed list
    println(reversedList1)

}