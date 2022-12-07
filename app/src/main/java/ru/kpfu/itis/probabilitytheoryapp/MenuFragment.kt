package ru.kpfu.itis.probabilitytheoryapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import ru.kpfu.itis.probabilitytheoryapp.databinding.FragmentMenuBinding

class MenuFragment : Fragment() {
    lateinit var binding: FragmentMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMenuBinding.inflate(layoutInflater, container, false)

        binding.btnCombinations.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_combinationFragment)
        }
        binding.btnPermutations.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_permutationFragment)
        }
        binding.btnPlacements.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_placementsFragment)
        }
        return binding.root
    }


}
