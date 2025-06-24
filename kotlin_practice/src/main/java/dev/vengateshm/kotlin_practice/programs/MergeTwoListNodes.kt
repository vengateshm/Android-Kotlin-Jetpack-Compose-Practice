package dev.vengateshm.kotlin_practice.programs

data class ListNode(val value: Int, var next: ListNode? = null)

fun main() {
  println(
    mergeTwoListNodes(
      ListNode(1, ListNode(2, ListNode(4))),
      ListNode(1, ListNode(3, ListNode(4))),
    ),
  )
}

fun mergeTwoListNodes(head1: ListNode?, head2: ListNode?): ListNode {
  var head1 = head1
  var head2 = head2
  val output = ListNode(0)
  var cur = output
  while (head1 != null && head2 != null) {
    if (head1.value < head2.value) {
      cur.next = head1
      head1 = head1.next
    } else {
      cur.next = head2
      head2 = head2.next
    }
    cur = cur.next!!
  }
  while (head1 != null) {
    cur.next = head1
    cur = cur.next!!
    head1 = head1.next
  }
  while (head2 != null) {
    cur.next = head2
    cur = cur.next!!
    head2 = head2.next
  }
  return output.next!!
}