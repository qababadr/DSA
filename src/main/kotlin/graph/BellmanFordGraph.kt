package graph

class GraphEdge(
    var s: Int = 0,
    var d: Int = 0,
    var w: Int = 0
)

class BellmanFordGraph(
    private val v: Int,
    private val e: Int
) {

    val edge: Array<GraphEdge> = Array(size = e){ GraphEdge() }

    /**
     * Bellman ford algorithm
     *
     * The bellman ford algorithm consist of the following steps:
     *
     * - 1 - Fill the distance array and predecessor array
     *
     * - 2 - Relax edges |V| - 1 times
     *
     * - 3 - Detect negative cycle if value changes then we have a negative cycle in the graph, and we cannot find the shortest distances
     *
     * Time Complexity:
     *
     * - Best Case Complexity O(E)
     *
     * - Average Case Complexity	O(VE)
     *
     * - The Worst Case Complexity	O(VE)
     *
     * Space Complexity O(V).
     */
    fun bfAlgo(graph: BellmanFordGraph, s: Int){
        val _v = graph.v
        val _e = graph.e

        //step1
        val dist: Array<Int> = Array(_v){ Int.MAX_VALUE }
        dist[s] = 0

        //step2
        for(i in 1 until _v){
            for(j in 0 until _e  ){
                // Get the edge data
                val u = graph.edge[j].s
                val v = graph.edge[j].d
                val w = graph.edge[j].w

                if(dist[u] != Int.MAX_VALUE && dist[u] + w < dist[v]){
                    dist[v] = dist[u] + w
                }
            }
        }
        //step3
        for(j in 0 until _e){
            val u = graph.edge[j].s
            val v = graph.edge[j].d
            val w = graph.edge[j].w

            if(dist[u] != Int.MAX_VALUE && dist[u] + w < dist[v]){
                println("CreateGraph contains negative w cycle")
                return
            }
        }

        // Print the distance and predecessor array
        printSolution(dist, _v)
    }

    // Print the solution
    private fun printSolution(dist: Array<Int>, v: Int) {
        println("Vertex Distance from Source");
        for (i in 0 until v){
            println("$i \t\t + ${dist[i]}")
        }
    }
}