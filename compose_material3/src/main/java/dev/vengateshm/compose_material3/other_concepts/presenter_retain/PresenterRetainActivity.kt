package dev.vengateshm.compose_material3.other_concepts.presenter_retain

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class PresenterRetainActivity : AppCompatActivity() {

    private lateinit var myPresenter: MyPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        myPresenter = lastCustomNonConfigurationInstance as? MyPresenter ?: MyPresenter()
    }

    @Deprecated("Deprecated in Java")
    override fun onRetainCustomNonConfigurationInstance(): Any? {
        return myPresenter
    }
}

class MyPresenter {
    init {
        println("MyPresenter():$this")
    }
}