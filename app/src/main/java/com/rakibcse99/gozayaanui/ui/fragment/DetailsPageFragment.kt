package com.rakibcse99.gozayaanui.ui.fragment

import android.view.LayoutInflater
import androidx.navigation.fragment.findNavController
import com.rakibcse99.gozayaanui.R
import com.rakibcse99.gozayaanui.base.BaseFragment
import com.rakibcse99.gozayaanui.databinding.FragmentDetailsPageBinding
import com.rakibcse99.gozayaanui.models.RecommendedModel
import com.rakibcse99.gozayaanui.ui.adapter.OnBoardingPagerAdapter

class DetailsPageFragment : BaseFragment<FragmentDetailsPageBinding>() {
    override fun getViewBinding(inflater: LayoutInflater): FragmentDetailsPageBinding {
        return  FragmentDetailsPageBinding.inflate(inflater)
    }

    override fun initializeData() {
        super.initializeData()
        val recommendedModel = arguments?.getSerializable("recommendedModel") as? RecommendedModel

        // Use the model data
        recommendedModel?.let {
            binding.apply {
                titleTV.text = it.propertyName
                locationTV.text = it.location
                retTV.text = it.rating.toString()
                aboutBodyTV.text = it.description
                retHotelTV.text = it.currency

                var currencyIcon = ""
                if (it.currency == "USD") {
                    currencyIcon = "$"
                } else {
                    currencyIcon = "৳"
                }

                retHotelTV.text = "${currencyIcon} ${it.fare}"
                durationTV.text = " / ${it.fareUnit}"

            }

        }

        // adapter Initialize
        val introPagerAdapter = recommendedModel?.let { OnBoardingPagerAdapter(requireContext(), it.detailImages,) }

        binding.viewPager.apply {
            adapter = introPagerAdapter
            pageMargin = 3
            clipChildren = false
            offscreenPageLimit = 3
        }
        binding.tabIndicator.setupWithViewPager(binding.viewPager)
    }
    override fun setListener() {
        super.setListener()
        binding.ivBack.setOnClickListener {
            findNavController().navigate(R.id.mainFragment)
        }
    }

    override fun backPressButtonPressed() {
        super.backPressButtonPressed()
            super.setListener()
            findNavController().navigate(R.id.mainFragment)

    }
}