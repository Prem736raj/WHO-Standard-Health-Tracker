# Device and accessibility QA matrix

Status: **Owner/device verification required**
Prepared: 2026-09-11

This is a reproducible checklist for the signed release candidate. A Windows
Gradle build proves compilation and unit behavior but does not prove rendering,
permissions, TalkBack, OEM scheduling or Health Connect provider behavior.

## Required device matrix

| Profile | API | Form factor | Theme/font | Required flows | Result |
| --- | ---: | --- | --- | --- | --- |
| Small phone | 26/29 | 320–360dp wide | Light, 1.0x/1.5x | onboarding, Home, Track, every calculator, History, Profile | Pending |
| Current Android phone | 33/34 | 360–411dp wide | Light/dark, 1.0x/1.5x/2.0x | first action, water result, BMI slider, AI retry, exports | Pending |
| New Android phone | 35/36 | 360–411dp wide | Light/dark, TalkBack | Health Connect, reminders, reboot/time change, clear data | Pending |
| Large/tablet layout | 34+ | 600dp+ | Light/dark, 1.5x | hubs, charts, calculator inputs/results, split/expanded layouts | Pending |

## Route and regression checklist

- [ ] Cold start, process death and restore do not show a blank/unknown route.
- [ ] Water Needs input → Calculate → result renders, back navigation works,
  and an unavailable result shows a recovery state rather than an empty page.
- [ ] History title, back and actions share one compact top row.
- [ ] BMI slider/live preview never overlaps the weight or height controls.
- [ ] WHR instructions use the rib-to-iliac midpoint and widest hip landmark.
- [ ] BP values at 179/119, 180/119, 179/120 and 180/120 show the same severe
  repeat/escalation boundary in result, recommendation and widget copy.
- [ ] Heart-rate zones show talk-test guidance and no fixed duration prescription.
- [ ] AI send, offline failure, retry and clear conversation do not duplicate a
  user bubble or expose raw context.
- [ ] Health Connect denial/unavailability leaves manual tracking usable and no
  write permission is requested.
- [ ] Reminder enablement asks for notifications only when needed; reboot,
  timezone/DST and notification denial are handled.
- [ ] CSV, JSON, PDF, image and weekly reports show provenance, selected scope
  and informational/non-diagnostic disclosure.

## Accessibility and visual checks

- [ ] TalkBack labels identify values, units, actions and charts without relying
  on color or emoji.
- [ ] Every primary action and icon action has at least a 48dp target.
- [ ] 1.5x and 2.0x font scales do not clip input labels, result values,
  bottom navigation or dialogs.
- [ ] Light/dark contrast remains readable for hero, metric, list, loading and
  error components.
- [ ] Landscape, split-screen and tablet widths do not create horizontal
  clipping or inaccessible controls.
- [ ] Screenshot review covers Home, Track, Calculators, Insights, Profile,
  Water result, BMI result and one report screen.

## Evidence record

- Build commit: fill with the signed release-candidate SHA.
- Device/emulator IDs: fill during owner QA.
- Failed flows and screenshots: attach to the release issue; do not put health
  values or personal data in public tickets.
- Current local limitation: `connectedDebugAndroidTest` built the test APK on
  2026-09-11 but failed before execution with `No connected devices!`; this
  matrix is intentionally not marked passed.
