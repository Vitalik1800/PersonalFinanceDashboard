package com.vs18.personalfinancedashboard.data.repository

import com.vs18.personalfinancedashboard.data.db.dao.TransactionDao
import com.vs18.personalfinancedashboard.data.db.entity.TransactionEntity
import com.vs18.personalfinancedashboard.data.model.TransactionType

class TransactionRepository(
    private val dao: TransactionDao
) {

    fun getAllTransactions() =
        dao.getAllTransactions()

    suspend fun addTransaction(
        amount: Double,
        type: TransactionType,
        category: String,
        note: String,
        date: Long
    ) {

        dao.insert(
            TransactionEntity(
                amount = amount,
                type = type,
                category = category,
                note = note,
                date = date
            )
        )

    }

    suspend fun updateTransaction(
        transaction: TransactionEntity
    ) {
        dao.update(transaction)
    }

    suspend fun deleteTransaction(
        transaction: TransactionEntity
    ) {
        dao.delete(transaction)
    }

    suspend fun getTransactionById(
        id: Int
    ) = dao.getTransactionById(id)

    suspend fun clearAllTransactions() {
        dao.clearAll()
    }

}