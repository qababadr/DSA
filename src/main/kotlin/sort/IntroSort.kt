package sort

import kotlin.math.floor
import kotlin.math.ln

/**
 * IntroSort begins with quicksort and if the recursion depth goes more than a
 * particular limit it switches to Heapsort to avoid Quicksort’s worse case O(N2) time complexity.
 */
class IntroSort {

    private val sizeThreshold = 16

    /**
     * @param a [IntArray]
     */
    fun sort(a: IntArray) {
        introSortLoop(a, 0, a.size, 2 * floorLg(a.size))
    }

    /**
     * @param a [IntArray]
     * @param begin [Int]
     * @param end [Int]
     */
    fun sort(a: IntArray, begin: Int, end: Int) {
        if (begin < end) {
            introSortLoop(a, begin, end, 2 * floorLg(end - begin))
        }
    }

    /**
     * Quicksort algorithm modified for IntroSort
     *
     * @param a [IntArray]
     * @param lo [Int]
     * @param hi [Int]
     * @param depthLimit [Int]
     */
    private fun introSortLoop(a: IntArray, lo: Int, hi: Int, depthLimit: Int) {
        var h = hi
        var dl = depthLimit
        while (h - lo > sizeThreshold) {
            if (dl == 0) {
                heapsort(a, lo, h)
                return
            }
            dl -= 1
            val p = partition(a, lo, h, medianOf3(a, lo, lo + (h - lo) / 2 + 1, h - 1))
            introSortLoop(a, p, h, dl)
            h = p
        }
        insertionSort(a, lo, h)
    }

    private fun partition(a: IntArray, lo: Int, hi: Int, x: Int): Int {
        var i = lo
        var j = hi
        while (true) {
            while (a[i] < x) i++
            j -= 1
            while (x < a[j]) j -= 1
            if (i >= j)
                return i
            exchange(a, i, j)
            i++
        }
    }

    private fun medianOf3(a: IntArray, lo: Int, mid: Int, hi: Int): Int {
        return if (a[mid] < a[lo]) {
            if (a[hi] < a[mid])
                a[mid]
            else {
                if (a[hi] < a[lo])
                    a[hi]
                else
                    a[lo]
            }
        } else {
            if (a[hi] < a[mid]) {
                if (a[hi] < a[lo])
                    a[lo]
                else
                    a[hi]
            } else
                a[mid]
        }
    }

    /* Heapsort algorithm */
    private fun heapsort(a: IntArray, lo: Int, hi: Int) {
        val n = hi - lo
        run {
            var i = n / 2
            while (i >= 1) {
                downHeap(a, i, n, lo)
                i -= 1
            }
        }
        var i = n
        while (i > 1) {
            exchange(a, lo, lo + i - 1)
            downHeap(a, 1, i - 1, lo)
            i -= 1
        }
    }

    private fun downHeap(a: IntArray, i: Int, n: Int, lo: Int) {
        var i = i
        val d = a[lo + i - 1]
        var child: Int
        while (i <= n / 2) {
            child = 2 * i
            if (child < n && a[lo + child - 1] < a[lo + child]) {
                child++
            }
            if (d >= a[lo + child - 1]) break
            a[lo + i - 1] = a[lo + child - 1]
            i = child
        }
        a[lo + i - 1] = d
    }

    /* Insertion sort algorithm */
    private fun insertionSort(a: IntArray, lo: Int, hi: Int) {
        var j: Int
        var t: Int
        var i: Int = lo
        while (i < hi) {
            j = i
            t = a[i]
            while (j != lo && t < a[j - 1]) {
                a[j] = a[j - 1]
                j--
            }
            a[j] = t
            i++
        }
    }

    /* Common methods for all algorithms */
    private fun exchange(a: IntArray, i: Int, j: Int) {
        val t = a[i]
        a[i] = a[j]
        a[j] = t
    }

    private fun floorLg(a: Int): Int {
        return floor(ln(a.toDouble()) / ln(2.0)).toInt()
    }
}