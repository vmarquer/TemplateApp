package com.example.templateapp.designsystem.componentsGallery

import ButtonPrimary
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.templateapp.ui.theme.components.TextH3Bold
import com.example.templateapp.ui.theme.spacing10


@Composable
fun DesignSystemButtons(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(spacing10),
    ) {
        TextH3Bold(text = "Buttons")

        ButtonPrimary(
            modifier = Modifier,
            text = "ButtonPrimary"
        )
    }
}