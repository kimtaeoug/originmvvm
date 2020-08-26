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


//뷰모델사용방법 - AndroidViewModel을 상송받는 뷰모델 생성 -> 뷰모델 클래스에 Room db객체를 넣음 -> 뷰모델에서 사용할 함수들 정의
//->activity에 뷰모델 정의
class MainViewModel(application: Application): AndroidViewModel(application) {
    val db = Room.databaseBuilder(
        application,
        AppDatabase::class.java, "database-name"
    ).build()
    //Livedata - data의 변경을 관찰할 수 있는 DataHolder클래스
    //activity, fragment등의 생명주기를 인식해 생명주기 내에서만 동작하는 요소로 생명주기가 종료되면 같이 삭제됨->
    //메모리 누출이 없고, 생명주기에 따른 데이터 관리를 개발자가 하지 않아도 됨
    //livedata가 생명주기를 인식하는 방법 - lifecycleowner
    //xml에 사용할 변수 선언
    var members: LiveData<List<MemberEntity>>
    init {
        members = getAll()
    }
    //dao나 viewmodel의 함수 앞에 suspend를 붙이면 비동기로 동작하게함(하지만 무조건 사용해야하는 함수가 됨)
    fun getAll(): LiveData<List<MemberEntity>> {
        return db.memberDao().getAll()
    }

    //xml에서 insert에 사용할 변수 선언
    var newTodo: String? =null
    fun insert(todo:String){
        //Dispatchers.IO - 백그라운드 스레드
        //viewModelScope - 백그라운드 스레드에서 네트워크를 요청하는 코루틴을 실행
        viewModelScope.launch(Dispatchers.IO) {
            db.memberDao().insert(MemberEntity(todo))
        }
    }
}