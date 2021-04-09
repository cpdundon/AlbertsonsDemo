package com.example.albertsonsdemo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.albertsonsdemo.databinding.LongFormElementBinding
import com.example.albertsonsdemo.model.AcronymMeaning

class AcronymRVAdapter : RecyclerView.Adapter<AcronymRVAdapter.AcronymViewHolder>() {

    private val acronymList: MutableList<AcronymMeaning> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            LongFormElementBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
            ).let { AcronymViewHolder(it) }

    override fun getItemCount() = acronymList.size

    override fun onBindViewHolder(holder: AcronymViewHolder, position: Int) = with(holder) {
        setMeaning(acronymList[position])
    }

    fun update(newMeanings: List<AcronymMeaning>) = acronymList.run {
        clear();addAll(newMeanings);notifyDataSetChanged()
    }

    class AcronymViewHolder(
            private val binding: LongFormElementBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun setMeaning(meaning: AcronymMeaning) = with(binding) {
            tvLongForm.text = meaning.meaning
        }
    }
}
