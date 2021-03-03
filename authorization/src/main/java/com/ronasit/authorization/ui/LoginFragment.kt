package com.ronasit.authorization.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout
import com.nminin.bindingbuilder.bind
import com.nminin.bindingbuilder.default.TextDecorator
import com.nminin.bindingbuilder.default.VisibilityDecorator
import com.ronasit.authorization.R
import com.ronasit.authorization.databinding.FragmentLoginBinding
import com.ronasit.core.extension.*
import com.ronasit.core.ui.Fragment
import com.ronasit.core.ui.StyleViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment(R.layout.fragment_login) {

    private val viewModel by viewModel<AuthorizationViewModel>()
    private val styleViewModel by viewModel<StyleViewModel>()

    override fun initView(view: View, savedInstanceState: Bundle?) {
        baseObserve(viewModel)
        bindView<TextInputLayout>(R.id.input_layout_email)
            .highlightsInputLayoutBind(styleViewModel.getStyle())
        bindView<TextInputLayout>(R.id.input_layout_password)
            .highlightsInputLayoutBind(styleViewModel.getStyle())


        with(FragmentLoginBinding.bind(view)) {
            textEmail.bind(this@LoginFragment)
                .highlightsEditTextBind(styleViewModel.getStyle())
            textPassword.bind(this@LoginFragment)
                .highlightsEditTextBind(styleViewModel.getStyle())

            this.buttonLogin.bind(this@LoginFragment)
                .highlightsBind(styleViewModel.getStyle())
                .onClick {
                    viewModel.login(
                        this.textEmail.text.toString(),
                        this.textPassword.text.toString()
                    )
                }
            this.errorEmail.bind(this@LoginFragment)
                .observe(
                    viewModel.emailError.map {
                        it.isNotEmpty().toVisibility()
                    }, VisibilityDecorator()
                )
                .observe(viewModel.emailError, TextDecorator())

            this.errorPassword.bind(this@LoginFragment)
                .observe(
                    viewModel.passwordError.map {
                        it.isNotEmpty().toVisibility()
                    }, VisibilityDecorator()
                )
                .observe(viewModel.passwordError, TextDecorator())

        }
    }
}