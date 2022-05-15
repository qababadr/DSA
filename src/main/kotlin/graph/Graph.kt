package graph

import java.util.LinkedList
import java.util.Stack

class Graph(private val v: Int) {

    private val adj: Array<LinkedList<Int>> = Array(v) { LinkedList() }
    private val visited: Array<Boolean> = Array(v) { false }

    /**
     * adds an edge to the graph
     *
     * @param s [Int]
     * @param d [Int]
     */
    fun addEdge(s: Int, d: Int){
        adj[s].add(d)
    }

    /**
     * <strong>Depth first search</strong>
     *
     *  Puts each vertex of the graph into one of two categories:
     *  Visited and Not Visited
     *  The DFS algorithm consist of:
     *
     *  - 1 - Putting graph's vertices on top of a stack.
     *
     *  - 2 - Add stack's item to visited list.
     *
     *  - 3 - Create a list of that vertex's adjacent nodes.
     *
     *  - 4 - Add the ones which aren't in the visited list to the top of the stack.
     *
     *  - 5 - Keep repeating steps 2 and 3 until the stack is empty.
     *
     *  The time complexity of the DFS algorithm is represented in the form of O(V + E),
     *  where V is the number of nodes and E is the number of edges.
     *  The space complexity of the algorithm is O(V)
     */
    fun dfs(vertex: Int){
        visited[vertex] = true
        println("$vertex ")
        val ite = adj[vertex].listIterator()
        while(ite.hasNext()){
            val adjacent = ite.next()
            if(!visited[adjacent]){
                dfs(adjacent)
            }
        }
    }


    /**
     * <strong>Breadth first search</strong>
     *
     * Puts each vertex of the graph into one of two categories: Visited and Not Visited
     *
     * The algorithm works as follows:
     * - 1 - Start by putting graph's vertices at the back of a queue.
     *
     * - 2 - Add to the visited list the front item of the queue.
     *
     * - 3 - Create a list of that vertex's adjacent nodes. Add the ones which aren't in the visited list to the back of the queue.
     *
     * - 4 - Keep repeating steps 2 and 3 until the queue is empty.
     *
     * The time complexity of the BFS algorithm is represented in the form of O(V + E),
     * where V is the number of nodes and E is the number of edges.
     * The space complexity of the algorithm is O(V).
     */
    fun bfs(s: Int){
        visited[s] = true
        val queue: LinkedList<Int> = LinkedList()
        queue.add(s)
        while(queue.isNotEmpty()){
            val d = queue.poll()
            val i = adj[d].listIterator()
            println("$d ")
            while(i.hasNext()){
                val n = i.next()
                if(!visited[n]){
                    visited[n] = true
                    queue.add(n)
                }
            }
        }
    }

    /**
     * Bellman Ford's algorithm
     *
     * Bellman Ford's algorithm consist of the following steps:
     *
     * - 1 - Start with the weighted graph
     *
     * - 2 - Choose a starting vertex and assign infinity path values to all the other vertices
     *
     * - 3 - Visit each edge and relax the path distances if they are inaccurate
     */
    fun belleManFord(){

    }

    /**
     * Print the stack
     */
    fun printSCC(){
        val stack = Stack<Int>()

        val visitedVertices: Array<Boolean> = Array(v){false}

        for(i in 0 until v){
            if(!visitedVertices[i]){
                fillOrder(i, visitedVertices, stack)
            }
        }

        val gr = transpose()

        for(i in 0 until v){
            visitedVertices[i] = false
        }

        while(stack.isNotEmpty()){
            val s = stack.pop()
            if(!visitedVertices[s]){
                gr.dfsUtil(s, visitedVertices)
                println()
            }
        }
    }

    private fun dfsUtil(s: Int, visitedVertices: Array<Boolean>){
        var n: Int
        val i = adj[s].iterator()
        visitedVertices[s] = true
        println("$s ")
        while(i.hasNext()){
            n = i.next()
            if(!visitedVertices[n]){
                dfsUtil(n, visitedVertices)
            }
        }
    }

    private fun transpose(): Graph{
        val g = Graph(v = v)
        for(s in 0 until v){
            val i = adj[s].listIterator()
            while (i.hasNext()){
                g.adj[i.next()].add(s)
            }
        }
        return g
    }

    private fun fillOrder(s: Int, visitedVertices: Array<Boolean>, stack: Stack<Int>){
        visitedVertices[s] = true
        val i = adj[s].iterator()
        while(i.hasNext()){
            val n = i.next()
            if(!visitedVertices[n]){
                fillOrder(n, visitedVertices, stack)
            }
        }
        stack.push(s)
    }
}