package dev.vengateshm.kotlin_practice

import com.google.common.collect.ImmutableList
import com.google.common.collect.ImmutableSet
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException

class ImmutableCollectionsTest {
    @Rule
    @JvmField
    var ee: ExpectedException = ExpectedException.none()

    @Test
    fun givenReadOnlyList_whenCastToMutableList_checkNewElementsAdded() {
        val list: List<String> = listOf("This", "Is", "Totally", "Immutable")
        (list as MutableList<String>)[2] = "Not"
        assertEquals(listOf("This", "Is", "Not", "Immutable"), list)
    }

    @Test
    fun givenImmutableList_whenAddTried_checkExceptionThrown() {
        val list: List<String> = ImmutableList.of("I", "am", "actually", "immutable")
        ee.expect(UnsupportedOperationException::class.java)
        (list as MutableList<String>).add("Oops")
    }

    @Test
    fun givenMutableList_whenCopiedAndAddTried_checkExceptionThrown() {
        val mutableList: List<String> = listOf("I", "Am", "Definitely", "Immutable")
        (mutableList as MutableList<String>)[2] = "100% Not"
        assertEquals(listOf("I", "Am", "100% Not", "Immutable"), mutableList)
        val list: List<String> = ImmutableList.copyOf(mutableList)
        ee.expect(UnsupportedOperationException::class.java)
        (list as MutableList<String>)[2] = "Really?"
    }

    @Test
    fun givenImmutableSetBuilder_whenAddTried_checkExceptionThrown() {
        val mutableList: List<String> = ArrayList(listOf("Hello", "Baeldung"))
        val set: ImmutableSet<String> = ImmutableSet.builder<String>()
            .add("I", "am", "immutable")
            .addAll(mutableList)
            .build()
        assertEquals(setOf("Hello", "Baeldung", "I", "am", "immutable"), set)
        ee.expect(UnsupportedOperationException::class.java)
        (set as MutableSet<String>).add("Oops")
    }

    @Test
    fun givenKICLList_whenAddTried_checkExceptionThrown() {
        val list: PersistentList<String> = persistentListOf("I", "am", "immutable")
        val result = list.add("My new item")
        assertEquals(listOf("I", "am", "immutable"), list)
    }
}