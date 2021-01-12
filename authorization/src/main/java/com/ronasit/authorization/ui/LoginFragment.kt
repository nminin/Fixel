package com.ronasit.authorization.ui

import android.os.Bundle
import android.view.View
import com.ronasit.authorization.R
import com.ronasit.authorization.databinding.FragmentLoginBinding
import com.ronasit.core.ui.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment: Fragment(R.layout.fragment_login) {

    private val viewModel by viewModel<AuthorizationViewModel>()

    override fun initView(view: View, savedInstanceState: Bundle?) {
        with(FragmentLoginBinding.bind(view)) {
            this.buttonLogin.setOnClickListener {
                viewModel.login(
                    this.textEmail.text.toString(),
                    this.textPassword.text.toString()
                )
            }
        }
    }
}