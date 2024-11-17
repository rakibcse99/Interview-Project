package com.rakibcse99.gozayaanui.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rakibcse99.gozayaanui.databinding.FragmentDetailsPageBinding
import com.rakibcse99.gozayaanui.models.RecommendedModel
import com.rakibcse99.gozayaanui.ui.adpter.OnBoardingPagerAdapter

class DetailsPageFragment : Fragment() {
    lateinit var binding: FragmentDetailsPageBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailsPageBinding.inflate(inflater)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
                durationTV.text =" / ${it.fareUnit}"

            }


            val introPagerAdapter = OnBoardingPagerAdapter(requireContext(), recommendedModel.detailImages,)

            binding.viewPager.apply {
                adapter = introPagerAdapter
                pageMargin = 3
                clipChildren = false
                offscreenPageLimit = 3
            }
            binding.tabIndicator.setupWithViewPager(binding.viewPager)
        }
    }
}