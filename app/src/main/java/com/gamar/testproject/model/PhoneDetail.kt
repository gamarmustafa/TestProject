package com.gamar.testproject.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames

@Serializable
data class PhoneDetail(
    @JsonNames("Color")
    val color: String? = null,
    @JsonNames("Capacity")
    val capacity: String? = null,
    @JsonNames("Price")
    val price: Double? = null,
    @SerialName("Generation")
    val generation: String? = null,
    val year: Int? = null,
    @SerialName("CPU model")
    val cpuModel: String? = null,
    @SerialName("Hard disk size")
    val hardDiskSize: String? = null,
    @SerialName("Strap Colour")
    val strapColour: String? = null,
    @SerialName("Case Size")
    val caseSize: String? = null,
    @SerialName("Screen size")
    val screenSize: Double? = null,
    @SerialName("Description")
    val description: String? = null
    )
