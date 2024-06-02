package com.example.cameraapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.cameraapp.model.entity.CamData
import com.example.cameraapp.model.repository.GalleryRepository

class GalleryViewModel(
    private val galleryRepository: GalleryRepository
) : ViewModel() {
    fun getAll(): List<CamData> {
        val list = mutableListOf<CamData>()
        galleryRepository.getAll().value?.forEach {
            list.add(it)
        }
        return list
    }

    fun getObs(): LiveData<List<CamData>> {
        return galleryRepository.getAll()
    }

    fun delete(id: Int) {
        galleryRepository.delete(id)
    }

    fun add(entity: CamData) {
        galleryRepository.add(entity)
    }
}