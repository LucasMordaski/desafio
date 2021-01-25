package com.mordaski.testeglobant.ui.news.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.mordaski.testeglobant.R
import com.mordaski.testeglobant.databinding.NewsDetailFragmentBinding
import com.mordaski.testeglobant.ui.news.data.model.NewsDetailsResponse
import com.mordaski.testeglobant.utils.Resource
import kotlinx.android.synthetic.main.error_view.view.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.koin.android.viewmodel.ext.android.viewModel

@ExperimentalCoroutinesApi
class NewsDetailFragment : Fragment() {

    lateinit var bindingView: NewsDetailFragmentBinding
    private val viewModel: NewsDetailsViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bindingView = NewsDetailFragmentBinding.inflate(inflater, container, false)
        return bindingView.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getString("token")?.let {
            runBlocking {
                viewModel.token = it
            }
        }
        arguments?.getInt("id")?.let {
            runBlocking {
                viewModel.getNews(it.toString())
            }
        }

        bindingView.close.setOnClickListener {
            backNews()
        }

        viewModel.response.observeForever {
            it?.let {
                managerResponse(it)
            }
        }

    }

    private fun managerResponse(response: Resource<List<NewsDetailsResponse>>) {
        when (response.status) {
            Resource.Status.SUCCESS -> {
                bindingView.loading.visibility = View.GONE
                bindingView.error.visibility = View.GONE
                response.data?.let {
                    setupView(it.first())
                } ?: setError()
            }

            Resource.Status.ERROR -> {
                setError()
            }
            Resource.Status.LOADING -> {bindingView.loading.visibility = View.VISIBLE}
            else -> {
            }
        }
    }

    private fun setupView(details: NewsDetailsResponse) {
        details.documento?.let {
            bindingView.titulo.text = it.titulo
            bindingView.info.text = it.corpoformatado
            Glide.with(bindingView.root)
                .load(it.imagem)
                .into(bindingView.image)
        }?:setError()


    }

    private fun setError() {
        bindingView.loading.visibility = View.GONE
        bindingView.error.visibility = View.VISIBLE
        bindingView.error.btnTryAgain.setOnClickListener {
            backNews()
        }
    }

    private fun backNews() {
        findNavController().navigate(
            R.id.action_details_to_news,
            bundleOf("token" to viewModel.token)
        )
    }


}
