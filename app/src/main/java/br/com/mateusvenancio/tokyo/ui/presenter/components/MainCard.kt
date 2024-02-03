package br.com.mateusvenancio.tokyo.ui.presenter.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.mateusvenancio.tokyo.ui.theme.TokyoTheme

@Composable
fun MainCard(
    title: String,
    modifier: Modifier = Modifier,
    action: (@Composable () -> Unit)? = null,
    content: @Composable() () -> Unit
) {
    TokyoTheme {
        Surface(
            modifier = Modifier.fillMaxWidth().then(modifier),
            shape = MaterialTheme.shapes.medium,
            shadowElevation = 12.dp
        ) {
            Column (modifier = Modifier.padding(8.dp)) {
                Row {
                    Text(title, style = TextStyle(fontSize = 20.sp))
                    Spacer(modifier = Modifier.weight(1f))
                    if (action != null) action()
                }
                content()
            }
        }
    }
}

@Preview
@Composable
fun MainCardPreview() {
    MainCard("Card 1") {
        Text("Item 1")
        Text("Item 2")
        Text("Item 3")
        Text("Item 4")
        Text("Item 5")
    }
}