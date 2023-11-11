package com.omidavz.animalsapp.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.omidavz.animalsapp.R
import com.omidavz.animalsapp.databinding.ItemAnimalBinding
import com.omidavz.animalsapp.model.Animal
import com.omidavz.animalsapp.model.AnimalList

class AnimalListAdapter(private val animalList: AnimalList) :
    RecyclerView.Adapter<AnimalListAdapter.AnimalViewHolder>(), AnimalClickListener {

    lateinit var binding: ItemAnimalBinding

    class AnimalViewHolder(private val binding: ItemAnimalBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(animal: Animal) {
            binding.animal = animal
        }
    }

    fun updateAnimalList(newAnimalList: List<Animal>) {
        animalList.clear()
        animalList.addAll(newAnimalList)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_animal,
            parent,
            false
        )

        return AnimalViewHolder(binding)

    }

    override fun getItemCount() = animalList.size

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        var animal = animalList[position]
        holder.bind(animal)
        binding.listener = this

    }

    override fun onClick(v: View) {
        for (animal in animalList) {
            if (v.tag == animal.name) {
                val action = ListFragmentDirections.actionDetail(animal)
                v.findNavController().navigate(action)
            }
        }

    }
}