package com.example.actnow

import java.sql.Date
import java.sql.Time
import java.time.LocalDate

enum class Niveau(val titre: String, val heuresRequises: Int) {
    NOUVEAU("Nouveau bénévole", 0),
    ACTIF("Bénévole actif", 20),
    ENGAGÉ("Bénévole engagé", 60),
    SUPER("Super bénévole", 150),
    MÉGA("Méga bénévole", 300);

    companion object {
        fun obtenirNiveauPourHeures(heures: Int): Niveau {
            return Niveau.entries
                .filter { it.heuresRequises <= heures }
                .maxByOrNull { it.heuresRequises } ?: NOUVEAU
        }
    }

    fun niveauSuivant(): Niveau? {
        val tous = Niveau.entries
        val indexActuel = tous.indexOf(this)
        return if (indexActuel < tous.lastIndex) tous[indexActuel + 1] else null
    }
}


data class Utilisateur (
    val id: Int,
    val nom: String,
    val prenom: String,
    val heures: Int,
    val date: LocalDate,
    val points: Int,
    val image: Int,
    val missionsCompletees: List<SingleMissionDto>
)

val utilisateur = Utilisateur(
    id = 1,
    nom = "Claude",
    prenom = "Thomas",
    heures = 11,
    date = LocalDate.of(2025, 10, 4),
    points = 125,
    image = R.drawable.profile_monsieur,
    missionsCompletees = listOf(SingleMissionDto(
        id = "1",
        titre = "Marché de Noël",
        association = associationData.associations[0],
        date = Date.valueOf("2024-12-05"),
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
        date = Date.valueOf("2024-03-20"),
        heure = Time.valueOf("09:00:00"),
        lieu = "Metz",
        description = "Rejoignez Les Mains Vertes pour une journée de plantation citoyenne au parc Blandan ! 🌱 " +
                "Au programme : préparation du sol, plantation d’arbres et de fleurs locales, et sensibilisation à la biodiversité. " +
                "Aucun prérequis nécessaire, juste votre bonne humeur et vos gants de jardinage !",
        nombreParticipants = 20,
        recompenses = listOf("🌱 15 XP", "🥇 Badge Éco-Citoyen", "🚰 Bouteille réutilisable offerte"),
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
    ))
)