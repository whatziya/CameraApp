package com.example.cameraapp

import android.annotation.SuppressLint
import android.net.Uri
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnCreateContextMenuListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cameraapp.databinding.ItemPhotoBinding
import com.example.cameraapp.helper.OnItemClickListener
import com.example.cameraapp.model.entity.CamData
import java.io.File

class GalleryAdapter(
) : RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder>(){
    private val list = mutableListOf<CamData>()
    private var itemClickListener: OnItemClickListener? = null
    inner class GalleryViewHolder(private val itemBinding: ItemPhotoBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(entity: CamData) {
            with(itemBinding) {
                val uri = Uri.fromFile(File(entity.uri))
                image.setImageURI(uri)
                root.setOnClickListener {
                    itemClickListener?.onItemClick(entity.id)
                }
            }
        }

    }
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.itemClickListener = listener
    }
    @SuppressLint("NotifyDataSetChanged")
    fun load(list : List<CamData>){
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    fun add(entity: CamData){
        list.add(entity)
        notifyItemInserted(list.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPhotoBinding.inflate(inflater, parent, false)
        return GalleryViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        holder.bind(list[position])
    }

}