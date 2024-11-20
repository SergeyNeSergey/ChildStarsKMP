package com.glinyanov.childstars.features.aboutchilds.data

import com.glinyanov.childstars.core.data.local.CommonPrefsRepository
import com.glinyanov.childstars.core.data.remote.UnauthorizedDataError
import com.glinyanov.childstars.core.domain.OtpDo
import com.glinyanov.childstars.features.aboutchilds.data.mappers.Mapper
import com.glinyanov.childstars.features.aboutchilds.data.repository.AboutChildsRepository
import com.glinyanov.childstars.features.aboutchilds.domain.AboutChildsErrors
import com.glinyanov.childstars.features.aboutchilds.domain.AboutChildsUseCase
import com.glinyanov.childstars.features.aboutchilds.domain.ChildDo
import kotlinx.io.IOException

internal class AboutChildsUseCaseImpl(
    private val repository: AboutChildsRepository,
    private val prefsRepository: CommonPrefsRepository,
    private val mapper: Mapper,
) : AboutChildsUseCase {

    override suspend fun addChild(): OtpDo {
        return try {
            val userId = prefsRepository.getUserId() ?: throw AboutChildsErrors.Forbidden
            mapper.toDo(repository.getOTPCode(userId))
        } catch (ex: UnauthorizedDataError) {
            prefsRepository.clearUserId()
            throw AboutChildsErrors.Forbidden
        } catch (ex: IOException) {
            throw AboutChildsErrors.OtpError
        }
    }

    override suspend fun getChilds(): List<ChildDo> {
        return try {
            val userId = prefsRepository.getUserId() ?: throw AboutChildsErrors.Forbidden
            mapper.toDo(repository.getChilds(userId))
        } catch (ex: UnauthorizedDataError) {
            prefsRepository.clearUserId()
            throw AboutChildsErrors.Forbidden
        } catch (ex: IOException) {
            throw AboutChildsErrors.AboutChildError
        }
    }
}