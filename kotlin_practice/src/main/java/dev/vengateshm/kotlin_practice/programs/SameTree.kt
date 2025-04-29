package dev.vengateshm.kotlin_practice.programs

fun main() {
    println(areTreesSame(null, null))
    println(areTreesSame(null, TreeNode(1)))
    println(areTreesSame(TreeNode(2), TreeNode(1)))
    println(areTreesSame(TreeNode(1), TreeNode(1)))
    val treeA = TreeNode(
        1,
        left = TreeNode(2),
        right = TreeNode(3),
    )
    val treeB = TreeNode(
        1,
        left = TreeNode(2),
        right = TreeNode(3),
    )
    println(areTreesSame(treeA, treeB))
    val treeC = TreeNode(
        1,
        left = TreeNode(2),
        right = TreeNode(4),
    )
    println(areTreesSame(treeA, treeC))
    val treeD = TreeNode(
        1,
        left = TreeNode(
            2,
            left = TreeNode(3),
        ),
    )
    val treeE = TreeNode(
        1,
        left = TreeNode(2),
        right = TreeNode(3),
    )
    println(areTreesSame(treeD, treeE))
}

fun areTreesSame(p: TreeNode?, q: TreeNode?): Boolean {
    if (p == null && q == null) return true
    if (p == null || q == null) return false
    if (p.value != q.value) return false
    return areTreesSame(p.left, q.left) && areTreesSame(p.right, q.right)
}

data class TreeNode(val value: Int, val left: TreeNode? = null, val right: TreeNode? = null)