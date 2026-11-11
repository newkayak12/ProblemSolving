package programmers.lv3

import java.util.PriorityQueue
import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test
import kotlin.math.max


class 이중우션순위큐 {

    @Test
    fun test1() {
        val operations = arrayOf("I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1")
        val result = intArrayOf(0, 0)

        assertArrayEquals(result, solution1(operations))
        assertArrayEquals(result, solution2(operations))
        assertArrayEquals(result, solution3(operations))
    }

    @Test
    fun test2() {
        val operations = arrayOf("I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333")
        val result = intArrayOf(333, -45)

        assertArrayEquals(result, solution1(operations))
        assertArrayEquals(result, solution2(operations))
        assertArrayEquals(result, solution3(operations))
    }

    fun solution1(operations: Array<String>): IntArray {
        val heap = Heap()
        for (operation in operations) {
            val split = operation.trim().split(" ")
            val action = split[0]

            when (action) {
                "I" -> {
                    heap.add(split[1].toInt())
                }

                else -> {
                    when {
                        split[1].equals("1") -> heap.removeMaximum()
                        else -> heap.removeMinimum()
                    }
                }
            }
        }

        return heap.root()
    }

    fun solution2(operations: Array<String>): IntArray {
        val array = mutableListOf<Int>()
        for (operation in operations) {
            val split = operation.trim().split(" ")
            val action = split[0]

            when (action) {
                "I" -> {
                    array.add(split[1].toInt())
                }

                else -> {
                    when {
                        split[1].equals("1") -> {
                            if (array.isNotEmpty()) {
                                val maximum = array.max()
                                array.remove(maximum)
                            }
                        }

                        else -> {
                            if (array.isNotEmpty()) {
                                val minimum = array.min()
                                array.remove(minimum)
                            }
                        }
                    }
                }
            }
        }

        return if (array.isEmpty()) intArrayOf(0, 0)
        else intArrayOf(array.max(), array.min())
    }

    fun solution3(operations: Array<String>): IntArray {
        val max = PriorityQueue<Int>(Comparator<Int> { o1, o2 -> o2 - o1 })
        val min = PriorityQueue<Int>(Comparator<Int> { o1, o2 -> o1 - o2 })


        for (operation in operations) {
            val split = operation.trim().split(" ")
            val action = split[0]

            when (action) {
                "I" -> {
                    val number = split[1].toInt()
                    max.add(number)
                    min.add(number)
                }

                else -> {
                    when {
                        split[1].equals("1") -> min.remove(max.poll())
                        else -> max.remove(min.poll())
                    }
                }
            }
        }


        return if (max.isEmpty() && min.isEmpty()) intArrayOf(0, 0)
        else {
            var maxValue = max.poll()
            var minValue = min.poll()
            intArrayOf(maxValue, minValue)
        }
    }

        /**
         * - 완전 이진 트리
         *    - 왼쪽부터 차례대로 빈틈없이 채워진 트리
         * - 부모 자식간 대소 비교 (최소힙: 부모가 자식보다 작음, 최대힙: 부모가 자식보다 큼)
         *    - 형제간 차이 비교 X
         * - 삽입 후 동작 (HeapifyUp)
         *   - 맨 뒤에 삽입
         * - 삭제 후 동작 (HeapifyDown)
         *   - root를 마지막 Node로 대체 후
         * - 인덱스
         *   - 부모 i
         *     - 왼쪽: 2i + 1
         *     - 오른쪽: 2i + 2
         *   - 자식 j
         *     - 부모 (j - 1) / 2
         */
        class Heap {

            private val minimumHeap = MinimumHeap()
            private val maximumHeap = MaximumHeap()

            fun add(number: Int) {
                minimumHeap.insert(number)
                maximumHeap.insert(number)
            }

            fun removeMaximum() = runCatching {
                val removedValue = maximumHeap.delete()
                minimumHeap.delete(removedValue)
            }

            fun removeMinimum() = runCatching {
                val removedValue = minimumHeap.delete()
                maximumHeap.delete(removedValue)
            }


            fun root(): IntArray {
                val maximum = maximumHeap.root()
                val minimum = minimumHeap.root()

                return if (maximum == -1 && minimum == -1) return intArrayOf(0, 0)
                else return intArrayOf(maximum, minimum)
            }


            private class MinimumHeap {
                private val heapArray = mutableListOf<Int>()

                fun heapifyUp(startIndex: Int) {
                    var index = startIndex

                    while (index > 0) {
                        val smallest = (index - 1) / 2
                        if (heapArray[index] > heapArray[smallest]) break

                        val temp = heapArray[index]
                        heapArray[index] = heapArray[smallest]
                        heapArray[smallest] = temp
                        index = smallest
                    }
                }

                fun heapifyDown(startIndex: Int) {
                    var index = startIndex

                    while (index < heapArray.size) {
                        var smallest = index
                        var leftChild = 2 * index + 1
                        var rightChild = 2 * index + 2

                        if (
                            leftChild in heapArray.indices &&
                            heapArray[smallest] > heapArray[leftChild]
                        ) smallest = leftChild
                        if (
                            rightChild in heapArray.indices &&
                            heapArray[smallest] > heapArray[rightChild]
                        ) smallest = rightChild

                        if (smallest == index) break

                        val temp = heapArray[index]
                        heapArray[index] = heapArray[smallest]
                        heapArray[smallest] = temp
                        index = smallest
                    }
                }

                fun insert(number: Int) {
                    heapArray.addLast(number)
                    heapifyUp(heapArray.lastIndex)
                }

                fun delete() = when {
                    heapArray.isEmpty() -> throw IllegalStateException()
                    heapArray.size == 1 -> heapArray.removeFirst()
                    else -> heapArray.first()
                        .also { heapArray[0] = heapArray.removeLast() }
                        .also { heapifyDown(0) }
                }

                fun delete(number: Int) {
                    if (heapArray.isEmpty() || !heapArray.contains(number)) return
                    val index = heapArray.indexOf(number)

                    if (heapArray.size == 1 || index == heapArray.lastIndex) {
                        heapArray.removeLast()
                        return
                    }

                    heapArray[index] = heapArray.removeLast()
                    val parent = (index - 1) / 2
                    if (parent in heapArray.indices && heapArray[parent] > heapArray[index]) heapifyUp(index)
                    else heapifyDown(index)
                }

                fun root() = if (heapArray.isEmpty()) -1 else heapArray.first()
            }

            private class MaximumHeap {
                private val heapArray = mutableListOf<Int>()

                private fun heapifyUp(startIndex: Int) {
                    var index = startIndex
                    while (index > 0) {
                        val largest = (index - 1) / 2
                        if (heapArray[index] < heapArray[largest]) break

                        val temp = heapArray[index]
                        heapArray[index] = heapArray[largest]
                        heapArray[largest] = temp

                        index = largest
                    }
                }

                private fun heapifyDown(startIndex: Int) {
                    var index = startIndex
                    while (index < heapArray.size) {
                        var largest = index
                        var leftChild = 2 * index + 1
                        var rightChild = 2 * index + 2

                        if (
                            leftChild in heapArray.indices &&
                            heapArray[leftChild] > heapArray[largest]
                        ) largest = leftChild

                        if (
                            rightChild in heapArray.indices &&
                            heapArray[rightChild] > heapArray[largest]
                        ) largest = rightChild

                        if (index == largest) break

                        val temp = heapArray[index]
                        heapArray[index] = heapArray[largest]
                        heapArray[largest] = temp

                        index = largest
                    }


                }

                fun insert(number: Int) {
                    heapArray.addLast(number)
                    heapifyUp(heapArray.lastIndex)
                }

                fun delete() = when {
                    heapArray.isEmpty() -> throw IllegalStateException()
                    heapArray.size == 1 -> heapArray.removeFirst()
                    else -> heapArray.first()
                        .also { heapArray[0] = heapArray.removeLast() }
                        .also { heapifyDown(0) }
                }

                fun delete(number: Int) {
                    if (heapArray.isEmpty() || !heapArray.contains(number)) return

                    val index = heapArray.indexOf(number)

                    if (heapArray.size == 1 || index == heapArray.lastIndex) {
                        heapArray.removeLast()
                        return
                    }


                    heapArray[index] = heapArray.removeLast()

                    val parent = (index - 1) / 2

                    if (parent in heapArray.indices && heapArray[parent] < heapArray[index]) heapifyUp(index)
                    else heapifyDown(index)
                }

                fun root() = if (heapArray.isEmpty()) -1 else heapArray.first()
            }
        }
    }