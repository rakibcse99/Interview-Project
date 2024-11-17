package com.rakibcse99.gozayaanui.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rakibcse99.gozayaanui.databinding.RowRecommendedBinding
import com.rakibcse99.gozayaanui.models.RecommendedModel
import com.rakibcse99.gozayaanui.utils.SimpleCallback


class RecommendedAdapter(var recommendedList: MutableList<RecommendedModel>) :
    RecyclerView.Adapter<RecommendedAdapter.UserHolder>() {

    var clickListener: SimpleCallback<RecommendedModel>? = null

    inner class UserHolder(val binding: RowRecommendedBinding) : RecyclerView.ViewHolder(binding.root) {
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
        val binding = RowRecommendedBinding
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
