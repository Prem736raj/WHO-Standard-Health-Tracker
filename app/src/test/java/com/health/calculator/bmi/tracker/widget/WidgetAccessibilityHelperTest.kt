package com.health.calculator.bmi.tracker.widget

import com.health.calculator.bmi.tracker.widget.core.WidgetAccessibilityHelper
import org.junit.Assert.assertEquals
import org.junit.Test

class WidgetAccessibilityHelperTest {

    @Test
    fun bloodPressureBadgeUsesTheSamePrecedenceAsTheCalculator() {
        assertEquals("✗ Markedly elevated", WidgetAccessibilityHelper.bpCategoryBadge(180, 80))
        assertEquals("✗ Markedly elevated", WidgetAccessibilityHelper.bpCategoryBadge(120, 120))
        assertEquals("✗ Stage 2", WidgetAccessibilityHelper.bpCategoryBadge(140, 70))
        assertEquals("⚠ Stage 1", WidgetAccessibilityHelper.bpCategoryBadge(135, 75))
        assertEquals("↓ Low", WidgetAccessibilityHelper.bpCategoryBadge(89, 59))
        assertEquals("– Not tracked", WidgetAccessibilityHelper.bpCategoryBadge(0, 0))
    }
}
