/**
 * Designed and developed by Aidan Follestad (@afollestad)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.afollestad.materialdialogs.datetime.utils

import android.widget.TimePicker
import java.util.Calendar
import java.util.GregorianCalendar

internal fun isFutureTime(
  datePicker: android.widget.DatePicker,
  timePicker: TimePicker
): Boolean {
  val now = Calendar.getInstance()
  val dateTime = toCalendar(datePicker, timePicker)
  return dateTime.timeInMillis >= now.timeInMillis
}

internal fun TimePicker.isFutureTime(): Boolean {
  val now = Calendar.getInstance()
  return toCalendar().timeInMillis >= now.timeInMillis
}

internal fun android.widget.DatePicker.isFutureDate(): Boolean {
  val now = Calendar.getInstance()
  return getDate().timeInMillis >= now.timeInMillis
}

internal fun TimePicker.toCalendar(): Calendar {
  val now = Calendar.getInstance()
  return GregorianCalendar(
      now.get(Calendar.YEAR),
      now.get(Calendar.MONTH),
      now.get(Calendar.DAY_OF_MONTH),
      hour(),
      minute()
  )
}

internal fun toCalendar(
  datePicker: android.widget.DatePicker,
  timePicker: TimePicker
): Calendar {
  return datePicker.getDate().apply {
    set(Calendar.HOUR_OF_DAY, timePicker.hour())
    set(Calendar.MINUTE, timePicker.minute())
  }
}

internal fun android.widget.DatePicker.getDate(): Calendar {
  val year = year
  val month = month
  val day = dayOfMonth
  return GregorianCalendar(year, month, day)
}

internal fun android.widget.DatePicker.setMinDate(calendar: Calendar) {
  minDate = calendar.timeInMillis
}

internal fun android.widget.DatePicker.setMaxDate(calendar: Calendar) {
  maxDate = calendar.timeInMillis
}

internal fun android.widget.DatePicker.setDate(calendar: Calendar) {
  updateDate(
    calendar.get(Calendar.YEAR),
    calendar.get(Calendar.MONTH),
    calendar.get(Calendar.DAY_OF_MONTH)
  )
}

internal fun android.widget.DatePicker.init(
  calendar: Calendar,
  listener: (previous: Calendar, now: Calendar) -> Unit
) {
  var previous = GregorianCalendar(
    calendar.get(Calendar.YEAR),
    calendar.get(Calendar.MONTH),
    calendar.get(Calendar.DAY_OF_MONTH)
  )
  init(
    calendar.get(Calendar.YEAR),
    calendar.get(Calendar.MONTH),
    calendar.get(Calendar.DAY_OF_MONTH)
  ) { _, year, month, day ->
    val now = GregorianCalendar(year, month, day)
    listener(previous, now)
    previous = now
  }
}