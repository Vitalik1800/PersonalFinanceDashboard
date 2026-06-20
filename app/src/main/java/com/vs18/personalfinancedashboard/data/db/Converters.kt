package com.vs18.personalfinancedashboard.data.db

import androidx.room.TypeConverter
import com.vs18.personalfinancedashboard.data.model.TransactionType

class Converters {

    @TypeConverter
    fun fromTransactionType(
        value: TransactionType
    ): String = value.name

    @TypeConverter
    fun toTransactionType(
        value: String
    ): TransactionType =
        TransactionType.valueOf(value)

}