package br.com.mateusvenancio.tokyo.ui.presenter.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.mateusvenancio.tokyo.ui.theme.TokyoTheme

@Composable
fun ItemList(
    icon: ImageVector,
    iconBackgroundColor: Color = MaterialTheme.colorScheme.primary,
    title: String,
    subtitle: String? = null,
    trailing: String,
) {
    TokyoTheme {
        Surface {
            Row (
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 8.dp).fillMaxWidth()
            ) {
                Icon(
                    icon,
                    contentDescription = "Icon",
                    tint = Color.White,
                    modifier = Modifier
                        .height(40.dp)
                        .width(40.dp)
                        .clip(CircleShape)
                        .background(iconBackgroundColor)
                        .padding(8.dp)
                )
                Column (
                    modifier = Modifier
                        .wrapContentSize()
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(horizontal = 8.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(title, maxLines = 2)
                    if (subtitle != null) Text(subtitle,
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                    )
                }
                Text(trailing)
            }
        }
    }
}

@Preview
@Composable
fun ItemListPreview() {
    ItemList(
        icon = Icons.Default.Add,
        title = "Receitas",
        trailing = "R$ 25,34",
    )
}