package com.movie.moviechat.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.movie.moviechat.Entity.MemberEntity

//Dao(DataAccessObject) - Entity에 어떤 동작을 할지 정의
//Dao아래 interface로 사용할 함수들 정의(@Insert등)
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