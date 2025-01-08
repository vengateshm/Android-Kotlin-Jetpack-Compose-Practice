package dev.vengateshm.kotlin_practice.data_structures.trees

class TreeNode<T>(val value: T) {
    var left: TreeNode<T>? = null
    var right: TreeNode<T>? = null
}

class BinaryTree<T> {
    var root: TreeNode<T>? = null

    fun insert(value: T) {
        val newNode = TreeNode(value)

        if (root == null) {
            root = newNode
            return
        }

        val list = mutableListOf<TreeNode<T>>()
        list.add(root!!)

        while (list.isNotEmpty()) {
            val currentNode = list.removeAt(0)
            if (currentNode.left == null) {
                currentNode.left = newNode
                return
            } else {
                list.add(currentNode.left!!)
            }

            if (currentNode.right == null) {
                currentNode.right = newNode
                return
            } else {
                list.add(currentNode.right!!)
            }
        }
    }

    fun printLevelOrder() {
        val list = mutableListOf<TreeNode<T>>()
        list.add(root!!)

        while (list.isNotEmpty()) {
            val currentNode = list.removeAt(0)
            println("${currentNode.value}")
            currentNode.left?.let { list.add(it) }
            currentNode.right?.let { list.add(it) }
        }
    }

    fun printLevelOrderStructure() {
        val list = mutableListOf<Pair<TreeNode<T>, Int>>()
        list.add(Pair(root!!, 0))

        var currentLevel = 0
        while (list.isNotEmpty()) {
            val (currentNode, level) = list.removeAt(0)

            if (level > currentLevel) {
                println()
                currentLevel = level
            }

            print("${" ".repeat(level * 4)}${currentNode.value}")

            currentNode.left?.let { list.add(Pair(it, level + 1)) }
            currentNode.right?.let { list.add(Pair(it, level + 1)) }
        }
    }
}

fun main() {
    val tree = BinaryTree<Int>()
    val values = listOf(1, 2, 3, 4, 5, 6, 7)
    for (value in values) {
        tree.insert(value)
    }
    tree.printLevelOrder()
    tree.printLevelOrderStructure()
}