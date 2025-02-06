package com.glinyanov.childstars.features.aboutchilds.data

import com.glinyanov.childstars.core.data.local.CommonPrefsRepository
import com.glinyanov.childstars.core.data.remote.UnauthorizedDataIOException
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
            val userId = prefsRepository.getUserId() ?: throw AboutChildsErrors.Forbidden(null)
            mapper.toDo(repository.getOTPCode(userId))
        } catch (ex: UnauthorizedDataIOException) {
            prefsRepository.clearUserId()
            throw AboutChildsErrors.Forbidden(null)
        } catch (ex: IOException) {
            throw AboutChildsErrors.OtpError(null)
        }
    }

    override suspend fun getChilds(): List<ChildDo> {
        return try {
            val userId = prefsRepository.getUserId() ?: throw AboutChildsErrors.Forbidden(null)
            mapper.toDo(repository.getChilds(userId))
        } catch (ex: UnauthorizedDataIOException) {
            prefsRepository.clearUserId()
            throw AboutChildsErrors.Forbidden(null)
        } catch (ex: IOException) {
            throw AboutChildsErrors.AboutChildError(null)
        }
    }
}