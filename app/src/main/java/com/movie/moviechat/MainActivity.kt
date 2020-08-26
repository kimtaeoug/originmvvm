package com.movie.moviechat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.movie.moviechat.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //databinding - xml파일에 data를 연결(binding)하는것
        //activity에서 뷰를 따로 정의하지 않아도 되고, data를 view에 연결해 두면, data가 변할 때 따로 세팅해주지 않아도 변경 가능
        //ActivityMainBinding은 데이터바인딩을 사용하면 레이아웃을 토대로 자동으로 생성해주는 클래스
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this,R.layout.activity_main)
        //lifecycleOwner는 livedata의 lifecycleObserber역할을 해줌
        binding.lifecycleOwner = this
        //viewmodel을 갖고옴
        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.viewModel = viewModel
    }

    //전제
    //activity에 databinding을 정의하고, databinding에 lifecycleOwner를 지정해야 livedata를 사용할 수 있고,
    //viewmodel을 지정해야 viewmodel사용가능
    //입력
        //값입력
        //xml의 editText의 android:text="@={viewModel.newTodo}"로 들어감- viewmodel에서 선언한 newtodo에 값이 들어감
        //버튼 누름
        //button의 android:onClick="@{()->viewModel.insert(viewModel.newTodo)}"작동 -> viewmodel의 insert함수 작동
        //viewmodel의 insert함수내 viewmodelScope안의 코드작동(viewmodelscope안에 있기 때문에 백그라운드에서 작동)
        //db.memberDao().insert(MemberEntity(todo))작동
        //viewmodel에 선언해놓은 db의 memberdao의 insert함수 실행(db를 build할때 AppDatabase를 db로 설정해놓음)
        //db로 지정해놓은 appDatabase에 입력값이 저장됨
    //출력
        //textview의 android:text="@{viewModel.members.toString()}" -> viewmodel의 members를 갖고옴
        //viewmodel의 members는 LiveData<List<MemberEntity>>임
        //members는 getAll()함수로 초기화됨 -> getAll()호출 ->  db.memberDao().getAll()호출
        //getAll()함수는 @Query("SELECT * FROM MemberEntity")인 쿼리
        //MemberEntity의 데이터들을 갖고옴
}