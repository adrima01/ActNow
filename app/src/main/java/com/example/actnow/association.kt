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
            adresse = Adresse(rue = "Rue Madame de Staël",
                numero = "22",
                codePostal = "57070",
                ville = "Metz",
                pays = "France"),
            descritpion = "Association qui s'occupe des événements pendant la période du Noël." +
                    "Elle cherche régulièrement des bénévolants pour leurs marchés et workshops."
        ),
        SingleAssociationDto(
            id = 2,
            nom = "Les Mains Vertes",
            téléphone = "06 45 12 89 33",
            mail = "contact@mainsvertes.fr",
            adresse = Adresse(
                rue = "Rue Saint-Livier",
                numero = "29",
                codePostal = "57000",
                ville = "Metz",
                pays = "France"
            ),
            descritpion = "Association dédiée à la reforestation urbaine et à la création de potagers partagés. " +
                    "Elle recherche des bénévoles pour entretenir les espaces verts et organiser des ateliers écologiques."
        ),
        SingleAssociationDto(
            id = 3,
            nom = "Sourires Solidaires",
            téléphone = "07 58 42 31 20",
            mail = "benevolat@sourires-solidaires.org",
            adresse = Adresse(
                rue = "Rue Paul Michaux",
                numero = "4bis",
                codePostal = "57000",
                ville = "Metz",
                pays = "France"
            ),
            descritpion = "Cette association apporte de la joie et du soutien aux personnes isolées et âgées. " +
                "Elle organise régulièrement des visites, animations et distributions de repas."
        ),
        SingleAssociationDto(
            id = 4,
            nom = "Les Gardiens de l’Océan",
            téléphone = "09 87 64 55 21",
            mail = "info@gardiensocean.fr",
            adresse = Adresse(
                rue = "Rue aux Ossons",
                numero = "2",
                codePostal = "57000",
                ville = "Metz",
                pays = "France"
            ),
            descritpion = "Organisation environnementale qui agit pour la protection des plages et de la biodiversité marine. " +
            "Elle recrute des bénévoles pour des opérations de nettoyage et de sensibilisation."
        ),
        SingleAssociationDto(
            id = 5,
            nom = "Les Petits Artistes",
            téléphone = "03 21 50 77 62",
            mail = "atelier@petitsartistes.fr",
            adresse = Adresse(
                rue = "En Nexirue",
                numero = "17",
                codePostal = "57000",
                ville = "Metz",
                pays = "France"
            ),
            descritpion = "Association culturelle qui propose des ateliers d’art pour les enfants et adolescents. " +
            "Elle recherche des bénévoles pour l’encadrement et l’organisation des expositions."
        ),
        SingleAssociationDto(
            id = 6,
            nom = "Tous à Table",
            téléphone = "05 44 20 18 70",
            mail = "contact@tousatable.org",
            adresse = Adresse(
                rue = "Rue Saint-Etienne",
                numero = "5",
                codePostal = "57140",
                ville = "Woippy",
                pays = "France"
            ),
            descritpion = "Réseau d'entraide alimentaire qui lutte contre le gaspillage. " +
            "Elle recherche des bénévoles pour la collecte, le tri et la distribution de repas solidaires."
        ),
        SingleAssociationDto(
            id = 7,
            nom = "Aide & Sourds France",
            téléphone = "01 82 66 90 14",
            mail = "contact@aidesourds.fr",
            adresse = Adresse(
                rue = "Rue des Jardins",
                numero = "27",
                codePostal = "57000",
                ville = "Metz",
                pays = "France"
                ),
            descritpion = "Association qui favorise l’inclusion des personnes sourdes et malentendantes. " +
            "Elle forme des bénévoles à la langue des signes et organise des événements inclusifs."
        ),
        SingleAssociationDto(
            id = 8,
            nom = "Refuge Animalier du Soleil",
            téléphone = "04 77 89 23 45",
            mail = "refuge@animalierdusoleil.fr",
            adresse = Adresse(
                rue = "Rue de la Charmille",
                numero = "13",
                codePostal = "57155",
                ville = "Marly",
                pays = "France"
            ),
            descritpion = "Refuge qui recueille et soigne les animaux abandonnés. " +
            "Les bénévoles aident à l’entretien du refuge et à la socialisation des animaux."
        )
    )
)