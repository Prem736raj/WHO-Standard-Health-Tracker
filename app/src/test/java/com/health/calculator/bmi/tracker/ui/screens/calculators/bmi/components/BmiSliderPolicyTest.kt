package com.health.calculator.bmi.tracker.ui.screens.calculators.bmi.components

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class BmiSliderPolicyTest {

    @Test
    fun markerFractionIsClampedAtThePreviewEdges() {
        assertEquals(0f, bmiMarkerFraction(5f), 0.0001f)
        assertEquals(0f, bmiMarkerFraction(12f), 0.0001f)
        assertEquals(0.5f, bmiMarkerFraction(31f), 0.0001f)
        assertEquals(1f, bmiMarkerFraction(50f), 0.0001f)
        assertEquals(1f, bmiMarkerFraction(80f), 0.0001f)
    }

    @Test
    fun markerFractionNeverProducesNonFiniteValues() {
        assertTrue(bmiMarkerFraction(Float.NaN).isFinite())
        assertTrue(bmiMarkerFraction(Float.POSITIVE_INFINITY).isFinite())
        assertTrue(bmiMarkerFraction(Float.NEGATIVE_INFINITY).isFinite())
    }
}
