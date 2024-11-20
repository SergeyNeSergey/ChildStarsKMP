package com.glinyanov.childstars.features.aboutchilds.domain

import com.glinyanov.childstars.core.domain.OtpDo

interface AboutChildsUseCase {

    suspend fun getChilds(): List<ChildDo>
    suspend fun addChild(): OtpDo
}