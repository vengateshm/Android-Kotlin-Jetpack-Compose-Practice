package dev.vengateshm.xml_kotlin.custom_views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dev.vengateshm.xml_kotlin.R

class TimerViewFragment : Fragment() {

    private lateinit var timerView: TimerView
    private lateinit var timerViewCoroutines: TimerViewCoroutines

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_timer_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        timerView = view.findViewById(R.id.timerView)
//        timerView.setTimer(25)
        timerViewCoroutines = view.findViewById(R.id.timerView)
        timerViewCoroutines.setTimer(25)
    }
}