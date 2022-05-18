import avltree.AVLTree
import binarysearchtree.BinarySearchTree
import doublestack.StackQueue
import graph.BellmanFordGraph
import graph.Graph
import linkedlist.LinkedList
import linkedlist.Node
import queue.SimpleQueue
import sort.IntroSort
import stack.SimpleStack
import tree.BinaryNode
import tree.TreeNode

fun makeUniverseTree(): TreeNode<String> {
    val universe = TreeNode("Universe")

    val galaxy = TreeNode("fps")
    val superClusters = TreeNode("superClusters")

    val stars = TreeNode("stars")
    val planets = TreeNode("planets")
    val dust = TreeNode("dust")

    val dwarfStars = TreeNode("dwarfStars")
    val dwarfPlanets = TreeNode("dwarfPlanets")
    val comets = TreeNode("comets")

    val blackHole = TreeNode("blackHole")
    val neutronStar = TreeNode("neutronStar")


    universe.add(galaxy)
    universe.add(superClusters)

    galaxy.add(stars)
    galaxy.add(planets)
    galaxy.add(dust)

    superClusters.add(blackHole)
    superClusters.add(neutronStar)

    stars.add(dwarfStars)
    stars.add(dwarfPlanets)
    stars.add(comets)

    return universe
}

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

    //declaring stack
    val stack = SimpleStack<Int>().apply {
        push(1)
        push(2)
        push(3)
        push(4)
    }
    print(stack)
    //deleting the top element
    stack.pop()
    print(stack)

    val queue = SimpleQueue<String>().apply {
        enqueue("A")
        enqueue("B")
        enqueue("C")
    }
    println(queue)
    //deleting the element at the front of the queue
    queue.dequeue()
    println(queue)
    //getting the next element at the front of the queue
    println("Next : ${queue.peek()}")

    //testing with the operations as simple queue
    val stackQueue = StackQueue<String>().apply {
        enqueue("D")
        enqueue("E")
        enqueue("F")
        enqueue("G")
    }
    println(stackQueue)
    stackQueue.dequeue()
    println(stackQueue)
    println("Next: ${stackQueue.peek()}")


    val tree = makeUniverseTree()
    tree.forEachDepthFirst { println(it.value) }
    println("------------------------------------")
    tree.forEachLevelOrder { println(it.value) }

    tree.search("planets")?.let {
        println("Found node: ${it.value}")
    }

    tree.search("white hole")?.let {
        println(it.value)
    } ?: println("Couldn't white hole")


    println("------------------------")
    val zero = BinaryNode(0)
    val one = BinaryNode(1)
    val five = BinaryNode(5)
    val seven = BinaryNode(7)
    val eight = BinaryNode(8)
    val nine = BinaryNode(9)

    seven.leftChild = one
    one.leftChild = zero
    one.rightChild = five
    seven.rightChild = nine
    nine.leftChild = eight

    println(seven)
    seven.traverseInOrder { println(it) }
    println("---------------------------")
    seven.traversePreOrder { println(it) }
    println("---------------------------")
    seven.traversePostOrder { println(it) }

    println("---------------------------")
    val bst = BinarySearchTree<Int>()
    (0..6).forEach {
        bst.insert(it)
    }
    println(bst)
    println("---------------------------")
    val exampleTree = BinarySearchTree<Int>().apply {
        insert(3)
        insert(1)
        insert(4)
        insert(0)
        insert(2)
        insert(5)
    }
    println(exampleTree)
    println("---------------------------")
    if (exampleTree.contains(5)) {
        println("Found 5!")
    } else {
        println("Couldn't find 5")
    }
    println("---------------------------")
    println("Tree before removal:")
    println(exampleTree)
    exampleTree.remove(0)
    println("Tree after removing root:")
    println(exampleTree)
    println("---------------------------")
    val avlTree = AVLTree<Int>()

    (0..14).forEach {
        avlTree.insert(it)
    }

    println(avlTree)
    println("---------------------------")

    val g = Graph(8)

    g.addEdge(0, 1)
    g.addEdge(1, 2)
    g.addEdge(2, 3)
    g.addEdge(2, 4)
    g.addEdge(3, 0)
    g.addEdge(4, 5)
    g.addEdge(5, 6)
    g.addEdge(6, 4)
    g.addEdge(6, 7)

    println("Strongly Connected Components:")

    g.printSCC()

    println("---------------------------")
    val graph = Graph(4)

    graph.addEdge(0, 1)
    graph.addEdge(0, 2)
    graph.addEdge(1, 2)
    graph.addEdge(2, 3)

    println("Depth First Traversal")
    graph.dfs(2)

    println("---------------------------")
    val graph2 = Graph(4)
    graph2.addEdge(0, 1)
    graph2.addEdge(0, 2)
    graph2.addEdge(1, 2)
    graph2.addEdge(2, 0)
    graph2.addEdge(2, 3)
    graph2.addEdge(3, 3)

    println("Following is Breadth First Traversal (starting from vertex 2)")
    graph2.bfs(2)
    println("---------------------------")
    val v = 5 //vertices
    val e = 8 //edges
    val graphBF = BellmanFordGraph(v, e)
    // edge 0 --> 1
    graphBF.edge[0].s = 0;
    graphBF.edge[0].d = 1;
    graphBF.edge[0].w = 5;

    // edge 0 --> 2
    graphBF.edge[1].s = 0;
    graphBF.edge[1].d = 2;
    graphBF.edge[1].w = 4;

    // edge 1 --> 3
    graphBF.edge[2].s = 1;
    graphBF.edge[2].d = 3;
    graphBF.edge[2].w = 3;

    // edge 2 --> 1
    graphBF.edge[3].s = 2;
    graphBF.edge[3].d = 1;
    graphBF.edge[3].w = 6;

    // edge 3 --> 2
    graphBF.edge[4].s = 3;
    graphBF.edge[4].d = 2;
    graphBF.edge[4].w = 2;

    graphBF.bfAlgo(graphBF, 0)// 0 is the source vertex

    println("---------------------------")
    val bubbleSortData = arrayOf(-2, 45, 0, 11, -9)

    println("Sorted Array in Ascending Order:")

    bubbleSortData.bubbleSort()

    println(bubbleSortData.joinToString(separator = ","))

    println("---------------------------")

    val selectSortData = arrayOf(20, 12, 10, 15, 2)

    println("Sorted Array in Ascending Order: ")

    selectSortData.selectionSort()

    println(selectSortData.joinToString(separator = ","))

    println("---------------------------")

    val insertionSortData = arrayOf(9, 5, 1, 4, 3 )

    println("Sorted Array in Ascending Order: ")

    insertionSortData.insertionSort()

    println(insertionSortData.joinToString(separator = ","))

    println("---------------------------")

    val numbers = mutableListOf(38,27,43,3,9,82,10)

    val sortedList = numbers.mergeSort()

    println("Unsorted: $numbers")

    println("Sorted: $sortedList")

    println("---------------------------")
    println("Original list:")
    val quickSortData = listOf(2, 4, 7, 3, 6, 9, 5, 1, 0)
    println(quickSortData)
    println("Ordered list:")
    println(quickSortData.quicksort())
    println("---------------------------")
    val intro  = IntroSort()
    val arr = intArrayOf(5,1,4,2,9,6)
    println(arr.contentToString())
    intro.sort(arr)
    println(arr.contentToString())
    println("---------------------------")
    val shellSortData = intArrayOf(23, 12, 1, 8, 34, 54, 2, 3)
    println("Array before sorting")
    println(shellSortData.contentToString())
    shellSortData.shellSort()

    println("Array after sorting")
    println(shellSortData.contentToString())
    println("---------------------------")
    val array = arrayOf(0, 1, 2, 2, 2, 2, 5, 8, 4, 7, 0, 6)
    println("Before sorting : ")
    println(array.contentToString())
    array.cycleSort()
    println("Array after sorting")
    println(array.contentToString())
    println("---------------------------")

    val inArr = intArrayOf(4, 65, 2, -31, 0, 99, 2, 83, 782, 1)

    inArr.cocktailSort()
    println(inArr.contentToString())

    println("---------------------------")
}