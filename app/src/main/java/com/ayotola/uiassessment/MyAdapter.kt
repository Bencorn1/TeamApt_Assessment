package com.ayotola.uiassessment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var data = emptyList<CardItems>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return MyHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        // Get current position of item in recyclerview to bind data and assign values from list
        val myHolder = holder as MyHolder
        val cardItems = data[position]

        holder.imgIcon.setImageResource(cardItems.image)
        holder.senderText.text = cardItems.sender
        holder.reasonText.text = cardItems.reasons
        holder.money.text = "+${cardItems.money}€"
    }

    override fun getItemCount(): Int {
      return  data.size
    }

    inner class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgIcon: ImageView = itemView.findViewById(R.id.profile_image)
        val  senderText: TextView = itemView.findViewById(R.id.sender)
        val reasonText: TextView = itemView.findViewById(R.id.reason)
        val money: TextView = itemView.findViewById(R.id.fee)
    }

    fun addMoreList(cardItems: List<CardItems>) {
        data = cardItems
    }
}