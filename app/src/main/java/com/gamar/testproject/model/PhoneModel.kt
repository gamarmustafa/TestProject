package com.gamar.testproject.model

import kotlinx.serialization.Serializable

@Serializable
data class PhoneModel(
    val id :Int? = null,
    val name: String? = null,
    val data: PhoneDetail? = null
)
