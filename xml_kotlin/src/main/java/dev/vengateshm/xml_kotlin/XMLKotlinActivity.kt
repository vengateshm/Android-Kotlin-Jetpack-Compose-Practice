package dev.vengateshm.xml_kotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import dev.vengateshm.xml_kotlin.haptics.HapticsFragment
import dev.vengateshm.xml_kotlin.tab_layout_viewpager.TabLayoutViewPagerFragment

class XMLKotlinActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_xml_kotlin)

        /*if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                replace<TabLayoutViewPagerFragment>(R.id.fragmentContainer)
            }
        }*/
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace<HapticsFragment>(R.id.fragmentContainer)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}