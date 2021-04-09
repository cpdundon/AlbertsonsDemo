package com.example.albertsonsdemo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.albertsonsdemo.databinding.LongFormElementBinding
import com.example.albertsonsdemo.model.AcronymItem
import com.example.albertsonsdemo.model.Lf

class AcronymRVAdapter(private val acronymList: List<AcronymItem>) : RecyclerView.Adapter<AcronymRVAdapter.AcronymViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AcronymViewHolder {

        val binding: LongFormElementBinding = LongFormElementBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
        )
        return AcronymViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return if (acronymList.isEmpty()) {
            0
        } else {
            acronymList[0].lfs.size
        }
    }

    override fun onBindViewHolder(holder: AcronymViewHolder, position: Int) {
        holder.setLongForm(acronymList[0].lfs, position)
    }

    class AcronymViewHolder(private val binding: LongFormElementBinding) :
            RecyclerView.ViewHolder(binding.root) {

        fun setLongForm(lfsList: List<Lf>, position: Int) {
            if (lfsList.isNotEmpty()) {
                lfsList[position].also { it ->
                    binding.tvLongForm.text = it.lf
                }
            }
        }
    }
}

