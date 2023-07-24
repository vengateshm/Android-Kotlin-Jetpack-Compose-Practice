package dev.vengateshm.compose_material3.expandable_list

interface ExpandableItem<out Parent, out Child> {
    fun parent(): Parent
    fun child(): Child
}