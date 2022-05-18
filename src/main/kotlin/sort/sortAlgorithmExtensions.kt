/**
 * Bubble Sort algorithm
 *
 * Bubble sort is a sorting algorithm that compares two adjacent elements
 * and swaps them until they are not in the intended order.
 *
 * Bubble sort algorithm consist of the following steps:
 *
 * - 1 - Starting from the first index, compare the first and the second elements.
 *
 * - 2 - If the first element is greater than the second element, they are swapped.
 *
 * - 3 - Now, compare the second and the third elements. Swap them if they are not in order.
 *
 * - 4- The above process goes on until the last element.
 *
 * Time Complexity
 *
 * - Best O(n)
 *
 * - Worst O(n2)
 *
 * - Average O(n2)
 *
 * - Space Complexity O(1)
 *
 * - Stability Yes
 *
 * @return [Array]
 */
fun Array<Int>.bubbleSort(): Array<Int> {
    for (i in 0 until size - 1) {
        var swapped = false
        for (j in 0 until (size - i - 1)) {
            if (this[j] > this[j + 1]) {
                val temp = this[j]
                this[j] = this[j + 1]
                this[j + 1] = temp
                swapped = true
            }
        }
        if (!swapped) {
            break
        }
    }
    return this
}

/**
 * Selection Sort Algorithm
 *
 * Selection sort is a sorting algorithm that selects the smallest element from an unsorted list
 * in each iteration and places that element at the beginning of the unsorted list.
 *
 * The steps as follows :
 *
 * 1- Set the first element as minimum.
 *
 * 2- Compare minimum with the second element. If the second element is smaller than minimum, assign the second element as minimum.
 * Compare minimum with the third element. Again, if the third element is smaller,
 * then assign minimum to the third element otherwise do nothing. The process goes on until the last element.
 *
 * 3- After each iteration, minimum is placed in the front of the unsorted list.
 *
 * 4- For each iteration, indexing starts from the first unsorted element.
 * Step 1 to 3 are repeated until all the elements are placed at their correct positions.
 *
 * Time Complexity
 *
 * - Best O(n2)
 * - Worst O(n2)
 * - Average O(n2)
 * - Space Complexity O(1)
 * - Stability No
 *
 * @return [Array]
 */
fun Array<Int>.selectionSort(): Array<Int> {
    for (step in 0 until size - 1) {
        var minIndex = step
        for (i in step + 1 until size) {
            if (this[i] < this[minIndex]) {
                minIndex = i
            }
        }
        val temp = this[step]
        this[step] = this[minIndex]
        this[minIndex] = temp
    }
    return this
}

/**
 * Insertion Sort Algorithm
 *
 * 1- The first element in the array is assumed to be sorted. Take the second element and store it separately in key.
 * Compare key with the first element. If the first element is greater than key, then key is placed in front of the first element.
 *
 * 2- Now, the first two elements are sorted.
 * Take the third element and compare it with the elements on the left of it.
 * Placed it just behind the element smaller than it. If there is no element smaller than it,
 * then place it at the beginning of the array.
 *
 * 3- Similarly, place every unsorted element at its correct position.
 *
 * Time Complexity
 *
 * - Best	O(n)
 * - Worst O(n2)
 * - Average O(n2)
 * - Space Complexity O(1)
 * - Stability Yes
 *
 * @return [Array]
 */
fun Array<Int>.insertionSort(): Array<Int> {
    for (step in 1 until size) {
        val key = this[step]
        var j = step - 1
        while (j >= 0 && key < this[j]) {
            this[j + 1] = this[j]
            --j
        }
        this[j + 1] = key
    }
    return this
}

/**
 * Merge two subarrays L and M into arr
 * @param left [List]
 * @param right [List]
 * @return [List]
 */
internal fun merge(left: List<Int>, right: List<Int>): List<Int> {
    var indexLeft = 0
    var indexRight = 0
    val newList: MutableList<Int> = mutableListOf()

    while (indexLeft < left.count() && indexRight < right.count()) {
        if (left[indexLeft] <= right[indexRight]) {
            newList.add(left[indexLeft])
            indexLeft++
        } else {
            newList.add(right[indexRight])
            indexRight++
        }
    }

    while (indexLeft < left.size) {
        newList.add(left[indexLeft])
        indexLeft++
    }

    while (indexRight < right.size) {
        newList.add(right[indexRight])
        indexRight++
    }
    return newList
}

/**
 * MergeSort Algorithm
 *
 * The MergeSort function repeatedly divides the array into two halves until we reach a stage where we
 * try to perform MergeSort on a subarray of size 1 i.e. p == r.
 * After that, the merge function comes into play and combines the sorted arrays into
 * larger arrays until the whole array is merged.
 *
 * Time Complexity
 * - Best Case Complexity: O(n*log n)
 * - Worst Case Complexity: O(n*log n)
 * - Average Case Complexity: O(n*log n)
 *
 *  Space Complexity
 *
 * The space complexity of merge sort is O(n).
 * @return [List]
 */
fun List<Int>.mergeSort(): List<Int> {
    if (size <= 1) {
        return this
    }

    val middle = size / 2
    val left = subList(0, middle)
    val right = subList(middle, size)

    return merge(left.mergeSort(), right.mergeSort())
}

/**
 * Quicksort is a sorting
 *
 * Quicksort is a sorting algorithm based on the divide and conquer approach where:
 *
 * 1- An array is divided into subarrays by selecting a pivot element (element selected from the array).
 * While dividing the array, the pivot element should be positioned in such a way that elements less than
 * pivot are kept on the left side and elements greater than pivot are on the right side of the pivot.
 *
 * 2- The left and right subarrays are also divided using the same approach. This process continues until each subarray contains a single element.
 *
 * 3- At this point, elements are already sorted. Finally, elements are combined to form a sorted array.
 *
 * Time Complexity
 *
 * - Best O(n*log n)
 * - Worst O(n2)
 * - Average O(n*log n)
 *
 * Space Complexity O(log n)
 *
 * Stability No
 *
 * @param items [List]
 * @return [List]
 */
fun quicksort(items: List<Int>): List<Int> {
    if (items.count() < 2) {
        return items
    }
    val pivot = items[items.count() / 2]

    val equal = items.filter { it == pivot }
//    println("pivot value is : "+equal)

    val less = items.filter { it < pivot }
//    println("Lesser values than pivot : "+less)

    val greater = items.filter { it > pivot }
//    println("Greater values than pivot : "+greater)

    return quicksort(less) + equal + quicksort(greater)
}

/**
 * Shell Sort
 *
 * Shell sort algorithm consist of the following steps :
 *
 * 1- Decide the gap, then reduce the gap
 *
 * 2- Do a gaped insertion sort for this gap size. The first gap elements a[0...gap-1] are already
 * in gapped order keep adding one more element
 * until the entire array is gap sorted
 *
 * 3- Add a[ i ]  to the elements that have been gap sorted save a[ i ] in temp and make a hole at position i
 *
 * 4- Shift earlier gap-sorted elements up until the correct location for a[ i ] is found
 *
 * 5- Put temp (the original a[ i ]) in its correct location
 *
 * @return [Int]
 */
fun IntArray.shellSort(): Int {
    val n = size

    // decide the gap, then reduce the gap
    var gap = n / 2
    while (gap > 0) {
        // Do a gaped insertion sort for this gap size.
        // The first gap elements a[0..gap-1] are already
        // in gaped order keep adding one more element
        // until the entire array is gap sorted
        var i = gap
        while (i < n) {
            // add a[i] to the elements that
            // have been gap sorted save a[i] in temp and make a hole at
            // position i
            val temp = this[i]

            // shift earlier gap-sorted elements up until
            //the correct location for a[i] is found
            var j = i
            while (j >= gap && this[j - gap] > temp) {
                this[j] = this[j - gap]
                j -= gap
            }

            // put temp (the original a[i]) in its correct
            // location
            this[j] = temp
            i += 1
        }
        gap /= 2
    }
    return 0
}

/**
 * Cycle Sort
 *
 * Cycle sort is a comparison sorting algorithm, which forces array to be factored into the number of cycles,
 * where each of them can be rotated to produce a sorted array. It is theoretically optimal in the sense that it reduces the number of writes to the original array.
 * Unlike every other sort, items are never written elsewhere in the array simply to push them out of the way of the action.
 * Each value is either written zero times, if it's already in its correct position or written one time to its correct position.
 * This matches the minimal number of overwrites required for a completed in-place sort.
 *
 * @return [Int]
 */
fun Array<Int>.cycleSort(): Int {
    var writes = 0

    // Loop through the array to find cycles to rotate.
    for (cycleStart in 0 until this.size - 1) {
        var item = this[cycleStart]

        // Find where to put the item.
        var pos = cycleStart
        for (i in cycleStart + 1 until this.size){
            if (this[i] < item){
                pos++
            }
        }

        // If the item is already there, this is not a cycle.
        if (pos == cycleStart) continue

        // Otherwise, put the item there or right after any duplicates.
        while (item == this[pos]){
            pos++
        }
        val temp = this[pos]
        this[pos] = item
        item = temp
        writes++

        // Rotate the rest of the cycle.
        while (pos != cycleStart) {
            // Find where to put the item.
            pos = cycleStart
            for (i in cycleStart + 1 until this.size){
                if (this[i] < item) pos++
            }

            // Otherwise, put the item there or right after any duplicates.
            while (item == this[pos]){
                pos++
            }
            val temp2 = this[pos]
            this[pos] = item
            item = temp2
            writes++
        }
    }
    return writes
}

/**
 * Cocktail sort
 *
 * Cocktail sort is the variation of Bubble Sort which traverses the list in both directions alternatively.
 * It is different from a bubble sort in the sense that, bubble sort traverses the list in forward direction only,
 * while cocktail sort algorithm traverses in forward as well as backward direction in one iteration
 * Much like the bubble sort, cocktail sort has very little relevance in the real world and is mainly used to teach algorithms.
 * Cocktail sort is at its fastest when it can reach a sorted list with a minimal number of passes. Since only adjacent elements are swapped,
 * this means that cocktail sort performs best when elements are physically nearby their sorted positions.
 */
fun IntArray.cocktailSort() {
    fun swap(i: Int, j: Int) {
        val temp = this[i]
        this[i] = this[j]
        this[j] = temp
    }
    do {
        var swapped = false
        for (i in 0 until size - 1)
            if (this[i] > this[i + 1]) {
                swap(i, i + 1)
                swapped = true
            }
        if (!swapped) break
        swapped = false
        for (i in size - 2 downTo 0)
            if (this[i] > this[i + 1]) {
                swap(i, i + 1)
                swapped = true
            }
    }while (swapped)
}