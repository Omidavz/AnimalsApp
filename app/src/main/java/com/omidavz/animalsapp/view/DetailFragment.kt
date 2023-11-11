package com.omidavz.animalsapp.view

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.omidavz.animalsapp.R
import com.omidavz.animalsapp.databinding.FragmentDetailBinding
import com.omidavz.animalsapp.model.Animal

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    var animal: Animal? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            binding.animal = DetailFragmentArgs.fromBundle(it).animal
        }
    }
}


