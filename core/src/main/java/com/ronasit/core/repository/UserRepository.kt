package com.ronasit.core.repository

import com.nminin.corearchcomponents.core.data.repository.RepositoryGet
import com.ronasit.core.base.repository.RepositoryRefresh
import com.ronasit.core.model.User

interface UserRepository: RepositoryRefresh<User>, RepositoryGet<User>