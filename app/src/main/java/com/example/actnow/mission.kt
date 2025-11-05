package com.example.actnow

import java.sql.Date
import java.sql.Time

data class MissionDto(
    val missions: List<SingleMissionDto>
)

data class SingleMissionDto (
    val id: String,
    val titre: String,
    val association: SingleAssociationDto,
    val date: Date,
    val heure: Time,
    val lieu: String,
    val description: String,
    val nombreParticipants: Int,
    val recompenses: List<String>,
    val imageName: String,
    val participantsImages: List<String>
)

val missionData = MissionDto(
    missions = listOf(
        SingleMissionDto(
            id = "1",
            titre = "Marché de Noël",
            association = associationData.associations[0],
            date = Date.valueOf("2026-12-05"),
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
            listOf("🎁 10 XP", "📜 Certificat de participation", "☕ Boisson chaude offerte"),
            imageName = "marchenoel",
            participantsImages = listOf("avatar1", "avatar2", "avatar3")
        ),
        SingleMissionDto(
            id = "2",
            titre = "Plantation de Printemps",
            association = associationData.associations[1],
            date = Date.valueOf("2026-03-20"),
            heure = Time.valueOf("09:00:00"),
            lieu = "Metz",
            description = "Rejoignez Les Mains Vertes pour une journée de plantation citoyenne au parc Blandan ! 🌱 " +
                    "Au programme : préparation du sol, plantation d’arbres et de fleurs locales, et sensibilisation à la biodiversité. " +
                    "Aucun prérequis nécessaire, juste votre bonne humeur et vos gants de jardinage !",
            nombreParticipants = 20,
            recompenses = listOf("🌱 15 XP", "🥇 Badge Éco-Citoyen", "🚰 Bouteille réutilisable offerte"),
            imageName = "plantationprintemps",
            participantsImages = listOf("avatar1", "avatar2", "avatar3")
        ),
        SingleMissionDto(
            id = "3",
            titre = "Visite solidaire en maison de retraite",
            association = associationData.associations[2],
            date = Date.valueOf("2025-11-10"),
            heure = Time.valueOf("15:00:00"),
            lieu = "Metz",
            description = "L’association Sourires Solidaires organise un après-midi de partage avec les résidents de la maison de retraite Les Amandiers. 💕 " +
                    "Lecture, musique, discussions, jeux de société : toutes les bonnes volontés sont bienvenues pour apporter un moment de joie et de compagnie.",
            nombreParticipants = 8,
            recompenses = listOf("🧤 12 XP", "📜 Certificat d'engagement", "🎁 Goodie bag"),
            imageName = "retraite",
            participantsImages = listOf("avatar1", "avatar2", "avatar3")
        ),
        SingleMissionDto(
            id = "4",
            titre = "Nettoyage de plage",
            association = associationData.associations[3],
            date = Date.valueOf("2026-06-08"),
            heure = Time.valueOf("10:00:00"),
            lieu = "Metz",
            description = "Les Gardiens de l’Océan vous invitent à participer à une grande opération de nettoyage de plage à l’occasion de la Journée Mondiale des Océans 🌊. " +
                    "Sacs, gants et bonne humeur fournis ! Ensemble, protégeons nos littoraux et sensibilisons à la réduction des déchets.",
            nombreParticipants = 25,
            recompenses = listOf("🧤 12 XP", "📜 Certificat d'engagement", "🎁 Goodie bag"),
            imageName = "nettoyage",
            participantsImages = listOf("avatar1", "avatar2", "avatar3")
        ),
        SingleMissionDto(
            id = "5",
            titre = "Atelier peinture enfants",
            association = associationData.associations[4],
            date = Date.valueOf("2026-04-12"),
            heure = Time.valueOf("14:00:00"),
            lieu = "Metz",
            description = "Les Petits Artistes recherchent des bénévoles pour encadrer un atelier de peinture avec des enfants de 6 à 10 ans 🎨. " +
            "Aidez-les à exprimer leur créativité à travers les couleurs et participez à la mise en place de leur exposition de fin d’année.",
            nombreParticipants = 10,
            recompenses = listOf("📚 15 XP", "🧸 Badge Bienveillance", "🍪 Goûter offert"),
            imageName = "peinture",
            participantsImages = listOf("avatar1", "avatar2", "avatar3")
        ),
        SingleMissionDto(
            id = "6",
            titre = "Distribution de repas solidaire",
            association = associationData.associations[5],
            date = Date.valueOf("2025-12-20"),
            heure = Time.valueOf("18:30:00"),
            lieu = "Metz",
            description = "Tous à Table organise une distribution de repas solidaires pour les personnes en situation de précarité 🍲. " +
            "Les bénévoles aideront à la préparation, la distribution et l’accueil des bénéficiaires. " +
            "Un moment fort de partage et d’humanité avant les fêtes !",
            nombreParticipants = 15,
            recompenses = listOf("🌳 25 XP", "🌎 Badge Nature", "🎖️ Certificat Éco-Acteur"),
            imageName = "repas",
            participantsImages = listOf("avatar1", "avatar2", "avatar3")
        ),
        SingleMissionDto(
            id = "7",
            titre = "Initiation à la langue des signes",
            association = associationData.associations[6],
            date = Date.valueOf("2025-12-15"),
            heure = Time.valueOf("17:00:00"),
            lieu = "Metz",
            description = "Participez à un atelier d’initiation à la langue des signes avec Aide & Sourds France 🤟. " +
            "Apprenez les bases de la communication gestuelle et découvrez comment mieux interagir avec les personnes sourdes au quotidien.",
            nombreParticipants = 20,
            recompenses = listOf("✏️ 10 XP", "⭐ Badge Éducation", "📜 Attestation de tutorat"),
            imageName = "signes",
            participantsImages = listOf("avatar1", "avatar2", "avatar3")
        ),
        SingleMissionDto(
            id = "8",
            titre = "Journée d’adoption et de sensibilisation",
            association = associationData.associations[7],
            date = Date.valueOf("2026-01-18"),
            heure = Time.valueOf("11:00:00"),
            lieu = "Metz",
            description = "Le Refuge Animalier du Soleil organise une grande journée d’adoption ! 🐾 " +
            "Les bénévoles aideront à accueillir le public, présenter les animaux et sensibiliser sur la cause animale.",
            nombreParticipants = 12,
            recompenses = listOf("🥾 30 XP", "🎽 T-shirt de l’événement", "🏅 Badge Marche Solidaire"),
            imageName = "adoption",
            participantsImages = listOf("avatar1", "avatar2", "avatar3")
        )
    )
)