# Release status

Updated: 2026-09-11

This file records evidence that can be reproduced from the repository. It does
not replace device, Play Console, Firebase Console, signing, or closed-test
verification.

## Current code and build gates

- Application ID remains `com.healthmetrics.tracker`.
- Latest completed audit commit is tracked in Git history; verify the SHA with
  `git log -1 --oneline` before creating a release artifact.
- Required local gates are `test`, `lintRelease`, `assembleDebug`,
  `assembleRelease`, and `bundleRelease`.
- Release signing is intentionally credential-gated through
  `RELEASE_STORE_FILE`, `RELEASE_STORE_PASSWORD`, `RELEASE_KEY_ALIAS`, and
  `RELEASE_KEY_PASSWORD`; no key or password is stored in this repository.

## Audit fixes now covered by code/tests

- P1 privacy and medical/data correctness fixes are complete; multi-profile
  switching remains disabled until records are profile-scoped.
- P2 history details parsing, food-log day boundaries, BMR ranges, WHR wording,
  BP wall-clock reminders, VO2 false-precision surfaces, hydration heuristic
  disclosure, and 48dp targets are implemented.
- A Room `MigrationTestHelper` test covers the checked-in 15→16 migration and
  verifies row preservation plus the `step_history` table.
- Dead quick-action/repository artifacts were removed after reference checks;
  the live large-widget resource under `app/src/main/res` was intentionally
  retained.

## Open runtime or owner gates

These cannot be proven by a Windows unit/build run:

1. Confirm the oldest database version ever distributed to users. If a real
   release predates version 13, restore that database and add schema fixtures
   plus migrations through version 16 before launch.
2. Run the migration test on a connected emulator/device; the repository only
   has schema fixtures for versions 15 and 16.
3. Run cold-start and macrobenchmark measurements with Firebase/App Check
   enabled in a release-like build; no startup performance number is claimed.
4. Exercise BP reminders across reboot, `TIME_SET`, timezone and DST changes,
   notification denial, and OEM battery restrictions.
5. Run Compose/accessibility checks on small and large screens, 1.5–2.0x font
   scale, TalkBack, dark/light mode, and API 26/33/34/35/36 devices.
6. Validate Health Connect aggregation with multiple data sources, denied
   permissions, unsupported providers, and process death.
7. Complete Play Console/Firebase owner tasks: release signing, App Check
   production registration, privacy/data-safety declarations, closed testing,
   store listing, and crash/ANR monitoring.

## Post-audit medical and AI trust hardening — 2026-09-11

- **Status:** Code-fixable items complete locally; device/provider validation
  remains open.
- **Changes:** WHR now uses one documented rib-to-iliac-crest waist landmark and
  one selected population reference point; the unsupported synthesized `+8 cm`
  second threshold and three-band presentation were removed while legacy enum
  data remains readable. Blood-pressure severe-reading constants are shared by
  categorization, recommendations and widget accessibility copy at inclusive
  `≥180 systolic or ≥120 diastolic`. Heart-rate zones no longer present fixed
  duration prescriptions and instead provide talk-test/planning guidance. The
  unused BSA placeholder reader was removed. AI retry now replaces the transient
  error bubble and reuses the persisted user turn rather than inserting a
  duplicate user message. Public privacy/terms/support links now use the
  canonical `Health-Metrics-Tracker` Pages/repository URL.
- **Follow-up:** Widget blood-pressure badges now delegate to the same
  categorization precedence as the calculator, so isolated severe systolic or
  diastolic readings cannot be mislabeled as Stage 1.
- **Follow-up:** BMI slider previews now remain readable when the category name
  wraps, expose a textual category summary to TalkBack, clamp edge markers
  inside the scale, and give weight/height fine-tune actions 48 dp targets with
  specific labels. The full local release gate remains green.
- **Follow-up:** The BMI Learn tab now replaces emoji headings, category-risk
  markers and local colour literals with the shared vector icon and semantic
  palette roles, keeping the same informational content and disclaimer.
- **Follow-up:** The WHR education route now uses vector icons and shared
  semantic palette roles throughout. Reference bands and BMI/WHR comparisons
  are labeled as informational context, while unsupported individual risk
  multipliers, fixed waist-change timelines and causal wording were removed.
- **Follow-up:** The BSA education route now maps legacy illustration values to
  shared vector icons and semantic palette roles. Formula history and guidance
  avoid “gold standard” or universally-most-accurate claims and no longer give
  unsupported precision about agreement between equations.
- **Follow-up:** The WHO exercise-guidelines surface now uses vector icons and
  shared semantic colours for zones, goals, sessions, and progress. The weekly
  progress ring has a spoken summary and session removal uses a 48 dp target;
  decorative emoji markers are no longer rendered.
- **Tests:** Added WHR landmark/reference-boundary, blood-pressure severe-edge,
  heart-rate guidance and AI conversation-turn policy tests. The full local
  Gradle gate is green; connected instrumentation built its APK but stopped
  before execution on 2026-09-11 with `No connected devices!`.
- **Owner/runtime notes:** Repository history contains an early Room version 12
  and checked-in migrations through version 16, but no tags, releases or
  Firebase App Distribution evidence identify the oldest distributed schema.
  Do not invent a pre-13 migration: the release owner must confirm distribution
  history and provide fixtures if needed. Connected migration, Health Connect,
  accessibility, signed-artifact and Play/Firebase checks remain listed in
  `docs/DEVICE_QA_2026-09.md` and this file.

## Heart-rate recommendation visual follow-up — 2026-09-12

- **Status:** Code-fixable polish complete; device/accessibility validation
  remains open.
- **Changes:** Goal, fitness-level, zone, calorie, workout and tip markers now
  render as stable Material icons instead of emoji. Legacy emoji fields remain
  only for backwards-compatible saved data and generated copy, which is
  cleaned before display. Shared semantic colors replace the local green,
  expandable rows use a 48 dp minimum touch target, and zero-valued zone
  distributions render a safe empty state without invalid Canvas arcs.
- **Verification:** `test`, `lintRelease`, `assembleDebug`,
  `assembleRelease` and `bundleRelease` passed with `GRADLE_EXIT=0`.
- **Remaining gates:** Run TalkBack, large-font, light/dark theme and chart
  rendering checks on a connected device; signing, Firebase and Play Console
  tasks remain owner-only.

## VO₂ max estimate visual and wording follow-up — 2026-09-12

- **Status:** Code-fixable polish complete; device/accessibility validation
  remains open.
- **Changes:** The VO₂ max surface uses stable heart, chart, timer,
  assignment and recovery icons rather than emoji, and shared semantic health
  colors replace local literals. User-facing copy now consistently describes
  an informational VO₂ max estimate; fitness-age and recovery wording avoid
  unsupported certainty while legacy resource/model values remain compatible.
- **Verification:** `test`, `lintRelease`, `assembleDebug`,
  `assembleRelease` and `bundleRelease` passed with `GRADLE_EXIT=0`.
- **Remaining gates:** Physical-device TalkBack, large-font, theme and route
  rendering checks, plus signing/Firebase/Play Console work, remain owner-only.

## Resting heart-rate guide trust and visual follow-up — 2026-09-12

- **Status:** Code-fixable polish complete; device/accessibility validation
  remains open.
- **Changes:** Measurement steps now use stable Material icons and shared
  semantic colors. Resting-heart-rate bands use neutral reference language
  instead of “normal/concerning” labels, and the sheet explains common sources
  of individual variation without diagnosing the reader.
- **Verification:** `test`, `lintRelease`, `assembleDebug`,
  `assembleRelease` and `bundleRelease` passed with `GRADLE_EXIT=0`.
- **Remaining gates:** Physical-device TalkBack, large-font, theme and sheet
  rendering checks, plus signing/Firebase/Play Console work, remain owner-only.

## Blood-pressure education trust and visual follow-up — 2026-09-12

- **Status:** Code-fixable audit complete; device/accessibility validation
  remains open.
- **Changes:** BP education sections, instructions, comparisons, myth/fact
  blocks and analogies now use stable vector icons and shared semantic colors.
  Expandable section headers have a 48 dp minimum target. Copy was aligned to
  current AHA/CDC home-monitoring guidance by removing unsupported fixed
  effect-size claims, universal “most accurate” language, fixed targets and
  medication certainty; reference bands are explicitly non-diagnostic.
- **Verification:** `test`, `lintRelease`, `assembleDebug`,
  `assembleRelease` and `bundleRelease` passed with `GRADLE_EXIT=0`.
- **Remaining gates:** Physical-device TalkBack, large-font, theme and route
  rendering checks, plus signing/Firebase/Play Console work, remain owner-only.

## Blood-pressure recommendation visual follow-up — 2026-09-12

- **Status:** Code-fixable polish complete; device/accessibility validation
  remains open.
- **Changes:** Persisted recommendation markers now resolve to stable Material
  icons instead of rendered emoji. Urgency, advice, risk, and white-coat
  sections use shared semantic health colors, and expandable recommendation
  headers provide a 48 dp minimum touch target while preserving legacy data.
- **Verification:** `test`, `lintRelease`, `assembleDebug`,
  `assembleRelease` and `bundleRelease` passed with `GRADLE_EXIT=0`.
- **Remaining gates:** Physical-device TalkBack, large-font, theme and route
  rendering checks, plus signing/Firebase/Play Console work, remain owner-only.
