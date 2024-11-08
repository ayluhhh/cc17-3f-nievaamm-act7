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
                Recommendation("BenCab"),
                Recommendation("BenCab"),
                Recommendation("Baguio Museum"),
                Recommendation("Tam-awan Village"),
                Recommendation("Museo Kordilyera"),
                Recommendation("Galleria de Potenciana by Arca’s Yard"),

            )
            "Coffee Shops" -> listOf(
                Recommendation("Hoka Brew"),
                Recommendation("Hoka Brew"),
                Recommendation("Foam Coffee"),
                Recommendation("Hatch Coffee"),
                Recommendation("Seollem Cafe"),
                Recommendation("Cafe by the Ruins"),

            )
            "Restaurants" -> listOf(
                Recommendation("Grumpy Joe"),
                Recommendation("Grumpy Joe"),
                Recommendation("Lemon and Olives"),
                Recommendation("Good Taste"),
                Recommendation("Canto"),
                Recommendation("Amare la Cucina"),
                Recommendation("House of Yogurt Lover (H.O.Y)"),

            )
            "Parks and Nature Attractions" -> listOf(
                Recommendation("Burnham Park"),
                Recommendation("Burnham Park"),
                Recommendation("Sunshine Park"),
                Recommendation("Maryknoll Ecological Sanctuary"),
                Recommendation("Botanical Garden"),
            )
            else -> emptyList()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
