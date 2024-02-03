package br.com.mateusvenancio.tokyo.ui.presenter.screens

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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.mateusvenancio.tokyo.models.Operation
import br.com.mateusvenancio.tokyo.models.OperationType
import br.com.mateusvenancio.tokyo.ui.presenter.components.ItemList
import br.com.mateusvenancio.tokyo.ui.presenter.components.MainCard
import br.com.mateusvenancio.tokyo.ui.theme.TokyoTheme
import java.math.BigDecimal
import java.text.NumberFormat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(operations: List<Operation>) {
    TokyoTheme {
        Scaffold (
            topBar = {
                TopAppBar(
                    title = {
                        Text("All operations", color = Color.White, fontWeight = FontWeight.SemiBold)
                    },
                    colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary),
                )
            },
            floatingActionButton = {
                FloatingActionButton(onClick = {  }) {
                    Icon(Icons.Default.Add, "Floating action button")
                }
            }
        ) { padding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(horizontal = 12.dp)
            ) {
                item {
                    MainCard(
                        title = "Monthly Balance",
                        action = {
                            Icon(Icons.Default.DateRange,"")
                        },
                        modifier = Modifier.padding(vertical = 16.dp)
                    ) {
                        val total = BigDecimal("-544.30")
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                "February",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                            )
                            Text(
                                NumberFormat.getCurrencyInstance().format(total.toLong()),
                                fontWeight = FontWeight.SemiBold,
                                color = if (total > BigDecimal.ZERO) {
                                    Color.Black
                                } else {
                                    Color.Red
                                }
                            )
                        }
                    }
                }
                items(operations.count()) {
                    val currentItem = operations[it]

                    val icon = when ( currentItem.type) {
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

                    ItemList(
                        icon = icon,
                        iconBackgroundColor = iconColor,
                        title = currentItem.title,
                        trailing = formattedValue,
                    )
                }
            }
        }
    }
}