package dev.vengateshm.appcore.utility

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.util.concurrent.CopyOnWriteArrayList

class SynchronizedLiveDataList<T>(data: List<T> = emptyList()) : MutableList<T> {

    private val list = CopyOnWriteArrayList(data)

    private val _liveData = MutableLiveData<List<T>>()
    val liveData: LiveData<List<T>> = _liveData

    override val size: Int
        get() = list.size

    override fun clear() {
        val cleared = list.clear()
        _liveData.postValue(list)
        return cleared
    }

    override fun get(index: Int): T {
        return list[index]
    }

    override fun isEmpty(): Boolean {
        return list.isEmpty()
    }

    override fun iterator(): MutableIterator<T> {
        return list.iterator()
    }

    override fun listIterator(): MutableListIterator<T> {
        return list.listIterator()
    }

    override fun listIterator(index: Int): MutableListIterator<T> {
        return list.listIterator(index)
    }

    override fun removeAt(index: Int): T {
        val removed = list.removeAt(index)
        _liveData.postValue(list)
        return removed
    }

    override fun subList(fromIndex: Int, toIndex: Int): MutableList<T> {
        return list.subList(fromIndex, toIndex)
    }

    override fun set(index: Int, element: T): T {
        val item = list.set(index, element)
        _liveData.postValue(listOf())
        return item
    }

    override fun retainAll(elements: Collection<T>): Boolean {
        val retained = list.retainAll(elements)
        _liveData.postValue(list)
        return retained
    }

    override fun removeAll(elements: Collection<T>): Boolean {
        val removed = list.removeAll(elements)
        _liveData.postValue(list)
        return removed
    }

    override fun remove(element: T): Boolean {
        val removed = list.remove(element)
        _liveData.postValue(list)
        return removed
    }

    override fun lastIndexOf(element: T): Int {
        return list.lastIndexOf(element)
    }

    override fun indexOf(element: T): Int {
        return list.indexOf(element)
    }

    override fun containsAll(elements: Collection<T>): Boolean {
        return list.containsAll(elements)
    }

    override fun contains(element: T): Boolean {
        return list.contains(element)
    }

    override fun addAll(elements: Collection<T>): Boolean {
        val added = list.addAll(elements)
        _liveData.postValue(list)
        return added
    }

    override fun addAll(index: Int, elements: Collection<T>): Boolean {
        val added = list.addAll(index, elements)
        _liveData.postValue(list)
        return added
    }

    override fun add(index: Int, element: T) {
        list.add(index, element)
        _liveData.postValue(list)
    }

    override fun add(element: T): Boolean {
        val added = list.add(element)
        _liveData.postValue(list)
        return added
    }

    fun clearAndAddAll(elements: Collection<T>) {
        list.clear()
        list.addAll(elements)
        _liveData.postValue(list)
    }
}