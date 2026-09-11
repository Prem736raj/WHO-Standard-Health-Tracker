# Data Safety mapping

Updated: 2026-09-11

This document maps the Android implementation to the information that must be
reviewed in the Google Play Data safety form. It is an engineering aid, not a
substitute for the release owner's final Play Console declarations.

## Local-only wellness records

The following are stored locally in Room/DataStore for the app's wellness
features and are not included in product analytics event parameters:

- Profile preferences and optional goals
- Weight, blood-pressure, water, calorie/food, step-history and calculator
  history records
- Reminder preferences, report selections and AI disclosure/context choices
- User-entered notes and locally generated reports/exports

These records support the feature the user selected, are not required for app
exploration, and are removed by the in-app clear-data controls where provided.
Android automatic backup is disabled in the manifest. The app does not expose
cloud backup, QR transfer or cross-device restore.

## Optional Health Connect access

Health Connect is feature-led and read-only:

- Steps: read steps to show optional daily totals, history and comparisons.
- Weight: read weight only when the user explicitly enables that connection.

The app requests no Health Connect write permissions and does not request sleep,
heart-rate or other records pre-emptively. Users can deny or revoke access in
Health Connect settings; the app keeps manual tracking available.

## Optional Firebase AI context

The AI Wellness Assistant is optional and requires the in-app disclosure. A
separate context switch is required before the app sends a bounded summary of
recent weight/water patterns. The summary excludes names, notes, raw entries,
calculator payloads and unrelated health records. Chat messages are stored in
the app's local chat history; Firebase service availability and retention are
controlled by the configured Firebase project and its owner settings.

The assistant is informational wellness guidance, not diagnosis, treatment or
emergency care. Users can continue without AI or clear the local conversation.

## Optional product analytics

Analytics collection is off by default and is enabled only through the app's
privacy setting. The allowlisted events contain product-flow metadata such as
screen/entry-point labels and fixed outcome categories. Health measurements,
free-form text, notes, names and calculator values are rejected before any
analytics adapter is called.

## Technical services

Firebase App Check/Play Integrity and crash/service infrastructure may process
technical identifiers needed to protect or operate the optional services. No
health measurement is placed in an analytics event parameter. The release owner
must verify the Firebase Console retention, processor and Data safety settings
before publishing.

## User controls and release review

Before release, verify the following against the signed build and current
Firebase/Play configuration:

1. Privacy disclosure links resolve to the Health Metrics Tracker pages.
2. Health Connect permission screens explain each requested record and denial
   leaves the rest of the app usable.
3. AI context and analytics are visibly optional and disabled by default.
4. Clear-data behavior removes the local records described above.
5. Play Console declarations match the actual release artifact and any enabled
   Firebase products; do not copy this mapping blindly if configuration changes.
