package com.example.actnow

import java.sql.Date
import java.sql.Time

data class AssociationDto(
    val associations: List<SingleAssociationDto>
)

data class SingleAssociationDto (

)

val associationData = AssociationDto(
    associations = listOf(
        SingleAssociationDto(

        )
    )
)