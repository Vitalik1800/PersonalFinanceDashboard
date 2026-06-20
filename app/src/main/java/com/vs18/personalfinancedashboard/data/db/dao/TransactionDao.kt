package com.vs18.personalfinancedashboard.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.vs18.personalfinancedashboard.data.db.entity.TransactionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {

    @Query(
        "SELECT * FROM transactions ORDER BY date DESC"
    )
    fun getAllTransactions():
            Flow<List<TransactionEntity>>

    @Query(
        "SELECT * FROM transactions WHERE id = :id"
    )
    suspend fun getTransactionById(
        id: Int
    ): TransactionEntity

    @Insert
    suspend fun insert(
        transaction: TransactionEntity
    )

    @Update
    suspend fun update(
        transaction: TransactionEntity
    )

    @Delete
    suspend fun delete(
        transaction: TransactionEntity
    )

    @Query(
        "DELETE FROM transactions"
    )
    suspend fun clearAll()

}