package com.vs18.personalfinancedashboard.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vs18.personalfinancedashboard.data.model.TransactionType

@Entity(
    tableName = "transactions"
)
data class TransactionEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val amount: Double,

    val type: TransactionType,

    val category: String,

    val note: String,

    val date: Long

)