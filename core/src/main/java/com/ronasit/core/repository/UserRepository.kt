package com.ronasit.core.repository

import com.nminin.corearchcomponents.core.data.repository.RepositoryGet
import com.ronasit.core.base.repository.RepositoryRefresh
import com.ronasit.core.base.repository.RepositorySet
import com.ronasit.core.model.Optional
import com.ronasit.core.model.User

interface UserRepository: RepositoryRefresh<Unit>, RepositoryGet<Optional<User>>, RepositorySet<User>