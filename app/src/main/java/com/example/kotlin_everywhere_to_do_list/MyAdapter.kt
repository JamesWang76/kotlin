package com.example.kotlin_everywhere_to_do_list


import android.graphics.Bitmap
import android.media.Image
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_note.view.*
import com.bumptech.glide.Glide

class MyAdapter: RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    private var noteList = mutableListOf<Holder.Datas>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_note, viewGroup, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    override fun onBindViewHolder(viewHolder: MyViewHolder, position: Int) {
        viewHolder.bind(noteList[position])
    }

    class MyViewHolder(val view: View): RecyclerView.ViewHolder(view){
        val tvNote = view.tv_note
        val tvImg = view.tv_img
        fun bind(newList: Holder.Datas){
            tvNote.text = newList.text
//            tvImg.setImageBitmap(newList.img)  // glide
            Glide.with(view).load(newList.img).into(tvImg)
            // onResume, onStart
        }
    }

    fun update(newList: MutableList<Holder.Datas>){
        noteList = newList
        notifyDataSetChanged()
    }

}