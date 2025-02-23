package com.udacity.shoestore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.children
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.databinding.FragmentShoesListBinding
import com.udacity.shoestore.models.Shoe
import timber.log.Timber

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ShoesListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ShoesListFragment : Fragment() {

    val viewModel: ShoesViewModel by activityViewModels()
    lateinit var selectedShoe: Shoe

    lateinit var binding: FragmentShoesListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoes_list, container, false)

        binding.shoeDetailButton.isEnabled = false
        binding.shoeDetailButton.alpha = 0.50f

        binding.shoeDetailButton.setOnClickListener {
            if (selectedShoe == null) {

            }
            it.findNavController().navigate(ShoesListFragmentDirections.actionShoesListFragmentToShoeDetailFragment(selectedShoe))
        }
        setHasOptionsMenu(true)
        Timber.i("The shoes list are: ${viewModel.shoesList.value}")

        viewModel.shoesList.observe(viewLifecycleOwner, Observer { shoes ->
            displayData(shoes)
        })




        return binding.root
    }

    private fun displayData(data: List<Shoe>) {
//        val data: MutableList<Shoe> = viewModel.shoesList.value ?: mutableListOf()
        data.forEachIndexed { index, shoe ->
            Timber.i("The item is: ${shoe}")
            val textView = TextView(context)
            textView.tag = index
            textView.text = shoe.name
            textView.setOnClickListener {
                binding.shoesMainView.children.forEach {
                    it.setBackgroundColor(resources.getColor(R.color.design_default_color_on_primary))
                }
                textView.setBackgroundColor(resources.getColor(R.color.colorAccent))
                selectedShoe = shoe

                if (::selectedShoe.isInitialized) {
                    binding.shoeDetailButton.isEnabled = true
                    binding.shoeDetailButton.alpha = 1.0f
                }

            }
            binding.shoesMainView.addView(textView)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.shoes_list_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.login -> {
                findNavController().navigate(ShoesListFragmentDirections.actionShoesListFragmentToLogin())
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}