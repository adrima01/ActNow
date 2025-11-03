package com.example.actnow

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
    val missionsCompletees: Int,
    val missionsAVenir: Int,
    val statut: Int,
    val points: Int,
    val image: Int
)

val utilisateur = Utilisateur(
    1,
    "Müller",
    "Thomas",
    100,
    LocalDate.of(2025, 10, 4),
    5,
    2,
    3,
    125,
    R.drawable.profile_monsieur
)