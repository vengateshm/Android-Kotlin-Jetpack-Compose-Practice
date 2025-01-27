package dev.vengateshm.xml_kotlin.tab_layout_viewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.android.material.tabs.TabLayoutMediator
import dev.vengateshm.xml_kotlin.databinding.FragmentPagedScreenBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PagedScreenFragment : Fragment() {
    private var _binding: FragmentPagedScreenBinding? = null

    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentPagedScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pages = listOf("Home", "Profile", "Settings", "About")
        with(binding) {
            val adapter = PagedScreenAdapter(pages)
            viewPager.adapter = adapter
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = pages[position]
            }.attach()
            viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
                val descriptions = fetchDescriptions()
                adapter.updateData1(descriptions)
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private suspend fun fetchDescriptions(): List<String> = withContext(Dispatchers.IO) {
        delay(5000)
        listOf(
            "Welcome to the Home tab! This is where your journey begins.",
            "This is your Profile tab. Manage your account details here.",
            "Settings tab: Configure your app the way you like it.",
            "About tab: Learn more about this app and its purpose.",
        )
    }
}