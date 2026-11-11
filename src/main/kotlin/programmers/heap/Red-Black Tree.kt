package programmers.heap

import programmers.heap.`Red-Black Tree`.Node.Color

class `Red-Black Tree`() {

    private var rootNode: Node? = null


    class Node(
        var parent: Node?,
        var leftChild: Node? = null,
        var rightChild: Node? = null,
        var color: Color = Color.RED,
        val value: Int
    ) {
        enum class Color { RED, BLACK }
    }

    fun insert(value: Int) {

        var parent: Node? = null
        var current = rootNode


        while (current != null) {
            parent = current
            current = when {
                value < current.value -> current.leftChild
                value > current.value -> current.rightChild
                else -> return
            }
        }


        val newNode = Node(value = value, parent = parent)

        when {
            parent == null -> rootNode = newNode
            value < parent.value -> parent.leftChild = newNode
            else -> parent.rightChild = newNode
        }

        rebalance(newNode)
    }

    /**
     *    P
     *     \
     *      N
     *     / \
     *    RL  RR
     *
     *
     *      N
     *    /  \
     *   P   RR
     *    \
     *     RL
     */

    private fun rotateLeft(node: Node) {
        var right = node.rightChild ?: return

        var rightLeft = right?.leftChild
        node.rightChild = rightLeft
        rightLeft?.parent = node

        right.parent = node.parent
        when {
            node.parent == null -> rootNode = right
            node == node.parent!!.leftChild -> node.parent!!.leftChild = right
            else -> node.parent!!.rightChild = right
        }


        right.leftChild = node
        node.parent = right
    }


    /**
     *         P
     *       /
     *      N
     *     / \
     *    LL  LR
     *
     *
     *      N
     *    /  \
     *   LL    P
     *       /
     *     LR
     */

    private fun rotateRight(node: Node) {
        var left = node.leftChild ?: return

        var leftRight = left?.leftChild
        node.leftChild = leftRight
        leftRight?.parent = node

        left.parent = node.parent
        when {
            node.parent == null -> rootNode = left
            node == node.parent!!.leftChild -> node.parent!!.leftChild = left
            else -> node.parent!!.rightChild = left
        }

        left.rightChild = node
        node.parent = left
    }

    private fun rebalance(node: Node) {
        var cur = node

        while (cur.parent?.color == Color.RED) {
            val parent = cur.parent
            val grandparent = parent?.parent ?: break
            val uncle: Node?


            if(parent == grandparent.leftChild) { //UNCLE = RED
                uncle = grandparent.rightChild

                if(uncle?.color == Color.RED) {
                    parent.color = Color.BLACK
                    uncle.color = Color.BLACK
                    grandparent.color = Color.RED
                    cur = grandparent
                }
                else {
                    if(cur == parent.rightChild) { // UNCLE = BLACK, LR (parent rotateLeft LL)
                        cur = parent
                        rotateLeft(cur)
                    }


                    // UNCLE = BLACK, LL (GrandParent Rotate Right + change  color)
                    cur.parent!!.color = Color.BLACK
                    grandparent.color = Color.RED
                    rotateRight(grandparent)
                }
            }
            else {
                uncle = grandparent.leftChild

                if(uncle?.color == Color.RED) {
                    parent.color = Color.BLACK
                    uncle.color = Color.BLACK
                    grandparent.color = Color.RED
                    cur = grandparent
                }
                else {
                    // UNCLE = BLACK, RL ( parent rotateRight RR)
                    if(cur == parent.leftChild) {
                        cur = parent
                        rotateRight(cur)
                    }
                    // UNCLE = BLACK, RR (GrandParent Rotate Left + change color)
                    cur.parent!!.color = Color.BLACK
                    grandparent.color = Color.RED
                    rotateLeft(grandparent)
                }
            }
        }

        rootNode!!.color = Color.BLACK
    }
}
