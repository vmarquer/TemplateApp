import androidx.annotation.StringRes
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.templateapp.ui.theme.components.TextBody1Bold
import com.example.templateapp.ui.theme.padding8
import com.example.templateapp.ui.theme.primary
import com.example.templateapp.ui.theme.white

@Composable
fun ButtonPrimary(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    text: String = "",
    @StringRes textId: Int = -1
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(padding8),
        colors = ButtonDefaults.buttonColors(containerColor = primary),
    ) {
        TextBody1Bold(
            text = (if (textId == -1) text else stringResource(id = textId)),
            color = white,
            modifier = Modifier
        )
    }
}