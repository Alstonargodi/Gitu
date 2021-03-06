package com.example.core.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.core.data.local.entity.userlist.GithubUserList
import kotlinx.coroutines.flow.Flow

@Dao
interface ListUserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertListUser(user: List<GithubUserList>)

    @Query("select*from githubuserlist order by id asc")
    fun readListUser(): Flow<List<GithubUserList>>
}