package com.glinyanov.childstars.features.aboutchilds.data.mappers

import com.glinyanov.childstars.core.domain.OtpDo
import com.glinyanov.childstars.features.aboutchilds.data.dto.ChildDto
import com.glinyanov.childstars.features.aboutchilds.domain.ChildDo

internal class Mapper {

    fun toDo(data: String): OtpDo = OtpDo(data)
    fun toDo(dto: List<ChildDto>): List<ChildDo> {
        return dto.map(::toDo)
    }

    private fun toDo(childDto: ChildDto): ChildDo {
        return ChildDo(
            id = childDto.name + childDto.otp + childDto.email,
            name = childDto.name,
            email = childDto.email,
        )
    }
}