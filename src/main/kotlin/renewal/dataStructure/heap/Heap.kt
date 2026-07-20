package renewal.dataStructure.heap

class MinHeap {
    private val firstIndex = 0
    private val heap = mutableListOf<Int>()
    private fun MutableList<Int>.swap(firstIndex: Int, secondIndex: Int) {
        val firstValue = this[firstIndex]
        val secondValue = this[secondIndex]

        this[firstIndex] = secondValue
        this[secondIndex] = firstValue
    }

    fun insert(value: Int) {
        heap.add(value)
        heapifyUp(heap.lastIndex)
    }

    fun extractMin(): Int? {
        if (heap.isEmpty()) return null
        if (heap.size == 1) return heap.removeLast()

        val first = heap.removeFirst()
        heap[firstIndex] = heap.removeLast()
        heapifyDown(firstIndex)

        return first
    }

    fun peek(): Int? {
        return heap.firstOrNull()
    }

    private fun heapifyUp(index: Int) {
        if (index == 0) return

        val parentsIndex = parent(index)
        if (heap[index] < heap[parentsIndex]) {
            heap.swap(index, parentsIndex)
            heapifyUp(parentsIndex)
        }
    }

    private fun heapifyDown(index: Int) {
        var smallest = index
        val leftChildIndex = leftChild(index)
        val rightChildIndex = rightChild(index)


        if (leftChildIndex < heap.size && heap[leftChildIndex] < heap[smallest]) {
            smallest = leftChildIndex
        }

        if (rightChildIndex < heap.size && heap[rightChildIndex] < heap[smallest]) {
            smallest = rightChildIndex
        }

        if (smallest != index) {
            heap.swap(index, smallest)
            heapifyDown(smallest)
        }
    }


    private fun parent(i: Int) = (i - 1) / 2
    private fun leftChild(i: Int) = 2 * i + 1
    private fun rightChild(i: Int) = 2 * i + 2
}

class MaxHeap {
    private val firstIndex = 0
    private val heap = mutableListOf<Int>()
    private fun MutableList<Int>.swap(firstIndex: Int, secondIndex: Int) {
        val firstValue = this[firstIndex]
        val secondValue = this[secondIndex]

        this[firstIndex] = secondValue
        this[secondIndex] = firstValue
    }

    fun insert(value: Int) {
        heap.add(value)
        heapifyUp(heap.lastIndex)
    }

    fun extractMax(): Int? {
        if (heap.isEmpty()) return null
        if (heap.size == 1) return heap.removeLast()

        val first = heap.removeFirst()
        heap[firstIndex] = heap.removeLast()
        heapifyDown(firstIndex)

        return first
    }

    fun peek(): Int? {
        return heap.first()
    }

    private fun heapifyUp(index: Int) {
        if (index == 0) return

        val parentsIndex = parent(index)
        if (heap[index] > heap[parentsIndex]) {
            heap.swap(index, parentsIndex)
            heapifyUp(parentsIndex)
        }
    }

    private fun heapifyDown(index: Int) {
        var largest = index
        val leftChildIndex = leftChild(index)
        val rightChildIndex = rightChild(index)

        if (leftChildIndex in heap.indices && heap[leftChildIndex] > heap[largest]) {
            largest = leftChildIndex
        }

        if (rightChildIndex in heap.indices && heap[rightChildIndex] > heap[largest]) {
            largest = rightChildIndex
        }

        if (largest != index) {
            heap.swap(index, largest)
            heapifyDown(largest)
        }
    }

    private fun parent(i: Int) = (i - 1) / 2
    private fun leftChild(i: Int) = 2 * i + 1
    private fun rightChild(i: Int) = 2 * i + 2
}