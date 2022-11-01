package com.afollestad.materialdialogs.datetime.utils

import com.afollestad.materialdialogs.datetime.utils.DayOfWeek.FRIDAY
import com.afollestad.materialdialogs.datetime.utils.DayOfWeek.MONDAY
import com.afollestad.materialdialogs.datetime.utils.DayOfWeek.SATURDAY
import com.afollestad.materialdialogs.datetime.utils.DayOfWeek.SUNDAY
import com.afollestad.materialdialogs.datetime.utils.DayOfWeek.THURSDAY
import com.afollestad.materialdialogs.datetime.utils.DayOfWeek.TUESDAY
import com.afollestad.materialdialogs.datetime.utils.DayOfWeek.WEDNESDAY
import java.util.Calendar

/** @author Aidan Follestad (@afollestad) */
internal enum class DayOfWeek(val rawValue: Int) {
  SUNDAY(Calendar.SUNDAY),
  MONDAY(Calendar.MONDAY),
  TUESDAY(Calendar.TUESDAY),
  WEDNESDAY(Calendar.WEDNESDAY),
  THURSDAY(Calendar.THURSDAY),
  FRIDAY(Calendar.FRIDAY),
  SATURDAY(Calendar.SATURDAY)
}

/** @author Aidan Follestad (@afollestad) */
internal fun Int.asDayOfWeek(): DayOfWeek {
  return DayOfWeek.values()
    .single { it.rawValue == this }
}

/** @author Aidan Follestad (@afollestad) */
internal fun DayOfWeek.andTheRest(): List<DayOfWeek> {
  return mutableListOf<DayOfWeek>().apply {
    for (value in rawValue..SATURDAY.rawValue) {
      add(value.asDayOfWeek())
    }
    for (value in SUNDAY.rawValue until rawValue) {
      add(value.asDayOfWeek())
    }
  }
}

/** @author Aidan Follestad (@afollestad) */
internal fun DayOfWeek.nextDayOfWeek(): DayOfWeek {
  return when (this) {
    SUNDAY -> MONDAY
    MONDAY -> TUESDAY
    TUESDAY -> WEDNESDAY
    WEDNESDAY -> THURSDAY
    THURSDAY -> FRIDAY
    FRIDAY -> SATURDAY
    SATURDAY -> SUNDAY
  }
}
