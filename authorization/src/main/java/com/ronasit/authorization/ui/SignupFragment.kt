package com.ronasit.authorization.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.nminin.bindingbuilder.bind
import com.nminin.bindingbuilder.default.TextDecorator
import com.nminin.bindingbuilder.default.VisibilityDecorator
import com.ronasit.authorization.R
import com.ronasit.authorization.databinding.FragmentSignupBinding
import com.ronasit.core.extension.*
import com.ronasit.core.ui.Fragment
import com.ronasit.core.ui.StyleViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignupFragment : Fragment(R.layout.fragment_signup) {

    private val viewModel by viewModel<AuthorizationViewModel>()
    private val styleViewModel by viewModel<StyleViewModel>()

    override fun initView(view: View, savedInstanceState: Bundle?) {
        baseObserve(viewModel)
        bindView<TextInputLayout>(R.id.input_layout_user_name)
            .highlightsInputLayoutBind(styleViewModel.getStyle())
        bindView<TextInputLayout>(R.id.input_layout_email)
            .highlightsInputLayoutBind(styleViewModel.getStyle())
        bindView<TextInputLayout>(R.id.input_layout_user_number)
            .highlightsInputLayoutBind(styleViewModel.getStyle())
        bindView<TextInputLayout>(R.id.text_input_password)
            .highlightsInputLayoutBind(styleViewModel.getStyle())
        bindView<TextInputEditText>(R.id.text_user_name)
            .highlightsEditTextBind(styleViewModel.getStyle())
        bindView<TextInputEditText>(R.id.text_email)
            .highlightsEditTextBind(styleViewModel.getStyle())
        bindView<TextInputEditText>(R.id.text_number)
            .highlightsEditTextBind(styleViewModel.getStyle())
        bindView<TextInputEditText>(R.id.text_password)
            .highlightsEditTextBind(styleViewModel.getStyle())


        with(FragmentSignupBinding.bind(view)) {

            this.buttonSignUp.bind(this@SignupFragment)
                .highlightsBind(styleViewModel.getStyle())
                .onClick {
                    viewModel.signUp(
                        this.textUserName.text.toString(),
                        this.textEmail.text.toString(),
                        this.textNumber.text.toString(),
                        this.textPassword.text.toString()
                    )
                }
            this.errorEmail.bind(this@SignupFragment)
                .observe(
                    viewModel.emailError.map {
                        it.isNotEmpty().toVisibility()
                    }, VisibilityDecorator()
                )
                .observe(viewModel.emailError, TextDecorator())

            this.errorPassword.bind(this@SignupFragment)
                .observe(
                    viewModel.passwordError.map {
                        it.isNotEmpty().toVisibility()
                    }, VisibilityDecorator()
                )
                .observe(viewModel.passwordError, TextDecorator())

            this.errorName.bind(this@SignupFragment)
                .observe(
                    viewModel.nameError.map {
                        it.isNotEmpty().toVisibility()
                    }, VisibilityDecorator()
                )
                .observe(viewModel.nameError, TextDecorator())

            this.errorNumber.bind(this@SignupFragment)
                .observe(
                    viewModel.phoneError.map {
                        it.isNotEmpty().toVisibility()
                    }, VisibilityDecorator()
                )
                .observe(viewModel.phoneError, TextDecorator())


        }
    }
}