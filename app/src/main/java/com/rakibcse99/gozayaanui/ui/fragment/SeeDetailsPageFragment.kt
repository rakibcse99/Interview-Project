package com.rakibcse99.gozayaanui.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.rakibcse99.gozayaanui.R
import com.rakibcse99.gozayaanui.base.BaseFragment
import com.rakibcse99.gozayaanui.base.Status
import com.rakibcse99.gozayaanui.databinding.FragmentSeeDetailsPageBinding
import com.rakibcse99.gozayaanui.models.RecommendedModel
import com.rakibcse99.gozayaanui.ui.adapter.RecommendedAdapterAll
import com.rakibcse99.gozayaanui.ui.viewModel.RecommendedViewModel
import com.rakibcse99.gozayaanui.utils.SimpleCallback
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SeeDetailsPageFragment : BaseFragment<FragmentSeeDetailsPageBinding>() {

    private val recommendedViewModel by viewModels<RecommendedViewModel>()
    override fun getViewBinding(inflater: LayoutInflater): FragmentSeeDetailsPageBinding {
        return FragmentSeeDetailsPageBinding.inflate(inflater)
    }

    override fun callInitialApi() {
        super.callInitialApi()

        recommendedViewModel.getRecommendedList()
    }

    override fun initializeData() {
        super.initializeData()
        binding.shimmerLayout.startShimmer()
    }

    override fun setListener() {
        super.setListener()
        binding.ivBack.setOnClickListener {
            findNavController().navigate(R.id.mainFragment)
        }

    }
    override fun setupObserver() {
        super.setupObserver()

        lifecycleScope.launch {
            recommendedViewModel.recommendedModelResult.collectLatest {

                when (it.status) {
                    Status.SUCCESS -> {
                        binding.shimmerLayout.stopShimmer()
                        binding.shimmerLayout.visibility = View.GONE
                        val recommendedModel = it.data
                        recyclerView(recommendedModel)
                    }

                    Status.LOADING -> {
                        binding.shimmerLayout.startShimmer()
                    }

                    Status.ERROR -> {
                        binding.shimmerLayout.stopShimmer()
                        binding.shimmerLayout.visibility = View.GONE
                        val errMsg = it.error?.message ?: ""
                        Toast.makeText(requireContext(), (errMsg), Toast.LENGTH_SHORT).show()
                    }
                }

            }
        }
    }


    private fun recyclerView(recommendedModel: MutableList<RecommendedModel>?) {
        val adapter = recommendedModel?.let { it1 -> RecommendedAdapterAll(it1) }

        binding.recylerRecommended.layoutManager = GridLayoutManager(context, 2)
        binding.recylerRecommended.itemAnimator = DefaultItemAnimator()
        binding.recylerRecommended.setHasFixedSize(true)
        adapter?.clickListener = object : SimpleCallback<RecommendedModel> {
            override fun callback(any: RecommendedModel) {
                val bundle = Bundle()
                bundle.putSerializable("recommendedModel", any)
                findNavController().navigate(R.id.detailsPageFragment, bundle)
            }
        }
        binding.recylerRecommended.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        binding.shimmerLayout.startShimmer()

    }

    override fun onPause() {
        binding.shimmerLayout.stopShimmer()
        super.onPause()
    }

    override fun backPressButtonPressed() {
        findNavController().navigate(R.id.mainFragment)
    }
}