package dev.vengateshm.xml_kotlin.multi_module

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import dev.vengateshm.appcore.comm.CommViewModel
import dev.vengateshm.xml_kotlin.R
import kotlinx.coroutines.launch

class MultiModuleMainActivity : AppCompatActivity() {

    private val commViewModel: CommViewModel by viewModels()
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multi_module_main)
        setUpNavController()
        configureCommEventHandler(this)
    }

    private fun setUpNavController() {
        val host =
            supportFragmentManager.findFragmentById(R.id.multi_module_main_nav_host_fragment_container) as NavHostFragment?
                ?: return
        navController = host.navController
        //Appbar
        //Actionbar
        //Bottom nav
        navController.addOnDestinationChangedListener { _, _, _ ->
            //Hide keyboard
            //Close drawer
        }
    }

    private fun configureCommEventHandler(context: Context) {
        commViewModel.commEvent.observe(this) { event ->
            commViewModel.viewModelScope.launch {
                MainFacade(context).processEvent(navController, event)?.run {
                    commViewModel.postCommEventResult(this)
                }
            }
        }
    }

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, MultiModuleMainActivity::class.java)
            context.startActivity(intent)
        }
    }
}