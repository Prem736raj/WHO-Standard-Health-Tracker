package com.health.calculator.bmi.tracker.calculator

import com.health.calculator.bmi.tracker.data.model.Gender
import com.health.calculator.bmi.tracker.data.model.WaistRiskLevel
import com.health.calculator.bmi.tracker.data.model.WhrCalculator
import com.health.calculator.bmi.tracker.data.model.WhrGuideData
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class WhrMeasurementPolicyTest {

    @Test
    fun measurementGuideUsesTheSharedAnatomicalMethod() {
        val guideText = buildString {
            append(WhrGuideData.waistGuide.description)
            append(WhrGuideData.waistGuide.steps.joinToString(" "))
            append(WhrGuideData.hipGuide.description)
            append(WhrGuideData.hipGuide.steps.joinToString(" "))
        }.lowercase()

        assertTrue(guideText.contains("lowest palpable rib"))
        assertTrue(guideText.contains("iliac crest"))
        assertTrue(guideText.contains("normal exhale"))
        assertTrue(guideText.contains("widest part of the buttocks"))
        assertFalse(guideText.contains("narrowest"))
        assertFalse(guideText.contains("belly button"))
        assertFalse(guideText.contains("navel"))
    }

    @Test
    fun waistReferenceUsesOneSourcedThresholdForEverySupportedPopulation() {
        Ethnicity.entries.forEach { ethnicity ->
            val maleReference = ethnicity.maleWaistCm
            val femaleReference = ethnicity.femaleWaistCm

            assertEquals(
                WaistRiskLevel.NORMAL,
                WhrCalculator.calculate(maleReference - 0.1f, 100f, Gender.MALE, 30, ethnicity = ethnicity)
                    .waistRiskLevel
            )
            assertEquals(
                WaistRiskLevel.INCREASED,
                WhrCalculator.calculate(maleReference, 100f, Gender.MALE, 30, ethnicity = ethnicity)
                    .waistRiskLevel
            )
            assertEquals(
                WaistRiskLevel.INCREASED,
                WhrCalculator.calculate(maleReference + 0.1f, 100f, Gender.MALE, 30, ethnicity = ethnicity)
                    .waistRiskLevel
            )

            assertEquals(
                WaistRiskLevel.NORMAL,
                WhrCalculator.calculate(femaleReference - 0.1f, 100f, Gender.FEMALE, 30, ethnicity = ethnicity)
                    .waistRiskLevel
            )
            assertEquals(
                WaistRiskLevel.INCREASED,
                WhrCalculator.calculate(femaleReference, 100f, Gender.FEMALE, 30, ethnicity = ethnicity)
                    .waistRiskLevel
            )
            assertEquals(
                WaistRiskLevel.INCREASED,
                WhrCalculator.calculate(femaleReference + 0.1f, 100f, Gender.FEMALE, 30, ethnicity = ethnicity)
                    .waistRiskLevel
            )
        }
    }
}
