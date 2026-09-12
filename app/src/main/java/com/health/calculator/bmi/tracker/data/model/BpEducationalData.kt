package com.health.calculator.bmi.tracker.data.model

data class BpEducationalSection(
    val id: String,
    val emoji: String,
    val title: String,
    val subtitle: String,
    val content: List<BpEducationalItem>
)

sealed class BpEducationalItem {
    data class Paragraph(val text: String) : BpEducationalItem()
    data class Heading(val text: String) : BpEducationalItem()
    data class BulletPoint(val icon: String, val title: String, val description: String) : BpEducationalItem()
    data class NumberedStep(val number: Int, val icon: String, val title: String, val description: String) : BpEducationalItem()
    data class HighlightBox(val emoji: String, val title: String, val text: String, val type: HighlightType) : BpEducationalItem()
    data class ComparisonRow(val label: String, val include: String, val avoid: String) : BpEducationalItem()
    data class MythBuster(val myth: String, val fact: String) : BpEducationalItem()
    data class Analogy(val emoji: String, val text: String) : BpEducationalItem()
    data class DividerItem(val label: String = "") : BpEducationalItem()
}

enum class HighlightType {
    INFO, WARNING, SUCCESS, TIP, DANGER
}

object BpEducationalContent {

    fun getAllSections(): List<BpEducationalSection> = listOf(
        getUnderstandingBP(),
        getHowToMeasure(),
        getRiskFactors(),
        getDashDiet(),
        getBpMyths()
    )

    // ─── Section 1: Understanding Blood Pressure ───────────────────────────

    private fun getUnderstandingBP() = BpEducationalSection(
        id = "understanding_bp",
        emoji = "🫀",
        title = "Understanding Blood Pressure",
        subtitle = "What the numbers mean and why they matter",
        content = listOf(
            BpEducationalItem.Analogy(
                "🏠",
                "Think of your circulatory system like a home plumbing system. Your heart is the pump, your arteries are the pipes, and blood pressure is the water pressure in those pipes. Too much pressure can damage the pipes over time — just like high blood pressure can damage your arteries."
            ),

            BpEducationalItem.Heading("What Do the Numbers Mean?"),

            BpEducationalItem.Paragraph(
                "Blood pressure is recorded as two numbers written as a fraction, like 120/80 mmHg (read as \"120 over 80\"). Each number tells you something different about your heart and blood vessels."
            ),

            BpEducationalItem.BulletPoint(
                "❤️",
                "Systolic (Top Number)",
                "This measures the pressure in your arteries when your heart BEATS and pushes blood out. It's the higher number because this is when the most force is being applied. Think of it as the 'push' pressure."
            ),

            BpEducationalItem.BulletPoint(
                "💙",
                "Diastolic (Bottom Number)",
                "This measures the pressure in your arteries when your heart RESTS between beats. It's the lower number because the heart is relaxing. Think of it as the 'resting' pressure. Even between beats, there should be some pressure to keep blood flowing."
            ),

            BpEducationalItem.BulletPoint(
                "📏",
                "mmHg (Millimeters of Mercury)",
                "This is the unit of measurement. It comes from early blood pressure devices that used a column of mercury. Even though modern devices are digital, we still use this unit."
            ),

            BpEducationalItem.DividerItem(),

            BpEducationalItem.Heading("The Heart Pump Analogy"),

            BpEducationalItem.Analogy(
                "💪",
                "Imagine squeezing a water balloon connected to a garden hose. When you SQUEEZE (systolic), water pressure in the hose goes up. When you RELEASE (diastolic), the pressure drops but doesn't go to zero because the balloon still has some tension. Your heart works the same way — each squeeze sends a wave of pressure through your arteries."
            ),

            BpEducationalItem.DividerItem(),

            BpEducationalItem.Heading("Why Both Numbers Matter"),

            BpEducationalItem.Paragraph(
                "Both systolic and diastolic pressures are important indicators of cardiovascular health:"
            ),

            BpEducationalItem.BulletPoint(
                "📈",
                "High Systolic",
                "A higher systolic value can reflect greater pressure during each heartbeat. Patterns over time are more informative than one reading, and a qualified professional should interpret persistent elevation."
            ),

            BpEducationalItem.BulletPoint(
                "📊",
                "High Diastolic",
                "Suggests your blood vessels aren't relaxing properly between heartbeats. More common in younger adults and indicates increased resistance in smaller blood vessels."
            ),

            BpEducationalItem.HighlightBox(
                "💡",
                "Did You Know?",
                "Your blood pressure changes throughout the day. It's typically lowest during sleep and rises in the morning. Stress, physical activity, caffeine, and even the temperature can cause temporary changes. This is why consistent measurement conditions are important.",
                HighlightType.INFO
            ),

            BpEducationalItem.DividerItem(),

            BpEducationalItem.Heading("The Pulse Pressure Connection"),

            BpEducationalItem.Paragraph(
                "The difference between your systolic and diastolic numbers is called pulse pressure. It changes with age, health, measurement conditions and the individual; this app shows it as context, not as a standalone assessment of artery or heart function."
            ),

            BpEducationalItem.HighlightBox(
                "🎯",
                "The Goal",
                "Reference categories can help describe a reading, but a personal target depends on your history, health conditions and care plan. Discuss goals with a qualified healthcare professional rather than treating 120/80 mmHg as a universal target.",
                HighlightType.SUCCESS
            )
        )
    )

    // ─── Section 2: How to Measure BP Correctly ───────────────────────────

    private fun getHowToMeasure() = BpEducationalSection(
        id = "how_to_measure",
        emoji = "📋",
        title = "How to Measure BP Correctly",
        subtitle = "Step-by-step guide for accurate readings",
        content = listOf(
            BpEducationalItem.HighlightBox(
                "⚠️",
                "Why Accuracy Matters",
                "An incorrect measurement can create confusion. Consistent technique makes readings more useful for personal tracking and for questions you may bring to a healthcare professional.",
                HighlightType.WARNING
            ),

            BpEducationalItem.Heading("Before You Measure"),

            BpEducationalItem.NumberedStep(
                1, "🚫",
                "Avoid These 30 Minutes Before",
                "Don't consume caffeine, exercise, or smoke for at least 30 minutes before taking a reading. All of these can temporarily raise your blood pressure and give you an inaccurate result."
            ),

            BpEducationalItem.NumberedStep(
                2, "🚽",
                "Empty Your Bladder",
                "Use the bathroom before measuring when possible; a full bladder can affect a reading."
            ),

            BpEducationalItem.NumberedStep(
                3, "🪑",
                "Sit and Relax for 5 Minutes",
                "Sit in a comfortable chair with your back supported. Place both feet flat on the floor (don't cross your legs). Rest quietly for 5 minutes. This allows your body to reach a baseline state."
            ),

            BpEducationalItem.DividerItem("During Measurement"),

            BpEducationalItem.NumberedStep(
                4, "💪",
                "Position Your Arm Correctly",
                "Rest your arm on a flat surface (like a table) so that the cuff on your upper arm is at the same level as your heart. Roll up your sleeve – don't measure over clothing. Use the same arm each time (left arm is generally recommended)."
            ),

            BpEducationalItem.NumberedStep(
                5, "⌚",
                "Apply the Cuff Properly",
                "Place the cuff on your bare upper arm, about 1 inch (2.5 cm) above the elbow crease. The cuff should be snug but you should be able to slip two fingers underneath. Make sure the tubing runs down the center of your inner arm."
            ),

            BpEducationalItem.NumberedStep(
                6, "🤫",
                "Stay Still and Quiet",
                "Don't talk, text, or move during the measurement. Small movements and conversation can affect a reading. Breathe normally and try to relax."
            ),

            BpEducationalItem.NumberedStep(
                7, "📝",
                "Take Multiple Readings",
                "Take two readings about one minute apart and record both; follow your monitor and care team's instructions if they differ. Repeated readings give more context than a single snapshot."
            ),

            BpEducationalItem.DividerItem("Best Practices"),

            BpEducationalItem.NumberedStep(
                8, "⏰",
                "Measure at the Same Time Daily",
                "Blood pressure fluctuates throughout the day. For consistent tracking, measure at the same times each day. Morning (within 1 hour of waking, before medication) and evening (before bed) are ideal."
            ),

            BpEducationalItem.NumberedStep(
                9, "📊",
                "Keep a Log",
                "Record every reading with the date, time, and any relevant notes (like 'after exercise' or 'felt stressed'). This log is invaluable for your doctor. This app does this automatically for you!"
            ),

            BpEducationalItem.HighlightBox(
                "✅",
                "A consistent home log",
                "Many home-monitoring plans use readings at consistent times on several days. Your care team can tell you how often to measure; record the readings and conditions rather than searching for one supposedly perfect number.",
                HighlightType.TIP
            ),

            BpEducationalItem.DividerItem(),

            BpEducationalItem.Heading("Common Measurement Errors"),

            BpEducationalItem.BulletPoint(
                "❌",
                "Wrong Cuff Size",
                "A cuff that's too small gives falsely high readings; too large gives falsely low readings. Most home monitors come with standard cuffs — check if your arm circumference requires a different size."
            ),

            BpEducationalItem.BulletPoint(
                "❌",
                "Measuring Over Clothing",
                "A cuff over clothing can affect a reading. Place it on bare skin and follow the monitor's fit instructions."
            ),

            BpEducationalItem.BulletPoint(
                "❌",
                "Unsupported Back or Feet",
                "Use a chair with back support, keep both feet flat and avoid crossing your legs so your posture is consistent."
            ),

            BpEducationalItem.BulletPoint(
                "❌",
                "Arm Below Heart Level",
                "Support your arm on a flat surface with the cuff at heart level; an unsupported or low arm can affect the reading."
            )
        )
    )

    // ─── Section 3: Risk Factors ───────────────────────────────────────────

    private fun getRiskFactors() = BpEducationalSection(
        id = "risk_factors",
        emoji = "⚡",
        title = "Risk Factors for High BP",
        subtitle = "What you can and can't change",
        content = listOf(
            BpEducationalItem.Paragraph(
                "Understanding your risk factors helps you take control of what you can change and be vigilant about what you can't. Having risk factors doesn't mean you'll develop high blood pressure — but awareness is the first step to prevention."
            ),

            BpEducationalItem.Heading("🔧 Modifiable Risk Factors"),
            BpEducationalItem.Paragraph("These are factors YOU can change through lifestyle choices:"),

            BpEducationalItem.BulletPoint(
                "🧂",
                "High Sodium Diet",
                "Higher sodium intake can raise blood pressure for some people. The American Heart Association describes less than 2,300 mg/day as a general limit and an ideal 1,500 mg/day for many adults with high blood pressure, but individual advice can differ."
            ),

            BpEducationalItem.BulletPoint(
                "⚖️",
                "Excess Weight",
                "Body size and blood pressure can be related, but the relationship varies. If weight change is a goal, choose gradual, sustainable habits and discuss an appropriate plan with a professional rather than expecting a fixed change in mmHg."
            ),

            BpEducationalItem.BulletPoint(
                "🛋️",
                "Physical Inactivity",
                "Regular movement supports overall cardiovascular health. Start at a comfortable level and build gradually; activity choices and limits should reflect your current health and advice from a professional."
            ),

            BpEducationalItem.BulletPoint(
                "😰",
                "Chronic Stress",
                "Stress hormones constrict blood vessels and make the heart beat faster. While temporary stress spikes are normal, chronic stress can contribute to sustained high BP. It also often leads to unhealthy coping behaviors."
            ),

            BpEducationalItem.BulletPoint(
                "🍷",
                "Excessive Alcohol",
                "More than 2 drinks daily for men or 1 for women can raise blood pressure. Heavy drinking can also reduce the effectiveness of BP medications. Moderate consumption (or none) is recommended."
            ),

            BpEducationalItem.BulletPoint(
                "🚬",
                "Smoking",
                "Each cigarette temporarily raises BP for several minutes. Chemicals in tobacco damage artery walls, making them narrow and stiff. Secondhand smoke also poses risks."
            ),

            BpEducationalItem.BulletPoint(
                "🍔",
                "Poor Diet",
                "Low potassium, low fiber, high saturated fat, and high sugar diets all contribute to hypertension. The DASH diet (see our DASH Diet section) is specifically designed to combat this."
            ),

            BpEducationalItem.BulletPoint(
                "😴",
                "Poor Sleep",
                "Sleep apnea and chronic sleep deprivation (less than 6 hours per night) are linked to higher blood pressure. Quality sleep allows the cardiovascular system to recover."
            ),

            BpEducationalItem.DividerItem(),

            BpEducationalItem.Heading("🔒 Non-Modifiable Risk Factors"),
            BpEducationalItem.Paragraph("These factors are outside your control, but knowing them helps you stay vigilant:"),

            BpEducationalItem.BulletPoint(
                "📅",
                "Age",
                "Blood pressure tends to increase with age. The risk rises significantly after 55 for women and 45 for men. Arterial stiffness naturally increases over time, leading to higher systolic pressure."
            ),

            BpEducationalItem.BulletPoint(
                "🧬",
                "Genetics & Family History",
                "If your parents or siblings have hypertension, your risk is higher. Genetic factors can affect how your kidneys handle sodium, how your blood vessels respond to stress, and your hormonal regulation of blood pressure."
            ),

            BpEducationalItem.BulletPoint(
                "🌍",
                "Race & Ethnicity",
                "Hypertension is more prevalent and often more severe in people of African descent. It also tends to develop earlier and respond differently to certain medications. South Asian populations also face elevated risk."
            ),

            BpEducationalItem.BulletPoint(
                "👤",
                "Sex",
                "Men are more likely to develop high BP before age 55. After menopause, women's risk increases and may eventually exceed men's risk. Hormonal changes play a significant role."
            ),

            BpEducationalItem.BulletPoint(
                "🏥",
                "Chronic Conditions",
                "Kidney disease, diabetes, sleep apnea, and certain hormonal disorders can cause or worsen hypertension. These conditions may require specialized treatment approaches."
            ),

            BpEducationalItem.HighlightBox(
                "💡",
                "The Good News",
                "Even if you have non-modifiable risk factors, managing the modifiable ones can significantly reduce your overall risk. Many people with genetic predisposition successfully maintain normal blood pressure through healthy lifestyle choices.",
                HighlightType.SUCCESS
            )
        )
    )

    // ─── Section 4: DASH Diet ──────────────────────────────────────────────

    private fun getDashDiet() = BpEducationalSection(
        id = "dash_diet",
        emoji = "🥗",
        title = "DASH Diet Overview",
        subtitle = "Dietary Approaches to Stop Hypertension",
        content = listOf(
            BpEducationalItem.Paragraph(
                "The DASH diet (Dietary Approaches to Stop Hypertension) is an eating pattern studied by the National Heart, Lung, and Blood Institute. It emphasizes vegetables, fruit, whole grains, beans, nuts, low-fat dairy and lower sodium; response varies by person."
            ),

            BpEducationalItem.HighlightBox(
                "🏆",
                "Proven Results",
                "DASH is an evidence-informed eating pattern, not a prescription or a promise of a specific blood-pressure change. Adapt it to your culture, preferences, access and any clinical dietary advice.",
                HighlightType.SUCCESS
            ),

            BpEducationalItem.Heading("Key Principles"),

            BpEducationalItem.BulletPoint(
                "🥬",
                "More Fruits & Vegetables",
                "Aim for 4-5 servings of each daily. They're rich in potassium, magnesium, and fiber — all of which help lower blood pressure. Fresh, frozen, and canned (no salt added) all count."
            ),

            BpEducationalItem.BulletPoint(
                "🌾",
                "Whole Grains",
                "6-8 servings daily. Choose whole wheat bread, brown rice, oatmeal, and whole grain pasta over refined grains. Whole grains provide fiber and nutrients that support cardiovascular health."
            ),

            BpEducationalItem.BulletPoint(
                "🥛",
                "Low-Fat Dairy",
                "2-3 servings daily. Low-fat milk, yogurt, and cheese provide calcium and vitamin D. Studies show dairy's calcium specifically helps regulate blood pressure."
            ),

            BpEducationalItem.BulletPoint(
                "🐟",
                "Lean Proteins",
                "Up to 6 servings daily. Choose fish (especially fatty fish like salmon), skinless poultry, and plant proteins like beans and lentils. Limit red meat to 1-2 times per week."
            ),

            BpEducationalItem.BulletPoint(
                "🥜",
                "Nuts, Seeds & Legumes",
                "4-5 servings per week. Almonds, walnuts, sunflower seeds, kidney beans, and lentils are excellent choices. They provide magnesium, potassium, and healthy fats."
            ),

            BpEducationalItem.BulletPoint(
                "🫒",
                "Healthy Fats",
                "2-3 servings daily. Use olive oil, avocado, and canola oil. Limit saturated fat to less than 6% of calories. Avoid trans fats entirely."
            ),

            BpEducationalItem.DividerItem(),

            BpEducationalItem.Heading("Foods to Limit or Avoid"),

            BpEducationalItem.BulletPoint(
                "🧂",
                "Sodium",
                "Standard DASH: Less than 2,300 mg/day. Low-sodium DASH: Less than 1,500 mg/day. The low-sodium version can lower BP by an additional 3-4 mmHg."
            ),

            BpEducationalItem.BulletPoint(
                "🍬",
                "Added Sugars",
                "Limit to 5 or fewer servings of sweets per week. Choose fruit for sweetness instead. Sugar-sweetened beverages are a major hidden source."
            ),

            BpEducationalItem.BulletPoint(
                "🥩",
                "Red & Processed Meats",
                "Limit red meat. Avoid processed meats like bacon, sausage, and deli meats which are very high in sodium and saturated fat."
            ),

            BpEducationalItem.BulletPoint(
                "🍟",
                "Processed & Fast Foods",
                "These are typically loaded with sodium, unhealthy fats, and empty calories. A single fast food meal can contain 2,000+ mg of sodium."
            ),

            BpEducationalItem.DividerItem(),

            BpEducationalItem.Heading("Sample DASH Day"),

            BpEducationalItem.HighlightBox(
                "🌅",
                "Breakfast",
                "Oatmeal with berries and walnuts, low-fat yogurt, orange juice (low sodium)",
                HighlightType.TIP
            ),

            BpEducationalItem.HighlightBox(
                "☀️",
                "Lunch",
                "Whole wheat turkey wrap with spinach and avocado, apple, low-fat milk",
                HighlightType.TIP
            ),

            BpEducationalItem.HighlightBox(
                "🌆",
                "Dinner",
                "Baked salmon, brown rice, steamed broccoli with olive oil, mixed green salad",
                HighlightType.TIP
            ),

            BpEducationalItem.HighlightBox(
                "🍎",
                "Snacks",
                "Handful of unsalted almonds, carrot sticks with hummus, banana",
                HighlightType.TIP
            ),

            BpEducationalItem.HighlightBox(
                "💡",
                "Getting Started",
                "You don't have to change everything at once. Start by adding one extra serving of fruits or vegetables daily, then gradually incorporate more DASH principles over several weeks. Small, consistent changes are more sustainable than dramatic overhauls.",
                HighlightType.INFO
            )
        )
    )

    // ─── Section 5: BP Myths ───────────────────────────────────────────────

    private fun getBpMyths() = BpEducationalSection(
        id = "bp_myths",
        emoji = "🔍",
        title = "Blood Pressure Myths",
        subtitle = "Separating fact from fiction",
        content = listOf(
            BpEducationalItem.Paragraph(
                "Misinformation about blood pressure is widespread and can be dangerous. Let's separate fact from fiction on the most common myths."
            ),

            BpEducationalItem.MythBuster(
                myth = "\"I feel fine, so my blood pressure must be fine.\"",
                fact = "High blood pressure often has no warning signs or symptoms. Measuring it is the only way to know your current reading; one reading is a snapshot, so a healthcare professional should interpret a repeated pattern and any symptoms."
            ),

            BpEducationalItem.MythBuster(
                myth = "\"Only old people get high blood pressure.\"",
                fact = "While the risk increases with age, hypertension affects people of ALL ages. About 1 in 4 adults aged 20-44 has elevated blood pressure. Childhood obesity, sedentary lifestyles, high-sodium diets, and stress are causing hypertension to appear in increasingly younger populations. Even children can have high blood pressure."
            ),

            BpEducationalItem.MythBuster(
                myth = "\"I can stop taking my BP medication once my numbers are normal.\"",
                fact = "Do not stop or change prescribed blood-pressure medicine on your own. A reading can reflect treatment, measurement conditions or other factors; ask the prescriber how to review medicines safely."
            ),

            BpEducationalItem.MythBuster(
                myth = "\"Salt is the only dietary factor that affects blood pressure.\"",
                fact = "While sodium is important, many other dietary factors influence blood pressure. Potassium deficiency, low calcium and magnesium intake, excessive alcohol, high sugar consumption, and not enough fiber all contribute. The DASH diet addresses all of these factors, not just sodium."
            ),

            BpEducationalItem.MythBuster(
                myth = "\"If high blood pressure ran in my family, there's nothing I can do.\"",
                fact = "Genetics is just one risk factor. Even with a strong family history, lifestyle changes can significantly reduce your risk or delay onset. Regular exercise, a healthy diet, maintaining a healthy weight, limiting alcohol, and managing stress can lower blood pressure by 10-20+ mmHg — often more than a single medication."
            ),

            BpEducationalItem.MythBuster(
                myth = "\"Home blood pressure monitors aren't accurate.\"",
                fact = "Modern validated home blood pressure monitors are very accurate and are actually recommended by medical professionals. Home readings can be MORE representative of your true blood pressure than clinic readings because they're taken in your natural environment, avoiding 'white coat syndrome.' The key is to use a validated, upper-arm monitor and follow proper measurement technique."
            ),

            BpEducationalItem.MythBuster(
                myth = "\"Red wine is good for blood pressure.\"",
                fact = "While moderate red wine consumption has been associated with some cardiovascular benefits (likely from antioxidants), alcohol — including wine — actually RAISES blood pressure. The risks of alcohol on blood pressure outweigh any potential benefits. If you don't drink, there's no reason to start for heart health. Better alternatives include grape juice and berries."
            ),

            BpEducationalItem.MythBuster(
                myth = "\"Drinking lots of water will lower my blood pressure.\"",
                fact = "Staying hydrated supports overall wellbeing, but extra water is not a blood-pressure treatment. Fluid needs vary with climate, activity, medicines and health conditions; follow individualized advice instead of a fixed glass count."
            ),

            BpEducationalItem.HighlightBox(
                "🎯",
                "The Bottom Line",
                "High blood pressure deserves attention, but this app cannot diagnose or manage it. Use readings as information to discuss with a qualified healthcare professional and follow the care plan you receive.",
                HighlightType.WARNING
            )
        )
    )
}
