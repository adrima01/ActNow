package com.example.actnow


data class AssociationDto(
    val associations: List<SingleAssociationDto>
)

data class SingleAssociationDto (
    val id: Int,
    val nom: String,
    val téléphone: String,
    val mail: String,
    val adresse: Adresse,
    val descritpion: String
)

data class Adresse(
    val rue: String,
    val numero: String,
    val codePostal: String,
    val ville: String,
    val pays: String
)

val associationData = AssociationDto(
    associations = listOf(
        SingleAssociationDto(
            id = 1,
            nom = "Les pères Noël",
            téléphone = "12 30 26 27 27",
            mail = "noel@gmail.com",
            adresse = Adresse(rue = "rue du Père",
                numero = "2",
                codePostal = "57070",
                ville = "Metz",
                pays = "France"),
            descritpion = "Association qui s'occupe des événements pendant la période du Noël." +
                    "Elle cherche régulièrement des bénévolants pour leurs marchés et workshops."
        )
    )
)