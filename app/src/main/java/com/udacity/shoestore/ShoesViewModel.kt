package com.udacity.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoesViewModel: ViewModel() {
    private val _shoesList = MutableLiveData<List<Shoe>>()
    val shoesList: LiveData<List<Shoe>>
        get() = _shoesList


    init {
        val shoe1 = Shoe("Shoe 1", 44.0, "Company 1", "A very nice shoe 1")
        val shoe2 = Shoe("Shoe 2", 46.0, "Company 2", "A very nice shoe 2")
        val list = mutableListOf(shoe1, shoe2)
        _shoesList.value = list
    }

    fun addNewShoe(shoe: Shoe) {
        val updatedList = _shoesList.value?.toMutableList() ?: mutableListOf() // Create new list
        updatedList.add(shoe)
        _shoesList.value = updatedList
    }
}