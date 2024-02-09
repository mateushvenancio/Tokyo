package br.com.mateusvenancio.tokyo.ui.presenter.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.mateusvenancio.tokyo.models.OperationType
import br.com.mateusvenancio.tokyo.ui.theme.TokyoTheme
import java.math.BigDecimal

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OperationsFormScreen(navController: NavController) {
    var title by remember { mutableStateOf("") }
    var type by remember { mutableStateOf(OperationType.OUTCOME) }
    var value by remember { mutableStateOf("0") }

    TokyoTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Create operations", color = Color.White) },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                navController.popBackStack()
                            }
                        ) {
                            Icon(
                                Icons.Filled.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    },
                    colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary),
                )
            },
        ) { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(
                        horizontal = 16.dp,
                        vertical = 8.dp
                    )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    OutlinedButton(
                        shape = MaterialTheme.shapes.medium.copy(
                            topEnd = CornerSize(0.dp),
                            bottomEnd = CornerSize(0.dp),
                        ),
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = if (type == OperationType.OUTCOME) {
                                MaterialTheme.colorScheme.primary
                            } else {
                                Color.Transparent
                            }
                        ),
                        modifier = Modifier.weight(1f),
                        onClick = { type = OperationType.OUTCOME },
                    ) {
                        Text(
                            "Outcome",
                            color = if (type == OperationType.OUTCOME) {
                                Color.White
                            } else {
                                Color.Black
                            }
                        )
                    }
                    OutlinedButton(
                        shape = MaterialTheme.shapes.medium.copy(
                            topStart = CornerSize(0.dp),
                            bottomStart = CornerSize(0.dp),
                        ),
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = if (type == OperationType.INCOME) {
                                MaterialTheme.colorScheme.primary
                            } else {
                                Color.Transparent
                            }
                        ),
                        modifier = Modifier.weight(1f),
                        onClick = { type = OperationType.INCOME }
                    ) {
                        Text(
                            "Income",
                            color = if (type == OperationType.INCOME) {
                                Color.White
                            } else {
                                Color.Black
                            }
                        )
                    }
                }
                OutlinedTextField(
                    value = title,
                    onValueChange = { value ->
                        title = value
                    },
                    label = { Text("Title") },
                    modifier = Modifier.fillMaxWidth(),
                )
                Divide()
                OutlinedTextField(
                    value = value,
                    onValueChange = { newValue ->
                        if (newValue.isBlank()) {
                            value = "0"
                            return@OutlinedTextField
                        }
                        if (!isValidBigDecimal(newValue)) {
                            // value = "0"
                            return@OutlinedTextField
                        }
                        value = newValue
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Decimal
                    ),
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
    }
}

@Composable
private fun Divide() {
    Spacer(
        modifier = Modifier
            .height(8.dp)
            .width(8.dp)
    )
}

private fun isValidBigDecimal(value: String): Boolean {
    return try {
        BigDecimal(value)
        true
    } catch (e: Exception) {
        false
    }
}