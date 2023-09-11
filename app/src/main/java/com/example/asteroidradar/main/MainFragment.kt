package com.example.asteroidradar.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.asteroidradar.R
import com.example.asteroidradar.RequiredAsteroids
import com.example.asteroidradar.databinding.FragmentMainBinding
import com.squareup.picasso.Picasso

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(requireActivity())[MainViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = FragmentMainBinding.inflate(inflater)
        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        setHasOptionsMenu(true)

        val adapter = CustomAdapter(AsteroidClickListener {
            this.findNavController()
                .navigate(MainFragmentDirections.actionMainFragmentToDetailFragment(it)) })
        binding.asteroidRecycler.adapter = adapter

        viewModel.asteroids.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }

        binding.activityMainImageOfTheDay.setOnClickListener {
            this.findNavController().navigate(MainFragmentDirections.actionMainFragmentToPictureFragment())
        }

        return binding.root
    }





    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {


        when (item.itemId) {
            R.id.show_week_menu ->
            {viewModel.changeRequirement(RequiredAsteroids.WEEK)
                return true}
            R.id.show_today_menu ->
            {viewModel.changeRequirement(RequiredAsteroids.TODAY)
                return true}
            R.id.show_all_menu ->
            {viewModel.changeRequirement(RequiredAsteroids.ALL)
                return true}
        }
        return false
    }
}
