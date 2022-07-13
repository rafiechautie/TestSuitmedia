package com.example.testsuitmedia.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testsuitmedia.data.response.DataItem
import com.example.testsuitmedia.databinding.ItemRowUserBinding
import com.example.testsuitmedia.ui.SecondScreenActivity

class UsersAdapter: PagingDataAdapter<DataItem, UsersAdapter.MyViewHolder>(DIFF_CALLBACK) {

    class MyViewHolder(private val binding: ItemRowUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DataItem) {
            val name = "${data.firstName} ${data.lastName}"
            binding.tvUser.text = name
            binding.tvEmail.text = data.email
            Glide.with(itemView)
                .load(data.avatar)
                .circleCrop()
                .into(binding.imgUser)

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, SecondScreenActivity::class.java)
                intent.putExtra(SecondScreenActivity.USER, name)
                itemView.context.startActivity(intent)
            }
        }
    }


    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataItem>() {
            override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null) {
            holder.bind(data)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemRowUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }
}