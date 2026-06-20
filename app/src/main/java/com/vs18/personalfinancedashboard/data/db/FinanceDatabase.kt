package com.vs18.personalfinancedashboard.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.vs18.personalfinancedashboard.data.db.dao.TransactionDao
import com.vs18.personalfinancedashboard.data.db.entity.TransactionEntity

@Database(
    entities = [
        TransactionEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    Converters::class
)
abstract class FinanceDatabase :
        RoomDatabase() {

    abstract fun transactionDao():
            TransactionDao

}