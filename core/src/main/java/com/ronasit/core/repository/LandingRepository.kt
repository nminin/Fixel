package com.ronasit.core.repository

import com.nminin.corearchcomponents.core.data.repository.RepositoryGet
import com.nminin.corearchcomponents.core.data.repository.RepositoryGetAll
import com.ronasit.core.base.repository.RepositoryRefresh
import com.ronasit.core.model.Landing
import com.ronasit.core.model.LandingCategory

interface LandingRepository: RepositoryGet<Landing>, RepositoryRefresh<Unit> {

}