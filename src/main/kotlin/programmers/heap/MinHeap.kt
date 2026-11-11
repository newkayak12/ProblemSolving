package programmers.heap

import java.lang.IllegalStateException


class MinHeap() {
    val heap = mutableListOf<Int>()


    private fun heapifyUp() {
        var i = heap.size - 1

        while (i > 0) {
            var smallest = (i - 1) / 2
            if (heap[smallest] < heap[i]) break

            val temp = heap[smallest]
            heap[smallest] = heap[i]
            heap[i] = temp
            i = smallest
        }
    }

    private fun heapifyDown() {
        var i = 0

        while (i < heap.size) {
            var smallest = i
            var leftChild = 2 * i + 1
            var rightChild = 2 * i + 2

            if (leftChild in heap.indices && heap[smallest] > heap[leftChild]) smallest = leftChild
            if (rightChild in heap.indices && heap[smallest] > heap[rightChild]) smallest = rightChild

            if (smallest == i) break


            val temp = heap[i]
            heap[i] = heap[smallest]
            heap[smallest] = temp
            i = smallest
        }
    }

    fun add(number: Int) {
        heap.add(number)
        heapifyUp()
    }

    fun remove() = when {
        heap.isEmpty() -> throw IllegalStateException()
        heap.size == 1 -> heap.removeFirst()
        else -> heap[0].also {
            heap[0] = heap.removeLast()
            heapifyDown()
        }
    }

}
class MaxHeap{
    private val heap = mutableListOf<Int>()

    fun heapifyUp(){

        var i = heap.size - 1
        while (i > 0) {
            var largest = (i - 1) / 2
            if (heap[largest] > heap[i])   break


            val temp = heap[largest]
            heap[i] = heap[largest]
            heap[largest] = temp
            i = largest
        }
    }

    fun heapifyDown(){
        var i = 0
        while (i < heap.size){
            var largest = i
            var leftChild = 2 * i + 1
            var rightChild = 2 * i + 2

            if(leftChild in heap.indices && heap[largest] < leftChild) largest = leftChild
            if(rightChild in heap.indices && heap[largest] < rightChild) largest = rightChild

            if(largest == i) break

            val temp = heap[largest]
            heap[i] = heap[largest]
            heap[largest] = temp
            i = largest
        }

        fun add(number: Int){
            heap.addLast(number)
            heapifyUp()
        }

        fun remove() = when {
            heap.isEmpty() -> throw IllegalStateException()
            heap.size == 1 -> heap.removeFirst()
            else -> heap[0].also {
                heap[0] = heap.removeLast()
                heapifyDown()
            }
        }
    }
}