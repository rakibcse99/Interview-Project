package com.rakibcse99.gozayaanui.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rakibcse99.gozayaanui.databinding.RowRecommendedAllBinding
import com.rakibcse99.gozayaanui.models.RecommendedModel
import com.rakibcse99.gozayaanui.utils.SimpleCallback


class RecommendedAdapterAll(var recommendedList: MutableList<RecommendedModel>) :
    RecyclerView.Adapter<RecommendedAdapterAll.UserHolder>() {

    var clickListener: SimpleCallback<RecommendedModel>? = null

    inner class UserHolder(val binding: RowRecommendedAllBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(recommendedModel: RecommendedModel) {
           binding.titleTV.text=recommendedModel.propertyName
            binding.locationTV.text=recommendedModel.location
            Glide
                .with(binding.root)
                .load(recommendedModel.heroImage)
                .centerCrop()
                .into(binding.imageIV)

            binding.root.setOnClickListener {
                clickListener?.callback(recommendedModel)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        val binding = RowRecommendedAllBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return UserHolder(binding)
    }


    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        holder.bind(recommendedList[position])
    }

    override fun getItemCount(): Int {
        return recommendedList.size
    }

}
