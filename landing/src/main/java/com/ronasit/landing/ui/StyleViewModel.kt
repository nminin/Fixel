package com.ronasit.landing.ui

import com.ronasit.core.base.ViewModel
import com.ronasit.core.extension.behaviorRelay
import io.reactivex.rxjava3.core.Observable

class StyleViewModel: ViewModel() {

    fun getColor(): Observable<String> = behaviorRelay("#FAC91F")
}