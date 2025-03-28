package dev.vengateshm.xml_kotlin

import android.os.Build
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.viewModelScope
import dev.vengateshm.xml_kotlin.utils.HomeScreenInitializer

class XMLKotlinActivity : AppCompatActivity() {

    private val xmlKotlinActivityViewModel: XMLKotlinActivityViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_xml_kotlin)

        /*if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                replace<TabLayoutViewPagerFragment>(R.id.fragmentContainer)
            }
        }*/

        HomeScreenInitializer()
            .initialize(
                lifecycle,
                this,
                xmlKotlinActivityViewModel.viewModelScope,
            )
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}