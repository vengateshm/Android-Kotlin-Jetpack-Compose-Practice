package dev.vengateshm.compose_material3.apps.todo_list

import android.content.Context
import androidx.compose.runtime.snapshots.SnapshotStateList
import okio.FileNotFoundException
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

const val FILE_NAME = "todolist.dat"

fun writeData(items: SnapshotStateList<String>, context: Context) {
  println("TODO List App:Writing data")
  val fos = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE)
  val oos = ObjectOutputStream(fos)
  val itemList = ArrayList<String>()
  itemList.addAll(items)
  oos.writeObject(itemList)
  oos.close()
}

fun readData(context: Context): SnapshotStateList<String> {
  println("TODO List App:Reading data")
  var itemList: ArrayList<String>
  try {
    val fis = context.openFileInput(FILE_NAME)
    val ois = ObjectInputStream(fis)
    itemList = ois.readObject() as ArrayList<String>
  } catch (e: FileNotFoundException) {
    itemList = ArrayList()
  }

  val items = SnapshotStateList<String>()
  items.addAll(itemList)
  return items
}