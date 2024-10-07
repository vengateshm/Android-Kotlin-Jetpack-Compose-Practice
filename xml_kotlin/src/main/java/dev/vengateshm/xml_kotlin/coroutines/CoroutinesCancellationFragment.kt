package dev.vengateshm.xml_kotlin.coroutines

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import dev.vengateshm.xml_kotlin.databinding.FragmentCoroutinesCancellationBinding
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CoroutinesCancellationFragment : Fragment() {

    private var _binding: FragmentCoroutinesCancellationBinding? = null

    private val binding get() = _binding!!

    private val coroutineScope = CoroutineScope(Dispatchers.Main.immediate)
    private val benchmarkUseCase = BenchmarkUseCase()
    private var isWithoutTryCatch = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCoroutinesCancellationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val benchmarkDurationInSeconds = 5

        binding.btnStart.setOnClickListener {
            coroutineScope.launch {
                updateRemainingTime(benchmarkDurationInSeconds)
            }

//            withoutTryCatch(benchmarkDurationInSeconds)
            withTryCatch(benchmarkDurationInSeconds)
        }
    }

    private fun withoutTryCatch(benchmarkDurationInSeconds: Int) {
        isWithoutTryCatch = true
        coroutineScope.launch {
            binding.btnStart.isEnabled = false
            val iterations =
                benchmarkUseCase.executeBenchmarkWithContext(benchmarkDurationInSeconds)
            logThreadInfo("benchmarked iterations: $iterations")
            Toast.makeText(requireContext(), "$iterations", Toast.LENGTH_SHORT).show()
            binding.btnStart.isEnabled = true
        }
    }

    private fun withTryCatch(benchmarkDurationInSeconds: Int) {
        isWithoutTryCatch = false
        coroutineScope.launch {
            try {
                binding.btnStart.isEnabled = false
                val iterations =
                    benchmarkUseCase.executeBenchmarkWithContext(benchmarkDurationInSeconds)
                logThreadInfo("benchmarked iterations: $iterations")
                Toast.makeText(requireContext(), "$iterations", Toast.LENGTH_SHORT).show()
                binding.btnStart.isEnabled = true
            } catch (e: CancellationException) {
                logThreadInfo("coroutine cancelled")
                binding.btnStart.isEnabled = true
                binding.tvTime.text = "Done!"
                //Toast.makeText(requireContext(), "Cancelled", Toast.LENGTH_SHORT).show()
                if (lifecycle.currentState.isAtLeast(Lifecycle.State.CREATED)) {
                    Toast.makeText(requireContext(), "Cancelled", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onStop() {
        logThreadInfo("onStop()")
        super.onStop()
        coroutineScope.coroutineContext.cancelChildren()
        if (isWithoutTryCatch) {
            binding.btnStart.isEnabled = true
            binding.tvTime.text = "Done!"
        }
    }

    private suspend fun updateRemainingTime(remainingTime: Int) {
        for (time in remainingTime downTo 0) {
            if (time > 0) {
                logThreadInfo("updateRemainingTime: $time seconds")
                binding.tvTime.text = "$time seconds remaining"
                delay(1000L)
            } else {
                binding.tvTime.text = "Done!"
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}