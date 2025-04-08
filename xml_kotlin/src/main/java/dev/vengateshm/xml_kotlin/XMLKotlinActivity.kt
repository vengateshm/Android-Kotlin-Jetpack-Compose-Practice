package dev.vengateshm.xml_kotlin

import android.os.Build
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.viewModelScope
import dev.vengateshm.xml_kotlin.utils.HomeScreenInitializer

class XMLKotlinActivity : AppCompatActivity() {

    private val xmlKotlinActivityViewModel: XMLKotlinActivityViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_xml_kotlin)

        HomeScreenInitializer()
            .initialize(
                lifecycle,
                this,
                xmlKotlinActivityViewModel.viewModelScope,
            )

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainer, Fragment())
                .commit()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}