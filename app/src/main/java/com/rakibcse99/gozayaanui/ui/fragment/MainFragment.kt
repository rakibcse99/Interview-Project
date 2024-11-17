package com.rakibcse99.gozayaanui.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.rakibcse99.gozayaanui.R
import com.rakibcse99.gozayaanui.base.Status
import com.rakibcse99.gozayaanui.databinding.FragmentMainBinding
import com.rakibcse99.gozayaanui.models.RecommendedModel
import com.rakibcse99.gozayaanui.ui.adpter.RecommendedAdapter
import com.rakibcse99.gozayaanui.ui.viewModel.RecommendedViewModel
import com.rakibcse99.gozayaanui.utils.SimpleCallback
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment() {
    lateinit var binding: FragmentMainBinding
    private val recommendedViewModel by viewModels<RecommendedViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(layoutInflater)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.seeAllTV.setOnClickListener {

            findNavController().navigate(R.id.seeDetailsPageFragment)
        }
        recommendedViewModel.getRecommendedList()
        lifecycleScope.launch {
            recommendedViewModel.recommendedModelResult.collectLatest {

                when (it.status) {
                    Status.SUCCESS -> {


                        val recommendedModel = it.data
                        recyclerView(recommendedModel)

                    }

                    Status.LOADING -> {

                    }

                    Status.ERROR -> {
                        val errMsg = it.error?.message ?: ""
                        Toast.makeText(requireContext(), (errMsg), Toast.LENGTH_SHORT).show()
                    }
                }

            }
        }
    }

    private fun recyclerView(recommendedModel: MutableList<RecommendedModel>?) {
        val adapter = recommendedModel?.let { it1 -> RecommendedAdapter(it1) }

        binding.recyclerRecommended.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerRecommended.itemAnimator = DefaultItemAnimator()
        binding.recyclerRecommended.setHasFixedSize(true)
        adapter?.clickListener = object : SimpleCallback<RecommendedModel> {
            override fun callback(any: RecommendedModel) {
                val bundle = Bundle()
                bundle.putSerializable("recommendedModel", any)
                findNavController().navigate(R.id.detailsPageFragment, bundle)
            }
        }


        binding.recyclerRecommended.adapter = adapter
    }


}