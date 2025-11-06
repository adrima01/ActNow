package com.example.actnow

data class BadgeDto(
    val badges: List<SingleBadgeDto>
)

enum class BadgeType {
    HOUR, MISSION, TIME
}

data class SingleBadgeDto(
    val id: Int,
    val titre: String,
    val description: String,
    val objectif: String,
    val image: Int,
    val achieved: Boolean,
    val type: BadgeType,
    val targetAmount: Int // nombre heures, missions realisee ou mois passes depuis l'inscription
)

val BadgeData = BadgeDto(
    badges = listOf(
        SingleBadgeDto(
            id = 1,
            titre = "1 mois ensemble",
            description = "Bénévole depuis un mois",
            objectif = "Membre depuis 1 mois",
            image = R.drawable.mois1,
            achieved = true,
            type = BadgeType.TIME,
            targetAmount = 1,

        ),
        SingleBadgeDto(
            id = 2,
            titre = "Premier pas",
            description = "Première mission complétée",
            objectif = "1 mission à complétér",
            image = R.drawable.pas,
            achieved = true,
            type = BadgeType.MISSION,
            targetAmount = 1
        ),
        SingleBadgeDto(
            id = 3,
            titre = "Bénévole active",
            description = "10h de bénévolat",
            objectif = "10h à complétér",
            image = R.drawable.benevole,
            achieved = false,
            type = BadgeType.HOUR,
            targetAmount = 10
        ),
        SingleBadgeDto(
            id = 4,
            titre = "Cœur généreux",
            description = "10 missions complétées",
            objectif = "10 missions à complétér",
            image = R.drawable.coeur,
            achieved = false,
            type = BadgeType.MISSION,
            targetAmount = 10
        ),
        SingleBadgeDto(
            id = 5,
            titre = "Marathonien",
            description = "100h de bénévolat",
            objectif = "100h à complétér",
            image = R.drawable.marathonien,
            achieved = false,
            type = BadgeType.HOUR,
            targetAmount = 100
        )
    )
)