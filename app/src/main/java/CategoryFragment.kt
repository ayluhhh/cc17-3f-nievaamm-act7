// CategoryFragment.kt
package com.example.mycityapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mycityapp.databinding.FragmentCategoryBinding
import com.example.mycityapp.data.Category
import com.example.mycityapp.data.Recommendation


class CategoryFragment : Fragment() {

    private var _binding: FragmentCategoryBinding? = null
    private val binding get() = _binding!!
    private val args: CategoryFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recommendations = getRecommendationsForCategory(args.categoryName)

        val adapter = MainAdapter(recommendations) { recommendation ->
            val action = CategoryFragmentDirections.actionCategoryFragmentToDetailsFragment(recommendation.name)
            findNavController().navigate(action)
        }

        binding.recommendationRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.recommendationRecyclerView.adapter = adapter
    }

    private fun getRecommendationsForCategory(category: String): List<Recommendation> {
        return when (category) {
            "Museums and Art Galleries" -> listOf(
                Recommendation("BenCab"), Recommendation("Baguio Museum"), /* add more */
            )
            "Coffee Shops" -> listOf(
                Recommendation("Hoka Brew"), Recommendation("Foam Coffee"), /* add more */
            )
            "Restaurants" -> listOf(
                Recommendation("Grumpy Joe"), Recommendation("Lemon and Olives"), /* add more */
            )
            "Parks and Nature Attractions" -> listOf(
                Recommendation("Burnham Park"), Recommendation("Sunshine Park"), /* add more */
            )
            else -> emptyList()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
