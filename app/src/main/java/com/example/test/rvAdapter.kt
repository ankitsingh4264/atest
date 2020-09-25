package com.example.test

import android.content.Context
import android.provider.ContactsContract.CommonDataKinds.Note
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.test.model.thought
import kotlinx.android.synthetic.main.item_rv.view.*


class rvAdapter(val context: Context, var list: List<thought>) : RecyclerView.Adapter<rvAdapter.viewholder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        val view=LayoutInflater.from(context).inflate(R.layout.item_rv, parent, false)
        return viewholder(view)
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        holder.bind(list[position], position)
    }

    override fun getItemCount(): Int {
        return list.size
    }


    fun setData(newData: List<thought>) {
        this.list = newData
        notifyDataSetChanged()
    }

    fun getNoteAt(position: Int): thought? {
        return list.get(position)
    }


    inner class  viewholder(itemView: View) : RecyclerView.ViewHolder(itemView){


        fun bind(currthought: thought, position: Int) {


            itemView.rvtitle.text=currthought.title
            itemView.rvdesc.text=currthought.description

        }


    }

}