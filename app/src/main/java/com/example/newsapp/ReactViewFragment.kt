package com.example.newsapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import com.example.newsapp.databinding.FragmentReactViewBinding


class ReactViewFragment : Fragment() {

    private lateinit var fragmentReactViewBinding: FragmentReactViewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_react_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentReactViewBinding = FragmentReactViewBinding.bind(view)

        loadWebView()
    }

    private fun loadWebView() {
        try {
            val webView = fragmentReactViewBinding.reactWebView
            webView.settings.javaScriptEnabled = true
            webView.webViewClient = WebViewClient()

            webView.loadUrl("http://192.168.2.37:5173")
            println("react view: loaded")
        } catch (e: Exception) {
            println("react view: ${e.message}")
        }
    }

}