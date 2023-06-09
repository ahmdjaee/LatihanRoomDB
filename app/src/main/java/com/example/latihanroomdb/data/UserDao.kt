package com.example.latihanroomdb.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {
    @Insert (onConflict = OnConflictStrategy.IGNORE)
    fun addUser (user:User)

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData (): LiveData<List<User>>

    @Update
    suspend fun updateUser (user: User)

    @Delete
    suspend fun deleteUser (user: User)
}