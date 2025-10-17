package com.example.actnow

import java.sql.Date
import java.sql.Time

data class MissionDto(
    val missions: List<SingleMissionDto>
)

data class SingleMissionDto (
    val id: Int,
    val titre: String,
    val association: SingleAssociationDto,
    val date: Date,
    val heure: Time,
    val lieu: String,
    val description: String,
    val nombreParticipants: Int,
    val récompenses: String,
    val imageUrl: String
)

val missionData = MissionDto(
    missions = listOf(
        SingleMissionDto(
            id = 1,
            titre = "Marché de Noël",
            association = associationData.associations[0],
            date = Date.valueOf("2025-12-05"),
            heure = Time.valueOf("14:30:00"),
            lieu = "Metz",
            description = "En ce temps d’hiver, notre association a le plaisir de vous inviter à son marché de Noël solidaire. " +
                    "Dans une ambiance chaleureuse et festive, vous découvrirez des stands tenus par des bénévoles et des habitants engagés : artisanat local, décorations faites main, gourmandises de saison et idées cadeaux responsables." +
                    "Nous recherchons des personnes prêtes à donner un peu de leur temps :\n" +
                    "tenir un stand,\n" +
                    "aider à l’installation et à la décoration,\n" +
                    "participer à l’accueil du public,\n" +
                    "ou encore prêter main-forte pour les animations.\n" +
                    "Chaque geste compte, et ensemble, nous pouvons créer un événement chaleureux, festif et engagé.",
            nombreParticipants = 12,
            récompenses = "10 XP",
            imageUrl = "https://www.voyageavecvue.com/wp-content/uploads/2022/11/Vignettes-pour-les-Categories-7.png"
        )
    )
)