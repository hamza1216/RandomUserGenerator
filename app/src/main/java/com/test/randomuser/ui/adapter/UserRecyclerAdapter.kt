package com.test.randomuser.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.randomuser.R
import com.test.randomuser.data.model.User


class UserRecyclerAdapter(private val context: Context, private var values: List<User>) : RecyclerView.Adapter<UserRecyclerAdapter.ViewHolder>() {

    private var clickListener: OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_user_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        Glide.with(context).load(item.picture)
            .circleCrop()
            .into(holder.imageView)
        holder.nameView.text = "${item.first_name} ${item.last_name}"
        holder.addressView.text = item.location
    }

    fun  getItem(position: Int): User = values[position]

    override fun getItemCount(): Int = values.size

    fun setOnItemClickListener(listener: OnClickListener) {
        this.clickListener = listener
    }

    fun update(data: List<User>) {
        values = data
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        val imageView: ImageView = view.findViewById(R.id.image)
        val nameView: TextView = view.findViewById(R.id.name)
        val addressView: TextView = view.findViewById(R.id.address)

        init {
            if (clickListener != null) {
                itemView.setOnClickListener(this)
            }
        }

        override fun onClick(v: View?) {
            if (v != null) {
                clickListener?.onItemClick(v,adapterPosition)
            }
        }
    }

    interface OnClickListener {
        fun onItemClick(v: View,position: Int)
    }

}