package dev.vengateshm.xml_kotlin.tab_layout_viewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import dev.vengateshm.xml_kotlin.databinding.FragmentImageGridBinding

class ImageGridFragment : Fragment() {

    private var _binding: FragmentImageGridBinding? = null

    private val binding
        get() = _binding!!

    var tabImageData: TabImageData? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentImageGridBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tabImageData?.let {
            with(binding) {
                val adapter = ImageUiDataListAdapter()
                adapter.imageUiDataList = it.imageUiDataList.toMutableList()
                val layoutManager = GridLayoutManager(requireContext(), 3)
                rvImageList.layoutManager = layoutManager
                rvImageList.adapter = adapter
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}