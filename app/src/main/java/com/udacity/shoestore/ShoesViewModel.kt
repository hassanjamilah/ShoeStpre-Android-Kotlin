package com.udacity.shoestore

import android.text.format.DateUtils
import android.view.animation.Transformation
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoesViewModel: ViewModel() {
    private val _shoesList = MutableLiveData<List<Shoe>>()
    val shoesList: LiveData<List<Shoe>>
        get() = _shoesList

    private val _selectedShoe = MutableLiveData<Shoe>()
    val selectedShoe: LiveData<Shoe>
        get() = _selectedShoe

    val shoeName = MutableLiveData<String>()
    val shoeSize = MutableLiveData<String>()  // Using String to avoid conversion issues
    val shoeCompany = MutableLiveData<String>()
    val shoeDescription = MutableLiveData<String>()
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
    fun saveShoe() {
        val newShoe = Shoe(
            shoeName.value ?: "",
            shoeSize.value?.toDoubleOrNull() ?: 0.0,
            shoeCompany.value ?: "",
            shoeDescription.value ?: ""
        )
        addNewShoe(newShoe)
    }
    fun setSelectedShoe(shoe: Shoe) {
        _selectedShoe.value = shoe
        shoeName.value = shoe.name
        shoeSize.value = shoe.size.toString()
        shoeCompany.value = shoe.company
        shoeDescription.value = shoe.description
    }
}