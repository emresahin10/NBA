package com.denbase.nba.data

data class Players(
    val first_name: String,
    val height_feet: Any?,
    val height_inches: Any?,
    val id: Int,
    val last_name: String,
    val position: String,
    val team: Teams,
    val weight_pounds: Any?
)