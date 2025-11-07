package com.example.templateapp.designsystem

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.templateapp.designsystem.componentsGallery.DesignSystemButtons
import com.example.templateapp.designsystem.componentsGallery.DesignSystemCards
import com.example.templateapp.designsystem.componentsGallery.DesignSystemColors
import com.example.templateapp.designsystem.componentsGallery.DesignSystemInputs
import com.example.templateapp.designsystem.componentsGallery.DesignSystemLabels
import com.example.templateapp.designsystem.componentsGallery.DesignSystemOthers
import com.example.templateapp.ui.theme.padding16
import com.example.templateapp.ui.theme.spacing16

@Composable
fun DesignSystem(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(padding16)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(spacing16)
    ) {
        DesignSystemButtons()
        DesignSystemLabels()
        DesignSystemInputs()
        DesignSystemColors()
        DesignSystemCards()
        DesignSystemOthers()
    }
}