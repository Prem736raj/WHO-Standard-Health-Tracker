package com.health.calculator.bmi.tracker.calculator

import com.health.calculator.bmi.tracker.data.model.BloodPressureCalculator
import com.health.calculator.bmi.tracker.data.model.BpCategory
import com.health.calculator.bmi.tracker.data.model.BloodPressureReference
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class BloodPressureBoundaryTest {

    @Test
    fun severeReadingUsesTheSameInclusiveBoundaryForEitherNumber() {
        assertEquals(BpCategory.GRADE_1_HYPERTENSION, BloodPressureCalculator.categorize(179, 119))
        assertEquals(BpCategory.HYPERTENSIVE_CRISIS, BloodPressureCalculator.categorize(180, 119))
        assertEquals(BpCategory.HYPERTENSIVE_CRISIS, BloodPressureCalculator.categorize(181, 119))
        assertEquals(BpCategory.HYPERTENSIVE_CRISIS, BloodPressureCalculator.categorize(179, 120))
        assertEquals(BpCategory.HYPERTENSIVE_CRISIS, BloodPressureCalculator.categorize(179, 121))
        assertEquals(BpCategory.HYPERTENSIVE_CRISIS, BloodPressureCalculator.categorize(180, 120))
        assertEquals(BpCategory.HYPERTENSIVE_CRISIS, BloodPressureCalculator.categorize(181, 121))

        assertFalse(BloodPressureCalculator.isEmergencyReading(179, 119))
        assertTrue(BloodPressureCalculator.isEmergencyReading(BloodPressureReference.SEVERE_SYSTOLIC_MMHG, 119))
        assertTrue(BloodPressureCalculator.isEmergencyReading(179, BloodPressureReference.SEVERE_DIASTOLIC_MMHG))
    }

    @Test
    fun mixedSystolicAndDiastolicReadingsUseTheHigherPattern() {
        assertEquals(BpCategory.ISOLATED_SYSTOLIC, BloodPressureCalculator.categorize(150, 55))
        assertEquals(BpCategory.GRADE_1_HYPERTENSION, BloodPressureCalculator.categorize(110, 95))
        assertEquals(BpCategory.NORMAL, BloodPressureCalculator.categorize(125, 75))
        assertEquals(BpCategory.HIGH_NORMAL, BloodPressureCalculator.categorize(135, 75))
        assertEquals(BpCategory.HIGH_NORMAL, BloodPressureCalculator.categorize(115, 85))
    }
}
