package com.example.actnow

import java.util.Date


data class Utilisateur (
    val id: Int,
    val nom: String,
    val prenom: String,
    val heures: Int,
    val date: Date,
    val missionsCompletees: Int,
    val missionsAVenir: Int,
    val statut: Int,
    val points: Int,
)

val utilisateur = Utilisateur(
    1,
    "Müller",
    "Thomas",
    42,
    java.sql.Date.valueOf("2025-10-05"),
    5,
    2,
    3,
    125
)