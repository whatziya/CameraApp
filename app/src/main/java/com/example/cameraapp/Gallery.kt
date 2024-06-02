package com.example.cameraapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.cameraapp.databinding.ActivityGalleryBinding
import com.example.cameraapp.helper.OnItemClickListener
import com.example.cameraapp.model.entity.CamData
import com.example.cameraapp.model.repository.GalleryRepository
import com.example.cameraapp.viewmodel.GalleryViewModel
import com.example.cameraapp.viewmodel.GalleryViewModelProviderFactory

class Gallery : AppCompatActivity() {
    private lateinit var binding: ActivityGalleryBinding
    private lateinit var galleryAdapter: GalleryAdapter
    private lateinit var galleryViewModel: GalleryViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGalleryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        galleryAdapter = GalleryAdapter()
        galleryAdapter.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(itemId: Int) {
                galleryViewModel.delete(itemId)
            }
        })
        onViewCreated()
    }

    private fun onViewCreated(){
        val dao = App.instance.appDao()
        val repo = GalleryRepository(dao)
        val factory = GalleryViewModelProviderFactory(repo)
        galleryViewModel = ViewModelProvider(this, factory)[GalleryViewModel::class.java]
        val list1 = mutableListOf<CamData>()
        galleryViewModel.getAll().forEach {
            list1.add(it)
        }
        galleryAdapter.load(list1)
        galleryViewModel.getObs().observe(this) { it ->
            val list1 = mutableListOf<CamData>()
            it.forEach {
                list1.add(it)
            }
            galleryAdapter.load(list1)
        }
        with(binding) {
            rv.adapter = galleryAdapter
        }
    }
}