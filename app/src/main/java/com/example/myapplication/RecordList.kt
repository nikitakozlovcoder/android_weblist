package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecordListAdapter(private val records: ArrayList<Record>, private val listener: (record: Record) -> Unit) : RecyclerView.Adapter<RecordListAdapter.ViewHolder>() {

    inner class ViewHolder(val view: View): RecyclerView.ViewHolder(view) {

        fun bind(record: Record, callback: (record: Record)->Unit){
            view.setOnClickListener{callback(record)}
            val name: TextView = view.findViewById(R.id.item_name)
            val url: TextView = view.findViewById(R.id.item_url)
            name.text = record.Name
            url.text = record.Url
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordListAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = records.size

    override fun onBindViewHolder(holder: RecordListAdapter.ViewHolder, position: Int) {
            holder.bind(records[position], listener)
    }

}