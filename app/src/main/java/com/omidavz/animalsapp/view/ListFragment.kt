package com.omidavz.animalsapp.view

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.omidavz.animalsapp.R
import com.omidavz.animalsapp.databinding.FragmentListBinding
import com.omidavz.animalsapp.model.AnimalList
import com.omidavz.animalsapp.viewmodel.ListViewModel

class ListFragment : Fragment() {

    lateinit var binding: FragmentListBinding
    private lateinit var viewModel: ListViewModel
    private val listAdapter = AnimalListAdapter(AnimalList())

    private val animalListDataObserver = Observer<AnimalList> { list ->
        list?.let {
            binding.animalList.visibility = View.VISIBLE
            listAdapter.updateAnimalList(it)
        }
    }
    private val allAnimalListDataObserver = Observer<AnimalList> { list ->
        list?.let {
            binding.animalList.visibility = View.VISIBLE
            listAdapter.updateAnimalList(it)
        }
    }
    private val loadingLiveDataObserver = Observer<Boolean> { isLoading ->
        binding.loadingView.visibility = if (isLoading) View.VISIBLE else View.GONE
        if (isLoading) {
            binding.listError.visibility = View.GONE
            binding.animalList.visibility = View.GONE
        }
    }
    private val errorLiveDataObserver = Observer<Boolean> { isError ->
        binding.listError.visibility = if (isError) View.VISIBLE else View.GONE
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var animalName: String

        viewModel = ViewModelProvider(this)[ListViewModel::class.java]
        viewModel.animals.observe(viewLifecycleOwner, animalListDataObserver)
        viewModel.loading.observe(viewLifecycleOwner, loadingLiveDataObserver)
        viewModel.loadError.observe(viewLifecycleOwner, errorLiveDataObserver)
        binding.animalBtn.setOnClickListener() {
            animalName = binding.animalET.text.toString().trim()
            if (!TextUtils.isEmpty(animalName)) {
                viewModel.getAnimals(animalName)
            } else {
                Toast.makeText(requireContext(), "Please Enter Animal Name", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        viewModel.allAnimals.observe(viewLifecycleOwner, allAnimalListDataObserver)

        viewModel.getAllAnimals()

        binding.apply {
            animalList.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = listAdapter
            }

            loadingView.visibility = View.VISIBLE

            refreshLayout.setOnRefreshListener {
                animalList.visibility = View.GONE
                listError.visibility = View.GONE
                loadingView.visibility = View.VISIBLE

                animalName = binding.animalET.text.toString().trim()
                viewModel.refresh(animalName)
                refreshLayout.isRefreshing = false
            }
        }
    }
}