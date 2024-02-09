package br.com.mateusvenancio.tokyo.ui.presenter.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.mateusvenancio.tokyo.core.ServiceLocator
import br.com.mateusvenancio.tokyo.core.navigation.Screens
import br.com.mateusvenancio.tokyo.models.OperationType
import br.com.mateusvenancio.tokyo.ui.presenter.components.ItemList
import br.com.mateusvenancio.tokyo.ui.presenter.components.MainCard
import br.com.mateusvenancio.tokyo.ui.theme.TokyoTheme
import java.text.NumberFormat
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    val operationsState = ServiceLocator.operationsState

    val datePickerState = rememberDatePickerState()
    var showDatePicker by remember { mutableStateOf(false) }

    TokyoTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            "All operations",
                            color = Color.White,
                            fontWeight = FontWeight.SemiBold
                        )
                    },
                    actions = {
                        IconButton(
                            onClick = {
                                ServiceLocator.operationsState.refreshOperations()
                            }
                        ) {
                            Icon(
                                Icons.Default.Refresh,
                                "Refresh Icon"
                            )
                        }
                    },
                    colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary),
                )
            },
            floatingActionButton = {
                FloatingActionButton(onClick = { navController.navigate(Screens.OperationsFormScreen.route) }) {
                    Icon(Icons.Default.Add, "Floating action button")
                }
            }
        ) { padding ->
            if (showDatePicker) {
                DatePickerDialog(
                    onDismissRequest = { showDatePicker = false },
                    confirmButton = {
                        Button(
                            onClick = {
                                datePickerState
                                    .selectedDateMillis?.let { millis ->
                                        operationsState.changeDate(
                                            Instant.ofEpochMilli(millis)
                                                .atZone(ZoneId.systemDefault())
                                                .toLocalDate()
                                        )
                                    }
                                showDatePicker = false
                            }
                        ) {
                            Text(text = "Escolher data")
                        }
                    }
                ) {
                    DatePicker(state = datePickerState)
                }
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(horizontal = 12.dp)
            ) {
                item {
                    CurrentHomeCard(
                        currentDate = operationsState.selectedDate,
                        total = operationsState.total
                    ) {
                        showDatePicker = true
                    }
                }
                items(operationsState.operations.count()) {
                    val currentItem = operationsState.operations[it]

                    val icon = when (currentItem.type) {
                        OperationType.INCOME -> Icons.Default.KeyboardArrowUp
                        OperationType.OUTCOME -> Icons.Default.KeyboardArrowDown
                    }
                    val iconColor = when (currentItem.type) {
                        OperationType.INCOME -> Color.Green
                        OperationType.OUTCOME -> Color.Red
                    }
                    val formattedValue = NumberFormat.getCurrencyInstance().format(
                        currentItem.value
                    )
                    val formattedDate = currentItem.date.format(
                        DateTimeFormatter.ofPattern("dd/MM/yyyy")
                    )

                    ItemList(
                        icon = icon,
                        iconBackgroundColor = iconColor,
                        title = currentItem.title,
                        trailing = formattedValue,
                        subtitle = formattedDate,
                    )
                }
            }
        }
    }
}

@Composable
private fun CurrentHomeCard(currentDate: LocalDate, total: Float, onSelect: () -> Unit) {
    MainCard(
        title = "Monthly Balance",
        action = {
            Icon(Icons.Default.DateRange, "")
        },
        modifier = Modifier.padding(vertical = 16.dp).clickable {
            onSelect()
        }
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                currentDate.format(
                    DateTimeFormatter.ofPattern("MMMM yyyy")
                ),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(
                NumberFormat.getCurrencyInstance().format(total),
                fontWeight = FontWeight.SemiBold,
                color = if (total > 0) {
                    Color.Black
                } else {
                    Color.Red
                }
            )
        }
    }
}