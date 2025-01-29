package dev.vengateshm.xml_kotlin.ui.multiple_view_type_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dev.vengateshm.xml_kotlin.databinding.FragmentItineraryBinding
import dev.vengateshm.xml_kotlin.ui.multiple_view_type_list.db.ItineraryDatabase
import dev.vengateshm.xml_kotlin.ui.multiple_view_type_list.repository.ItineraryRepositoryImpl

class ItineraryFragment : Fragment() {

    private var _binding: FragmentItineraryBinding? = null
    private val binding get() = _binding!!

    private val itineraryAdapter by lazy {
        ItineraryAdapter(clickListener = viewModel::onItemClicked)
    }

    private val viewModel: ItineraryViewModel by viewModels(
        factoryProducer = {
            ItineraryViewModelFactory(
                ItineraryRepositoryImpl(
                    ItineraryDatabase.getDatabase(
                        requireContext(),
                    ).itineraryDao(),
                ),
            )
        },
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentItineraryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            recyclerViewItinerary.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = itineraryAdapter
                addItemDecoration(OffsetDecoration(itineraryAdapter))
            }
        }

        viewModel.itineraryList.observe(
            viewLifecycleOwner,
            Observer { itineraryItems ->
                itineraryAdapter.setItineraryItems(itineraryItems)
            },
        )
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}