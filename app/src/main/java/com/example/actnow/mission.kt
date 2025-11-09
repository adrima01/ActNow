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
    val adresse: Adresse,
    val participantsImages: List<String>
)

val missionData = MissionDto(
    missions = listOf(
        SingleMissionDto(
            id = "1",
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
            recompenses = listOf("📜 Certificat de participation", "☕ Boisson chaude offerte"),
            imageName = "marchenoel",
            adresse = Adresse(
                rue = "Rue Madame de Staël",
                numero = "22",
                codePostal = "57070",
                ville = "Metz",
                pays = "France",
                lat = 49.101884,
                long = 6.201481
            ),
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
            recompenses = listOf("🥇 Badge Éco-Citoyen", "🚰 Bouteille réutilisable offerte"),
            imageName = "plantationprintemps",
            adresse = Adresse(
                rue = "Rue Saint-Livier",
                numero = "29",
                codePostal = "57000",
                ville = "Metz",
                pays = "France",
                lat = 49.100148,
                long = 6.172286
            ),
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
            recompenses = listOf("📜 Certificat d'engagement", "🎁 Goodie bag"),
            imageName = "retraite",
            adresse = Adresse(
                rue = "Rue Paul Michaux",
                numero = "4bis",
                codePostal = "57000",
                ville = "Metz",
                pays = "France",
                lat = 49.119089,
                long = 6.174845
            ),
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
            recompenses = listOf("📜 Certificat d'engagement", "🎁 Goodie bag"),
            imageName = "nettoyage",
            adresse = Adresse(
                rue = "Rue aux Ossons",
                numero = "2",
                codePostal = "57000",
                ville = "Metz",
                pays = "France",
                lat = 49.115648,
                long = 6.182490
            ),
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
            recompenses = listOf("🧸 Badge Bienveillance", "🍪 Goûter offert"),
            imageName = "peinture",
            adresse = Adresse(
                rue = "En Nexirue",
                numero = "17",
                codePostal = "57000",
                ville = "Metz",
                pays = "France",
                lat = 49.117037,
                long = 6.172470
            ),
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
            recompenses = listOf("🌎 Badge Nature", "🎖️ Certificat Éco-Acteur"),
            imageName = "repas",
            adresse = Adresse(
                rue = "Rue Saint-Etienne",
                numero = "5",
                codePostal = "57140",
                ville = "Woippy",
                pays = "France",
                lat = 49.1423,
                long = 6.15017
            ),
            participantsImages = listOf("avatar1", "avatar2", "avatar3")
        ),
        SingleMissionDto(
            id = "7",
            titre = "Initiation à la langue des signes",
            association = associationData.associations[6],
            date = Date.valueOf("2026-02-15"),
            heure = Time.valueOf("17:00:00"),
            lieu = "Metz",
            description = "Participez à un atelier d’initiation à la langue des signes avec Aide & Sourds France 🤟. " +
                    "Apprenez les bases de la communication gestuelle et découvrez comment mieux interagir avec les personnes sourdes au quotidien.",
            nombreParticipants = 20,
            recompenses = listOf("⭐ Badge Éducation", "📜 Attestation de tutorat"),
            imageName = "signes",
            adresse = Adresse(
                rue = "Rue des Jardins",
                numero = "27",
                codePostal = "57000",
                ville = "Metz",
                pays = "France",
                lat = 49.1216,
                long = 6.1776
            ),
            participantsImages = listOf("avatar1", "avatar2", "avatar3")
        ),
        SingleMissionDto(
            id = "8",
            titre = "Journée d’adoption et de sensibilisation",
            association = associationData.associations[7],
            date = Date.valueOf("2026-05-18"),
            heure = Time.valueOf("11:00:00"),
            lieu = "Metz",
            description = "Le Refuge Animalier du Soleil organise une grande journée d’adoption ! 🐾 " +
                    "Les bénévoles aideront à accueillir le public, présenter les animaux et sensibiliser sur la cause animale.",
            nombreParticipants = 12,
            recompenses = listOf("🎽 T-shirt de l’événement", "🏅 Badge Marche Solidaire"),
            imageName = "adoption",
            adresse = Adresse(
                rue = "Rue de la Charmille",
                numero = "13",
                codePostal = "57155",
                ville = "Marly",
                pays = "France",
                lat = 49.062,
                long = 6.146
            ),
            participantsImages = listOf("avatar1", "avatar2", "avatar3")
        )
    )
)