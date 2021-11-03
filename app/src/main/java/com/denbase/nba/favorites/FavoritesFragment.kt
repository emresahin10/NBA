package com.denbase.nba.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.denbase.nba.R
import com.denbase.nba.databinding.FragmentFavoritesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment(R.layout.fragment_favorites) {
    private lateinit var binding: FragmentFavoritesBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFavoritesBinding.bind(view)
    }
}