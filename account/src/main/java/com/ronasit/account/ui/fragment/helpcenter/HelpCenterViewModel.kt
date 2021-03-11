package com.ronasit.account.ui.fragment.helpcenter

import com.ronasit.account.model.HelpInfo
import com.ronasit.account.repository.HelpRepository
import com.ronasit.core.base.ViewModel
import com.ronasit.core.extension.safeSubscribe
import com.ronasit.core.extension.dispose
import com.ronasit.core.extension.safeMap
import com.ronasit.core.model.HelpCenterType

class HelpCenterViewModel(
    private val helpRepository: HelpRepository
) : ViewModel() {

    fun getTitle(type: HelpCenterType) = helpRepository.get(type)
        .safeMap {
            it.name
        }

    fun getItems(type: HelpCenterType) = helpRepository.get(type)
        .map {
            it.content.filter {
                it.getType() != HelpInfo.Type.EMPTY
            }
        }

    fun refresh(type: HelpCenterType) {
        helpRepository.refresh(type)
            .safeSubscribe()
            .dispose(disposeBag)
    }
}