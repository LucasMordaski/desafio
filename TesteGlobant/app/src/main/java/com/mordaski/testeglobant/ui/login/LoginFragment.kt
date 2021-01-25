package com.mordaski.testeglobant.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import org.koin.android.viewmodel.ext.android.viewModel
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.mordaski.testeglobant.R
import com.mordaski.testeglobant.databinding.LoginFragmentBinding
import com.mordaski.testeglobant.ui.login.data.model.AuthResponse
import com.mordaski.testeglobant.utils.Resource
import kotlinx.android.synthetic.main.error_view.view.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking


@ExperimentalCoroutinesApi
class LoginFragment : Fragment() {

    lateinit var bindingView: LoginFragmentBinding
    val viewModel: LoginViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bindingView = DataBindingUtil.inflate(inflater, R.layout.login_fragment, container, false)
        return bindingView.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

            bindingView.btnLogin.setOnClickListener {
                runBlocking {
                    viewModel.doLogin()
                }
            }

//            btnRegister.setOnClickListener {
//                //TODO Cadastrar novo cliente
//            }


        viewModel.response.observeForever {
            it?.let {
                managerResponse(it)
            }
        }

    }

    private fun managerResponse(response: Resource<AuthResponse>) {
        when (response.status) {
            Resource.Status.SUCCESS -> {
                findNavController().navigate(
                    R.id.action_login_to_news,
                    bundleOf("token" to response.data?.token)
                )

            }

            Resource.Status.ERROR -> {
                bindingView.loading.visibility = View.GONE
                bindingView.error.visibility = View.VISIBLE
                bindingView.error.btnTryAgain.setOnClickListener {
                    bindingView.error.visibility = View.GONE
                }
            }
            Resource.Status.LOADING -> {bindingView.loading.visibility = View.VISIBLE}
            else -> {
            }
        }
    }

}
