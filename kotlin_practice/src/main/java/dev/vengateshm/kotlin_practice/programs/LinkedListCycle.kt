package dev.vengateshm.kotlin_practice.programs

data class LinkedListNode(val value: Int, var next: LinkedListNode? = null)

fun main() {
    val node1 = LinkedListNode(3)
    val node2 = LinkedListNode(2)
    val node3 = LinkedListNode(0)
    val node4 = LinkedListNode(-4)
    node1.next = node2
    node2.next = node3
    node3.next = node4
    node4.next = node2
    println(hasCycle(node1))
}

//Hare & Tortoise Algorithm
fun hasCycle(head: LinkedListNode?): Boolean {
    var slow = head
    var fast = head

    while (fast != null && fast.next != null) {
        fast = fast.next?.next
        slow = slow?.next
        if (slow == fast) {
            return true
        }
    }

    return false
}