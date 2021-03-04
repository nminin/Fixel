package com.ronasit.account.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.textfield.TextInputLayout
import com.nminin.bindingbuilder.default.TextDecorator
import com.nminin.bindingbuilder.default.VisibilityDecorator
import com.nminin.bindingbuilder.onTextChanged
import com.ronasit.account.R
import com.ronasit.account.ui.viewmodel.ChangePasswordViewModel
import com.ronasit.core.base.binding.EnabledDecorator
import com.ronasit.core.extension.*
import com.ronasit.core.ui.Fragment
import com.ronasit.core.ui.StyleViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChangePasswordFragment: Fragment(R.layout.fragment_change_password) {
    private val styleViewModel by viewModel<StyleViewModel>()
    private val changePasswordViewModel by viewModel<ChangePasswordViewModel>()

    override fun initView(view: View, savedInstanceState: Bundle?) {
        bindView<TextInputLayout>(R.id.input_layout_old_password)
            .highlightsInputLayoutBind(styleViewModel.getStyle())

        bindView<EditText>(R.id.text_old_password)
            .highlightsEditTextBind(styleViewModel.getStyle())
            .onTextChanged {
                changePasswordViewModel.setOldPassword(it)
            }

        bindView<View>(R.id.underline_old_password_error)
            .observe(changePasswordViewModel.getOldPasswordError().map {
                (it.value != null).toVisibility()
            }, VisibilityDecorator())

        bindView<TextView>(R.id.error_old_password)
            .observe(changePasswordViewModel.getOldPasswordError().map {
                (it.value != null).toVisibility()
            }, VisibilityDecorator())
            .observe(changePasswordViewModel.getOldPasswordError().safeMap {
                it.value
            }, TextDecorator())

        bindView<TextInputLayout>(R.id.input_layout_new_password)
            .highlightsInputLayoutBind(styleViewModel.getStyle())

        bindView<EditText>(R.id.text_new_password)
            .highlightsEditTextBind(styleViewModel.getStyle())
            .onTextChanged {
                changePasswordViewModel.setNewPassword(it)
            }

        bindView<View>(R.id.underline_new_password_error)
            .observe(changePasswordViewModel.getNewPasswordError().map {
                (it.value != null).toVisibility()
            }, VisibilityDecorator())

        bindView<TextView>(R.id.error_new_password)
            .observe(changePasswordViewModel.getNewPasswordError().map {
                (it.value != null).toVisibility()
            }, VisibilityDecorator())
            .observe(changePasswordViewModel.getNewPasswordError().safeMap {
                it.value
            }, TextDecorator())

        bindView<TextInputLayout>(R.id.input_layout_repeat_password)
            .highlightsInputLayoutBind(styleViewModel.getStyle())

        bindView<EditText>(R.id.text_repeat_password)
            .highlightsEditTextBind(styleViewModel.getStyle())
            .onTextChanged {
                changePasswordViewModel.setRepeatPassword(it)
            }

        bindView<View>(R.id.underline_repeat_password_error)
            .observe(changePasswordViewModel.getRepeatPasswordError().map {
                (it.value != null).toVisibility()
            }, VisibilityDecorator())

        bindView<TextView>(R.id.error_repeat_password)
            .observe(changePasswordViewModel.getRepeatPasswordError().map {
                (it.value != null).toVisibility()
            }, VisibilityDecorator())
            .observe(changePasswordViewModel.getRepeatPasswordError().safeMap {
                it.value
            }, TextDecorator())

        bindView<Button>(R.id.button_change_password)
            .highlightsBind(styleViewModel.getStyle())
            .observe(changePasswordViewModel.isSaveChangesAvailable(), EnabledDecorator())
            .onClick {
                changePasswordViewModel.changePassword()
            }

        view.findViewById<ImageView>(R.id.button_back)
            .setOnClickListener {
                onBackPressed()
            }

        changePasswordViewModel.onPasswordChanged()
            .subscribe{
                onBackPressed()
            }
    }

    override fun onBackPressed() {
        coordinator.navigateUp()
    }
}