package com.udacity.shoestore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.models.Shoe
import timber.log.Timber


/**
 * A simple [Fragment] subclass.
 * Use the [ShoeDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ShoeDetailFragment : Fragment() {
    lateinit var selectedShoe: Shoe
    lateinit var binding: FragmentShoeDetailBinding
    private val viewModel: ShoesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_detail, container, false)
//        viewModel = ViewModelProvider(this).get(ShoesViewModel::class.java)

        val args = ShoeDetailFragmentArgs.fromBundle(requireArguments())
        selectedShoe = args.selectedShoe
        Timber.i("The shoe in details screen is: ${selectedShoe}")

        binding.shoeNameTextField.setText(selectedShoe.name)
        binding.shoeCompanyTextField.setText(selectedShoe.company)
        binding.shoeSizeTextField.setText(selectedShoe.size.toString())
        binding.shoeDescriptionTextField.setText(selectedShoe.description)


        binding.cancelButton.setOnClickListener {
            findNavController().navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoesListFragment())
        }

        binding.saveButton.setOnClickListener {
            val newShoeName: String = binding.shoeNameTextField.text.toString()
            val newShoeDescription = binding.shoeDescriptionTextField.text.toString()
            val newShoeCompany = binding.shoeCompanyTextField.text.toString()
            val newShoeSize = binding.shoeSizeTextField.text.toString().toDouble()
            val newShoe = Shoe(newShoeName, newShoeSize, newShoeCompany, newShoeDescription)
            viewModel.addNewShoe(newShoe)
            Timber.i("The new shoes: ${viewModel.shoesList.value?.size}")

            findNavController().navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoesListFragment())
        }


        return binding.root
    }


}