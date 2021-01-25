package com.mordaski.testeglobant.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mordaski.testeglobant.R
import com.mordaski.testeglobant.databinding.NewsFragmentBinding
import com.mordaski.testeglobant.ui.news.data.model.NewsResponse
import com.mordaski.testeglobant.utils.Resource
import kotlinx.android.synthetic.main.error_view.view.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.koin.android.viewmodel.ext.android.viewModel

@ExperimentalCoroutinesApi
class NewsFragment : Fragment(), NewsAdapter.NewsItemListener{

    lateinit var bindingView: NewsFragmentBinding
    private val viewModel: NewsViewModel by viewModel()
    private lateinit var adapter: NewsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bindingView = NewsFragmentBinding.inflate(inflater, container, false)
        return bindingView.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        arguments?.getString("token")?.let {
            runBlocking {
                viewModel.token = it
                viewModel.getNews()
            }
        }

        adapter = NewsAdapter(this)
        bindingView.newsRv.layoutManager = LinearLayoutManager(requireContext())
        bindingView.newsRv.adapter = adapter

        viewModel.response.observeForever {
            it?.let {
                managerResponse(it)
            }
        }


    }

    private fun managerResponse(response: Resource<List<NewsResponse>>) {
        when (response.status) {
            Resource.Status.SUCCESS -> {
                bindingView.loading.visibility = View.GONE
                bindingView.error.visibility = View.GONE
                response.data?.let {
                    adapter.setItems(it)
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

    private fun setError() {
        bindingView.loading.visibility = View.GONE
        bindingView.error.visibility = View.VISIBLE
        bindingView.error.btnTryAgain.setOnClickListener {
            bindingView.error.visibility = View.GONE
            runBlocking {
                viewModel.getNews()
            }
        }
    }

    override fun onClickedCharacter(characterId: Int) {
        findNavController().navigate(
            R.id.action_news_to_details,
            bundleOf(
                "token" to viewModel.token,
                "id" to characterId
            )
        )
    }
}
