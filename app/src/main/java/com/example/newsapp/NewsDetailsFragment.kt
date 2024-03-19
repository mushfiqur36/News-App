package com.example.newsapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.example.newsapp.databinding.FragmentNewsDetailsBinding
import com.example.newsapp.presenter.viewmodel.NewsViewModel


class NewsDetailsFragment : Fragment() {
    private lateinit var fragmentNewsDetailsBinding: FragmentNewsDetailsBinding
    private lateinit var viewModel: NewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentNewsDetailsBinding = FragmentNewsDetailsBinding.bind(view)
        val args: NewsDetailsFragmentArgs by navArgs()
        val article = args.selectedArticle

        viewModel = (activity as MainActivity).viewModel

        println("news title: ${article.url}")
        fragmentNewsDetailsBinding.newsDetailsWebView.apply {
            webViewClient = WebViewClient()
            if (article.url != null) {
                loadUrl(article.url)
            }
        }

        fragmentNewsDetailsBinding.fabSave.setOnClickListener {
            viewModel.saveArticle(article)
            com.google.android.material.snackbar.Snackbar.make(
                view,
                "Article saved successfully",
                com.google.android.material.snackbar.Snackbar.LENGTH_SHORT
            ).show()
        }
    }
}