package br.com.mateusvenancio.tokyo.core.formatters

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun dateToDDMMYYYY(date: LocalDate): String {
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    return date.format(formatter)
}