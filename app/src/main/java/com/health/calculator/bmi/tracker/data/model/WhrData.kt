package com.health.calculator.bmi.tracker.data.model

data class WhrInput(
    val waistCm: Float = 0f,
    val hipCm: Float = 0f,
    val gender: Gender = Gender.MALE,
    val age: Int = 25,
    val useMetric: Boolean = true
)

data class WhrMeasurementGuide(
    val title: String,
    val description: String,
    val steps: List<String>
)

object WhrGuideData {
    /**
     * Use one anatomical landmark everywhere in the product. The WHO report
     * describes the waist as the midpoint between the lowest palpable rib and
     * the top of the iliac crest; other landmark conventions describe a
     * different protocol and are therefore intentionally avoided.
     */
    const val waistLandmark =
        "Measure midway between the lowest palpable rib and the top of the hip bone (iliac crest)."
    const val waistMethod =
        "After a normal exhale, keep the tape horizontal and snug without compressing the skin."
    const val hipLandmark =
        "Measure around the widest part of the buttocks, with the tape level."

    val waistGuide = WhrMeasurementGuide(
        title = "How to Measure Waist",
        description = "$waistLandmark $waistMethod",
        steps = listOf(
            "Stand upright with your feet together and your abdomen relaxed",
            "Find the lowest palpable rib and the top of the hip bone (iliac crest)",
            "Place the tape at the midpoint between those two landmarks",
            "Keep the tape horizontal and snug without compressing the skin",
            "Breathe normally and take the reading after a normal exhale",
            "Repeat the measurement once or twice and use a consistent method"
        )
    )

    val hipGuide = WhrMeasurementGuide(
        title = "How to Measure Hips",
        description = hipLandmark,
        steps = listOf(
            "Stand with feet together",
            "Find the widest part of the buttocks",
            "Wrap the tape around at this widest point",
            "Keep the tape level and parallel to the floor",
            "Make sure the tape is snug without compressing the skin",
            "Read the measurement while standing naturally",
            "Repeat the measurement once or twice and use a consistent method"
        )
    )
}
