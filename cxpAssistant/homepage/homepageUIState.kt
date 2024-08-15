package com.google.ai.cxpAssistant.homepage

data class homepageUIState(
    val page: Page = Page.HOME
)

enum class Page {
    HOME,
    CHAT
}
