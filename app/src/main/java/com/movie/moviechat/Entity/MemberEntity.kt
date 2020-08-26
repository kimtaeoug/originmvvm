package com.movie.moviechat.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey

//Entity - db에 사용할 변수들 정의 
//data class를 사용햐 getter/setter 생략
@Entity
data class MemberEntity(var title:String?) {
    @PrimaryKey(autoGenerate = true) var id:Int = 0
}