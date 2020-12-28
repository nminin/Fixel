package com.ronasit.landing.data.mapper

import com.ronasit.core.base.Mapper
import com.ronasit.core.model.Category
import com.ronasit.core.model.Landing
import com.ronasit.landing.data.networking.response.LandingResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

internal class LandingMapper : Mapper<LandingResponse, Landing> {
    override fun map(input: LandingResponse): Single<Landing> = Single.create<Landing> { emitter ->
        emitter.onSuccess(
            Landing(
                headerImage = input.banner?.headerImage,
                textHeader = input.banner?.textHeader,
                textMain = input.banner?.textMain,
                textPromo = input.banner?.textPromo,
                textBottom = input.banner?.textBottom,
                buttonVisibility = input.banner?.button?.isVisible ?: false,
                buttonText = input.banner?.button?.text ?: "",
                categoriesHeader = input.categories?.textHeader,
                categories = input.categories?.items?.map { item ->
                    Category(
                        item.genderId ?: "",
                        item.name ?: "",
                        item.image ?: ""
                    )
                } ?: emptyList()

            )
        )
    }
        .subscribeOn(Schedulers.computation())
        .observeOn(AndroidSchedulers.mainThread())

}