package br.com.mateusvenancio.tokyo.ui.presenter.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.platform.LocalFocusManager
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDatePicker(
    onChanged: (LocalDate) -> Unit
) {
    val focusManager = LocalFocusManager.current
    val datePickerState = rememberDatePickerState()

    var show by remember { mutableStateOf(false) }
    var selectedDate by remember { mutableStateOf(LocalDate.now()) }

    Column(

    ) {
        if (show) {
            DatePickerDialog(
                onDismissRequest = { show = false },
                confirmButton = {
                    Button(
                        onClick = {
                            datePickerState
                                .selectedDateMillis?.let { millis ->
                                    onChanged(
                                        Instant.ofEpochMilli(millis)
                                            .atZone(ZoneId.systemDefault())
                                            .toLocalDate()
                                    )
                                }
                            show = false
                        }
                    ) {
                        Text(text = "Escolher data")
                    }
                }
            ) {
                DatePicker(state = datePickerState)
            }
        }
        TextField(
            value = selectedDate.format(
                DateTimeFormatter.ofPattern("dd/MM/yyyy")
            ),
            onValueChange = { },
            readOnly = false,
            label = { Text("Select date") },
            modifier = Modifier
                .fillMaxWidth()
                .onFocusEvent {
                    if (it.isFocused) {
                        show = true
                        focusManager.clearFocus(force = true)
                    }
                },
        )
    }
}