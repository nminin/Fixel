package com.ronasit.account.ui.fragment.helpcenter

import com.ronasit.account.repository.HelpRepository
import com.ronasit.core.base.ViewModel
import com.ronasit.core.extension.acceptTo
import com.ronasit.core.extension.dispose
import com.ronasit.core.model.HelpCenterType

class HelpCenterViewModel(
    private val helpRepository: HelpRepository
): ViewModel() {

    fun getHelpPage(type: HelpCenterType) = helpRepository.get(type)

    fun refresh(type: HelpCenterType) {
        helpRepository.refresh(type)
            .acceptTo()
            .dispose(disposeBag)
    }
}