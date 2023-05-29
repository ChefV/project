package com.example.news.presentation.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.news.data.model.NewsData
import com.example.news.databinding.FragmentNewsBinding

class NewsFragment : Fragment() {
    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: NewsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getNews()

        viewModel.newsLiveData.observe(viewLifecycleOwner) { data ->
            binding.progressBar.visibility = View.GONE
            if (data == null) {
                showError()
            } else {
                displayNews(data)
            }
        }
    }

    private fun showError() {
        binding.errorLayout.visibility = View.VISIBLE
        binding.tryAgainButton.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            viewModel.getNews()
        }
    }

    private fun displayNews(newsData: NewsData) {
        binding.errorLayout.visibility = View.GONE
        val layoutManager = LinearLayoutManager(requireContext())
        val dividerItemDecoration = DividerItemDecoration(requireContext(), layoutManager.orientation)
        binding.newsRecyclerView.addItemDecoration(dividerItemDecoration)
        binding.newsRecyclerView.layoutManager = layoutManager
        binding.newsRecyclerView.adapter = NewsAdapter(newsData, findNavController())
    }

}