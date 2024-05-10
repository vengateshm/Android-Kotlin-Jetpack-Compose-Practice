package dev.vengateshm.xml_kotlin.tab_layout_viewpager

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import dev.vengateshm.xml_kotlin.databinding.FragmentTabLayoutViewpagerBinding

class TabLayoutViewPagerFragment : Fragment() {

    private var _binding: FragmentTabLayoutViewpagerBinding? = null

    private val binding
        get() = _binding!!

    private lateinit var imagePagerAdapter: ImagePagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTabLayoutViewpagerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tabImageDataList = listOf(
            TabImageData(
                title = "Nature",
                imageUiDataList = downloadedImageUiDataList
            ),
            TabImageData(
                title = "Spring",
                imageUiDataList = downloadedImageUiDataList
            ),
            TabImageData(
                title = "Wonder",
                imageUiDataList = downloadedImageUiDataList
            ),
            TabImageData(
                title = "Abstract",
                imageUiDataList = downloadedImageUiDataList
            ),
        )

        imagePagerAdapter = ImagePagerAdapter(requireActivity(), tabImageDataList)
        binding.viewPager.adapter = imagePagerAdapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager, true, true) { tab, position ->
            tab.text = tabImageDataList[position].title
        }.attach()

        Handler(Looper.getMainLooper()).postDelayed(
            {
                val updatedData = tabImageDataList.map {
                    it.copy(imageUiDataList = downloadedImageUiDataList + onlineImageUiDataList)
                }
                imagePagerAdapter.updateData(updatedData)
//                imagePagerAdapter.notifyDataSetChanged()
//                imagePagerAdapter.notifyItemChanged(binding.viewPager.currentItem)
                binding.viewPager.adapter = imagePagerAdapter
            },
            5000
        )
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}