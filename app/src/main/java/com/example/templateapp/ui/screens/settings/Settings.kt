package com.example.templateapp.ui.screens.settings

import ButtonPrimary
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import com.example.templateapp.R
import com.example.templateapp.ui.theme.components.TextH1Bold
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@Composable
fun Settings(
    modifier: Modifier = Modifier,
    goDesignSystem: () -> Unit,
    settingsViewModel: SettingsViewModel = hiltViewModel()
) {

    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TextH1Bold(textId = R.string.settings_title)
            ButtonPrimary(
                modifier = Modifier,
                textId = R.string.design_system_title,
                onClick = goDesignSystem
            )
        }
    }
}

@HiltViewModel
class SettingsViewModel @Inject constructor() : ViewModel() {}
