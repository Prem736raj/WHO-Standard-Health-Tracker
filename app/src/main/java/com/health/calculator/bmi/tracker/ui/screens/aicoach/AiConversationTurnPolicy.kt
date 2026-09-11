package com.health.calculator.bmi.tracker.ui.screens.aicoach

/**
 * Keeps a retry an execution concern rather than a new conversation turn.
 * The failed user message is already persisted; retrying only replaces its
 * transient error bubble with a loading bubble.
 */
internal object AiConversationTurnPolicy {

    fun prepareRetry(messages: List<ChatMessage>): List<ChatMessage> {
        val retryableErrorIndex = messages.indexOfLast { !it.isUser && it.isError }
        if (retryableErrorIndex >= 0) {
            return messages.toMutableList().apply {
                this[retryableErrorIndex] = ChatMessage(
                    text = "",
                    isUser = false,
                    isLoading = true
                )
            }
        }

        return if (messages.lastOrNull()?.isUser == true) {
            messages + ChatMessage(text = "", isUser = false, isLoading = true)
        } else {
            messages
        }
    }

    fun priorDialogue(messages: List<ChatMessage>, currentPrompt: String): List<Pair<Boolean, String>> =
        messages
            .filter { !it.isLoading && !it.isError && it.text.isNotBlank() }
            .dropLastWhile { it.isUser && it.text == currentPrompt }
            .takeLast(6)
            .map { it.isUser to it.text }
}
