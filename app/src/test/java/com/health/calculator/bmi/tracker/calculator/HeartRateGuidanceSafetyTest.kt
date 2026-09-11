package com.health.calculator.bmi.tracker.calculator

import com.health.calculator.bmi.tracker.ui.components.HeartRateFormula
import com.health.calculator.bmi.tracker.util.HeartRateZoneCalculator
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class HeartRateGuidanceSafetyTest {

    @Test
    fun zonesExposeTalkTestGuidanceWithoutFixedDurationPrescription() {
        val result = HeartRateZoneCalculator.calculateZones(
            age = 35,
            formula = HeartRateFormula.TANAKA
        )

        assertEquals(5, result.zones.size)
        assertTrue(result.zones.all { it.talkTest.isNotBlank() })
        assertTrue(result.zones.all { it.percentLow < it.percentHigh })
        assertTrue(result.zones.all { it.bpmLow <= it.bpmHigh })
        assertFalse(result.zones.any { zone ->
            listOf("minutes", "minute", "hours", "hour").any { unit ->
                zone.purpose.contains(unit, ignoreCase = true) ||
                    zone.effortDescription.contains(unit, ignoreCase = true)
            }
        })
    }
}
