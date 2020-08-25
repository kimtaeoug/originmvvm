package com.movie.moviechat

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.movie.moviechat.Database.AppDatabase
import com.movie.moviechat.Entity.MemberEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application) {
    val db = Room.databaseBuilder(
        application,
        AppDatabase::class.java, "database-name"
    ).build()

    var members: LiveData<List<MemberEntity>>
    init {
        members = getAll()
    }

    fun getAll(): LiveData<List<MemberEntity>> {
        return db.memberDao().getAll()
    }

    //insert에 사용할 변수
    var newTodo: String? =null
    fun insert(todo:String){
        viewModelScope.launch(Dispatchers.IO) {
            db.memberDao().insert(MemberEntity(todo))
        }
    }
}