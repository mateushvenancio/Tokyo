package br.com.mateusvenancio.tokyo.ui.presenter.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.mateusvenancio.tokyo.ui.theme.TokyoTheme

@Composable
fun AppBar() {
    TokyoTheme {
        Surface (
            modifier = Modifier.fillMaxWidth(),
            color = Color.Blue,
            shadowElevation = 12.dp
        ) {
            Row (
                modifier = Modifier.height(36.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AppBarIconButton(
                    onClick = { /*TODO*/ },
                    icon = Icons.Filled.Menu,
                    contentDescription = "Menu Item"
                )
                AppBarIconButton(
                    onClick = { /*TODO*/ },
                    icon = Icons.Default.KeyboardArrowLeft,
                    contentDescription = "Left Arrow",
                    modifier = Modifier.weight(1f)
                )
                MonthSelector(
                    modifier = Modifier.weight(1f)
                )
                AppBarIconButton(
                    onClick = { /*TODO*/ },
                    icon = Icons.Default.KeyboardArrowRight,
                    contentDescription = "Right Arrow",
                    modifier = Modifier.weight(1f)
                )
                AppBarIconButton(
                    onClick = { /*TODO*/ },
                    icon = Icons.Default.MoreVert,
                    contentDescription = "Menu More"
                )
            }
        }
    }
}

@Composable
fun AppBarIconButton(icon: ImageVector, contentDescription: String, modifier: Modifier = Modifier, onClick: () -> Unit) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            icon,
            contentDescription = contentDescription,
            tint = Color.White
        )
    }
}

@Composable
fun MonthSelector(modifier: Modifier = Modifier) {
    Text("Fevereiro",
        color = Color.White,
        modifier = modifier,
        textAlign = TextAlign.Center,
    )
}

@Composable
@Preview
fun AppBarPreview() {
    AppBar()
}