package dev.vengateshm.xml_kotlin.custom_views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import dev.vengateshm.xml_kotlin.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PulsatingInfoViewFragment : Fragment() {

    private lateinit var pulsatingInfoView: PulsatingInfoView
    private var timerJob: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_pulsating_info_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val textList = listOf(
            "Boarding starts",
            "Gate open",
            "Gate closed",
            "Boarding completed",
            "On time",
            "Delayed",
        )
        val timeList = listOf(
            "15 mins",
            "5 mins",
            "20 mins",
            "30 mins",
            "45 mins",
        )

        pulsatingInfoView = view.findViewById(R.id.pulsatingInfoView)
        pulsatingInfoView.updateText("Boarding starts", "15 mins")
        timerJob = viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
            while (true) {
                pulsatingInfoView.updateText(textList.random(), timeList.random())
                delay(5000)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        timerJob?.cancel()
        timerJob = null
    }
}