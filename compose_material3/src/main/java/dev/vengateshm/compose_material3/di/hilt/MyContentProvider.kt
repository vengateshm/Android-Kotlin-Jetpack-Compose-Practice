package dev.vengateshm.compose_material3.di.hilt

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import android.util.Log
import dagger.hilt.android.EntryPointAccessors
import dev.vengateshm.compose_material3.di.hilt.component.DaggerParentComponent
import javax.inject.Inject

class MyContentProvider : ContentProvider() {

    private val tag = "MyContentProvider"

    @Inject
    lateinit var engine: Engine

    companion object {
        private const val TABLE = "table"
        private const val AUTHORITY = "dev.vengateshm.content"
        val CONTENT_URI: Uri = Uri.parse("content://$AUTHORITY/$TABLE")
    }

    override fun onCreate(): Boolean {
        DaggerParentComponent.create()
            .childComponentFactory()
            .create()
            .inject(this)

        Log.d(tag, "Engine: ${engine.name}")

        val entryPoint =
            EntryPointAccessors.fromApplication(context!!, MyContentProviderEntryPoint::class.java)
        Log.d(tag, "Engine using entry point : ${entryPoint.getEngine().name}")

        return true
    }

    override fun query(
        p0: Uri,
        p1: Array<out String>?,
        p2: String?,
        p3: Array<out String>?,
        p4: String?,
    ): Cursor? {
        return null
    }

    override fun getType(p0: Uri): String {
        return ""
    }

    override fun insert(p0: Uri, p1: ContentValues?): Uri? {
        return Uri.EMPTY
    }

    override fun delete(p0: Uri, p1: String?, p2: Array<out String>?): Int {
        return 0
    }

    override fun update(p0: Uri, p1: ContentValues?, p2: String?, p3: Array<out String>?): Int {
        return 0
    }
}