package dev.vengateshm.xml_kotlin.toolbar_menu

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.forEach
import dev.vengateshm.xml_kotlin.R

// No actionViewClass - Click happens
// Set actionViewClass / actionLayout - Need to set click listener

class ToolbarMenuActivity : AppCompatActivity() {

  private val viewModel: ToolbarMenuViewModel by viewModels()
  private var menu: Menu? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_toolbar_menu)

    val toolbar = findViewById<Toolbar>(R.id.toolbar)
    setSupportActionBar(toolbar)
    supportActionBar?.title = "Toolbar Menu"

    observeIconChanges()
    viewModel.init()
  }

  private fun observeIconChanges() {
    viewModel.action1Data.observe(this) { data ->
      menu?.let { menu ->
        val action1: MenuItem? = menu.findItem(R.id.action_1)
        action1?.let {
          setUpAction1Data(action1.actionView, data.first, data.second)
        }
      }
    }
  }

  private fun setUpAction1Data(view: View?, image: String?, initials: String?) {
    view?.setOnClickListener {
      Toast.makeText(this, "Action 1 clicked", Toast.LENGTH_SHORT).show()
    }
    val imageView = view?.findViewById<ImageView>(R.id.menu_info_image)
    val textView = view?.findViewById<TextView>(R.id.menu_info_text)
    if (!image.isNullOrEmpty()) {
      imageView?.apply {
        setImageBitmap(decodeBase64Image(image))
        visibility = View.VISIBLE
      }
    }
//    if (!initials.isNullOrEmpty()) {
//      textView?.apply {
//        view.setBackgroundResource(R.drawable.initials_bg)
//        text = initials
//        visibility = View.VISIBLE
//      }
//    }
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.toolbar_menu_custom_view, menu)
    this.menu = menu

    menu?.forEach {
      if (it.itemId == R.id.action_1) {
        it.isVisible = true
      } else if (it.itemId == R.id.action_2) {
        it.isVisible = true
      }
    }

//    // Configure settings ImageButton
//    val settingsItem = menu?.findItem(R.id.action_settings)
//    val settingsButton = settingsItem?.actionView as? ImageButton
//    settingsButton?.apply {
//      setImageResource(android.R.drawable.ic_menu_preferences)
//      background = getDrawable(android.R.attr.selectableItemBackgroundBorderless)
//      contentDescription = "Settings"
//      setPadding(32, 32, 32, 32)
//      setOnClickListener {
//        Toast.makeText(this@ToolbarMenuActivity, "Settings clicked", Toast.LENGTH_SHORT).show()
//      }
//    }
//
//    // Configure info ImageView
//    val infoItem = menu?.findItem(R.id.action_info)
//    val infoImage = infoItem?.actionView as? ImageView
//    infoImage?.apply {
//      setImageResource(android.R.drawable.ic_menu_info_details)
//      contentDescription = "Info"
//      setPadding(32, 32, 32, 32)
//    }

    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    return when (item.itemId) {
      R.id.action_1 -> {
        Toast.makeText(this, "${item.title} clicked}", Toast.LENGTH_SHORT).show()
        true
      }

      R.id.action_2 -> {
        Toast.makeText(this, "${item.title} clicked}", Toast.LENGTH_SHORT).show()
        true
      }

      else -> super.onOptionsItemSelected(item)
    }
  }

  fun decodeBase64Image(base64Str: String): Bitmap? {
    val byteArray = if (base64Str.length > 500)
      Base64.decode(base64Str, Base64.DEFAULT)
    else ByteArray(1024)
    return try {
      BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    } catch (e: Exception) {
      e.printStackTrace()
      null
    }
  }
}
