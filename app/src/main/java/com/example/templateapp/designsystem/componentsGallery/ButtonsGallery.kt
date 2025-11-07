package com.example.templateapp.designsystem.componentsGallery

import ButtonPrimary
import ButtonSecondary
import FloatingActionButton
import SingleChoiceSegmentedButton
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.templateapp.R
import com.example.templateapp.ui.theme.components.TextBody2Regular
import com.example.templateapp.ui.theme.components.TextH3Bold
import com.example.templateapp.ui.theme.grey
import com.example.templateapp.ui.theme.padding8
import com.example.templateapp.ui.theme.spacing2

data class SegmentedButtonOption(val textId: Int)
@Composable
fun DesignSystemButtons(modifier: Modifier = Modifier) {
    val segmentedButtonOptions = listOf(
        SegmentedButtonOption(R.string.design_system_option_1),
        SegmentedButtonOption(R.string.design_system_option_2),
        SegmentedButtonOption(R.string.design_system_option_3)
    )
    var segmentedButtonSelectedOption by remember { mutableStateOf(segmentedButtonOptions[0]) }
    var expanded by remember { mutableStateOf(false) }
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(spacing2),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = !expanded }
                .padding(vertical = padding8),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextH3Bold(text = "Buttons")
            Icon(
                imageVector = if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                contentDescription = null,
                tint = grey
            )
        }
        AnimatedVisibility(visible = expanded) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(spacing2)
            ) {
                ButtonPrimary(
                    modifier = Modifier,
                    text = "ButtonPrimary"
                )
                ButtonSecondary(
                    modifier = Modifier,
                    text = "ButtonSecondary"
                )
                FloatingActionButton(
                    icon = Icons.Default.Add
                )
                SingleChoiceSegmentedButton(
                    options = segmentedButtonOptions,
                    getText = { it.textId },
                    onSelectionChanged = { selectedOption ->
                        segmentedButtonSelectedOption = selectedOption
                    }
                )
                TextBody2Regular(textId= segmentedButtonSelectedOption.textId)
            }
        }
    }
}