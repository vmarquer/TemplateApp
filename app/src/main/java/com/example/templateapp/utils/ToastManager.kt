package com.example.templateapp.utils

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import com.example.templateapp.ui.theme.success

object ToastManager {
    var message by mutableStateOf<String?>(null)
        private set

    var color: Color = success

    fun showMessage(newMessage: String, color: Color) {
        message = newMessage
        this.color = color
    }

    fun clearMessage() {
        message = null
    }
}