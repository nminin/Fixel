package com.ronasit.account.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.*
import com.nminin.bindingbuilder.default.TextDecorator
import com.nminin.bindingbuilder.onTextChanged
import com.ronasit.account.R
import com.ronasit.account.ui.viewmodel.AccountViewModel
import com.ronasit.core.base.binding.EnabledDecorator
import com.ronasit.core.extension.*
import com.ronasit.core.model.HighlightText
import com.ronasit.core.ui.CustomDialogHost
import com.ronasit.core.ui.Fragment
import com.ronasit.core.ui.StyleViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AccountInfoFragment: Fragment(R.layout.fragment_account_info) {

    private val styleViewModel by viewModel<StyleViewModel>()
    private val accountViewModel by viewModel<AccountViewModel>()

    override fun initView(view: View, savedInstanceState: Bundle?) {
        bindView<TextView>(R.id.text_header)
            .highlightsBind(
                HighlightText.fromRes(
                    requireContext(),
                    R.string.highlight_text_account,
                    listOf(R.string.highlight_my)
                ),
                styleViewModel.getStyle(),
                "#000000"
            )
        bindView<ImageView>(R.id.button_info)
            .onClick {
                showInfoDialog()
            }
        bindView<ImageView>(R.id.button_back)
            .onClick {
                onBackPressed()
            }

        bindView<EditText>(R.id.text_first_name)
            .highlightsEditTextBind(styleViewModel.getStyle())
            .observe(accountViewModel.getUser().safeMap {
                it.firstName
            }, TextDecorator())
            .onTextChanged {
                accountViewModel.setFirstName(it)
            }
        bindView<EditText>(R.id.text_last_name)
            .highlightsEditTextBind(styleViewModel.getStyle())
            .observe(accountViewModel.getUser().safeMap {
                it.lastName
            }, TextDecorator())
            .onTextChanged {
                accountViewModel.setLastName(it)
            }
        bindView<EditText>(R.id.text_email)
            .highlightsEditTextBind(styleViewModel.getStyle())
            .observe(accountViewModel.getUser().safeMap {
                it.email
            }, TextDecorator())
            .onTextChanged {
                accountViewModel.setEmail(it)
            }
        bindView<EditText>(R.id.text_phone)
            .highlightsEditTextBind(styleViewModel.getStyle())
            .observe(accountViewModel.getUser().safeMap {
                it.phoneNumber
            }, TextDecorator())
            .onTextChanged {
                accountViewModel.setPhone(it)
            }
        bindView<EditText>(R.id.text_id)
            .highlightsEditTextBind(styleViewModel.getStyle())
            .observe(accountViewModel.getUser().safeMap {
                it.identificationNumber
            }, TextDecorator())
            .onTextChanged {
                accountViewModel.setID(it)
            }

        bindView<Button>(R.id.button_save)
            .highlightsBind(styleViewModel.getStyle())
            .observe(accountViewModel.isSaveAvailable(), EnabledDecorator())
            .onClick {
                accountViewModel.saveChanges()
            }

        bindView<TextView>(R.id.button_change_password)
            .onClick {
                coordinator.toChangePassword()
            }

        bindView<Switch>(R.id.switch_male)
            .highlightsSwitch(styleViewModel.getStyle())
        bindView<Switch>(R.id.switch_female)
            .highlightsSwitch(styleViewModel.getStyle())
        bindView<Switch>(R.id.switch_non_binary)
            .highlightsSwitch(styleViewModel.getStyle())

    }

    override fun onBackPressed() {
        coordinator.navigateUp()
    }
    private fun showInfoDialog() {
        (activity as CustomDialogHost).showDialog(IdInfoDialogFragment())
    }
}