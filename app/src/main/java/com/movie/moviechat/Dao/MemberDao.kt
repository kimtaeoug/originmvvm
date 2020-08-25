package com.movie.moviechat.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.movie.moviechat.Entity.MemberEntity

@Dao
interface MemberDao {
    @Query("SELECT * FROM MemberEntity")
    fun getAll(): LiveData<List<MemberEntity>>

    @Insert
    fun insert(member: MemberEntity)

    @Update
    fun upate(member: MemberEntity)

    @Delete
    fun delete(member: MemberEntity)
}