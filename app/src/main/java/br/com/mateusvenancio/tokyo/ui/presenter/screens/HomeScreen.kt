package br.com.mateusvenancio.tokyo.ui.presenter.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDateRangePickerState
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
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    val operationsState = ServiceLocator.operationsState

    val datePickerState = rememberDateRangePickerState()
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
                SelectDate { initial, final ->
                    operationsState.changeDates(initial, final)
                    showDatePicker = false
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
                        initialDate = operationsState.initialDate,
                        finalDate = operationsState.finalDate,
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
private fun CurrentHomeCard(
    initialDate: LocalDate,
    finalDate: LocalDate,
    total: Float,
    onSelect: () -> Unit
) {
    MainCard(
        title = "Monthly Balance",
        action = {
            Icon(Icons.Default.DateRange, "")
        },
        modifier = Modifier
            .padding(vertical = 16.dp)
            .clickable {
                onSelect()
            }
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
            val formattedInitialDate = initialDate.format(formatter)
            val formattedFinalDate = finalDate.format(formatter)

            Text(
                text = "$formattedInitialDate - $formattedFinalDate",
                fontSize = 18.sp,
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SelectDate(confirm: (initialDate: LocalDate, finalDate: LocalDate) -> Unit) {
    val datePickerState = rememberDateRangePickerState()
    var showDatePicker by remember { mutableStateOf(false) }

    DatePickerDialog(
        onDismissRequest = { showDatePicker = false },
        confirmButton = { }
    ) {
        Box(
            modifier = Modifier.padding(16.dp),
            contentAlignment = Alignment.BottomEnd
        ) {
            DateRangePicker(
                state = datePickerState,
                title = null,
                headline = {
                    val defaultLabel = "Select date"

                    val initialLabel = datePickerState.selectedStartDateMillis?.let {
                        return@let millisToString(it)
                    } ?: defaultLabel

                    val finalLabel = datePickerState.selectedEndDateMillis?.let {
                        return@let millisToString(it)
                    } ?: defaultLabel

                    Text("$initialLabel - $finalLabel")
                }
            )
            Button(
                onClick = {
                    val initialDate = millisToDate(datePickerState.selectedStartDateMillis)
                    val finalDate = millisToDate(datePickerState.selectedEndDateMillis)

                    val isNotNull = initialDate != null && finalDate != null
                    val isRangeValid = initialDate?.isBefore(finalDate) ?: false

                    if (isNotNull && isRangeValid) {
                        confirm(initialDate!!, finalDate!!)
                    }
                }
            ) {
                Text("Done")
            }
        }
    }
}

private fun millisToString(millis: Long): String {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = millis
    val format = SimpleDateFormat("dd/MM/yyyy")
    return format.format(calendar.timeInMillis)
}

private fun millisToDate(millis: Long?): LocalDate? {
    if (millis == null) return null
    return Instant.ofEpochMilli(millis)
        .atZone(ZoneId.systemDefault())
        .toLocalDate()
}