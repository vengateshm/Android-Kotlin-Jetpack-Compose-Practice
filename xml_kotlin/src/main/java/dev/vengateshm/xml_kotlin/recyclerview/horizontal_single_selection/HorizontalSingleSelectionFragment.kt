package dev.vengateshm.xml_kotlin.recyclerview.horizontal_single_selection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.vengateshm.xml_kotlin.R

class HorizontalSingleSelectionFragment : Fragment() {

    private lateinit var adapter: HorizontalSingleSelectionAdapter
    private val viewModel: HorizontalSingleSelectionViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(
            R.layout.fragment_horizontal_single_selection_list,
            container,
            false,
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(view.findViewById<RecyclerView>(R.id.recyclerView)) {
            this@HorizontalSingleSelectionFragment.adapter = HorizontalSingleSelectionAdapter {
                viewModel.onItemClicked(it)
            }
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
//            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true)
            this.adapter = this@HorizontalSingleSelectionFragment.adapter
//            val animator = itemAnimator as? DefaultItemAnimator
//            animator?.supportsChangeAnimations = false
            itemAnimator = null
        }

        viewModel.items.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }
}