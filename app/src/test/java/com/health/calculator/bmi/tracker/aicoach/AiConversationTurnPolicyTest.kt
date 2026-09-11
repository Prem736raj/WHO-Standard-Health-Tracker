package com.health.calculator.bmi.tracker.aicoach

import com.health.calculator.bmi.tracker.ui.screens.aicoach.AiConversationTurnPolicy
import com.health.calculator.bmi.tracker.ui.screens.aicoach.ChatMessage
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class AiConversationTurnPolicyTest {

    @Test
    fun retryReplacesErrorBubbleWithoutAddingAnotherUserMessage() {
        val messages = listOf(
            ChatMessage("How can I hydrate better?", isUser = true),
            ChatMessage("Try regular water breaks.", isUser = false),
            ChatMessage("", isUser = false, isError = true)
        )

        val retried = AiConversationTurnPolicy.prepareRetry(messages)

        assertEquals(1, retried.count { it.isUser })
        assertEquals(3, retried.size)
        assertTrue(retried.last().isLoading)
        assertFalse(retried.last().isError)
    }

    @Test
    fun retryAddsLoadingBubbleWhenOnlyThePersistedUserTurnIsRendered() {
        val retried = AiConversationTurnPolicy.prepareRetry(
            listOf(ChatMessage("What is a healthy breakfast?", isUser = true))
        )

        assertEquals(2, retried.size)
        assertEquals(1, retried.count { it.isUser })
        assertTrue(retried.last().isLoading)
    }

    @Test
    fun priorDialogueExcludesCurrentPromptAndTransientBubbles() {
        val messages = listOf(
            ChatMessage("Earlier question", isUser = true),
            ChatMessage("Earlier answer", isUser = false),
            ChatMessage("Current question", isUser = true),
            ChatMessage("partial", isUser = false, isLoading = true),
            ChatMessage("error", isUser = false, isError = true)
        )

        val dialogue = AiConversationTurnPolicy.priorDialogue(messages, "Current question")

        assertEquals(
            listOf(true to "Earlier question", false to "Earlier answer"),
            dialogue
        )
    }
}
