package com.movie.moviechat.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MemberEntity(var title:String?) {
    @PrimaryKey(autoGenerate = true) var id:Int = 0
}