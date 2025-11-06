package com.example.actnow.viewmodels

import com.example.actnow.BadgeData
import com.example.actnow.BadgeType
import com.example.actnow.SingleBadgeDto
import com.example.actnow.utilisateur
import java.time.LocalDate
import java.time.temporal.ChronoUnit

class ProfileViewModel {

    val achievedBadges: List<SingleBadgeDto>
    val lockedBadges: List<SingleBadgeDto>

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
