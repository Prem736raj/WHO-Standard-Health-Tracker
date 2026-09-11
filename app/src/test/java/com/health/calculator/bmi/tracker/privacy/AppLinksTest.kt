package com.health.calculator.bmi.tracker.privacy

import com.health.calculator.bmi.tracker.core.constants.AppLinks
import org.junit.Assert.assertTrue
import org.junit.Test

class AppLinksTest {

    @Test
    fun publicTrustLinksUseTheCanonicalPagesSite() {
        assertTrue(AppLinks.PRIVACY_POLICY == "https://prem736raj.github.io/Health-Metrics-Tracker/privacy.html")
        assertTrue(AppLinks.TERMS_OF_SERVICE == "https://prem736raj.github.io/Health-Metrics-Tracker/terms.html")
    }
}
