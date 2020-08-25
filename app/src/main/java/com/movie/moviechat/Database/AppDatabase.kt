package com.movie.moviechat.Database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.movie.moviechat.Dao.MemberDao
import com.movie.moviechat.Entity.MemberEntity

@Database(entities = [MemberEntity::class],version=1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun memberDao(): MemberDao
}