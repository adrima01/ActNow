package com.example.actnow.viewmodels

import androidx.lifecycle.ViewModel
import com.example.actnow.BadgeData
import com.example.actnow.BadgeType
import com.example.actnow.Niveau
import com.example.actnow.SingleBadgeDto
import com.example.actnow.missionData
import com.example.actnow.utilisateur
import com.russhwolf.settings.Settings
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.Locale

class ProfileViewModel : ViewModel() {

    val achievedBadges: List<SingleBadgeDto>
    val lockedBadges: List<SingleBadgeDto>

    val niveauActuel = Niveau.obtenirNiveauPourHeures(utilisateur.heures)
    val niveauSuivant = niveauActuel.niveauSuivant()

    val pourcentage: Float = if (niveauSuivant != null) {
        ((utilisateur.heures - niveauActuel.heuresRequises).toFloat() /
                (niveauSuivant.heuresRequises - niveauActuel.heuresRequises))
            .coerceIn(0f, 1f)
    } else {
        1f
    }

    val formattedDate: String = utilisateur.date.format(
        DateTimeFormatter.ofPattern("MMMM yyyy", Locale.FRENCH)
    )

    val fullName: String = "${utilisateur.prenom} ${utilisateur.nom}"
    val totalHeures: Int = utilisateur.heures
    val missionsCompletees: Int = utilisateur.missionsCompletees
    val missionsAVenir: Int = utilisateur.missionsAVenir
    val imageResId: Int = utilisateur.image

    val settings = Settings()

    val participatedMissions = missionData.missions.filter { mission ->
        settings.getBooleanOrNull(mission.id.toString()) == true
    }

    val count = participatedMissions.size

    init {
        val monthsBetween = ChronoUnit.MONTHS.between(utilisateur.date, LocalDate.now())

        val achieved = mutableListOf<SingleBadgeDto>()
        val locked = mutableListOf<SingleBadgeDto>()

        BadgeData.badges.forEach { badge ->
            val achievedCondition = when (badge.type) {
                BadgeType.HOUR -> utilisateur.heures >= badge.targetAmount
                BadgeType.MISSION -> utilisateur.missionsCompletees >= badge.targetAmount
                BadgeType.TIME -> monthsBetween >= badge.targetAmount
            }
            if (achievedCondition) achieved.add(badge) else locked.add(badge)
        }

        achievedBadges = achieved
        lockedBadges = locked
    }
}
