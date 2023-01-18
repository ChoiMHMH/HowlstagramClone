package com.example.howlstagramclone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    var auth : FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = FirebaseAuth.getInstance()
    }
    fun signinAndSignup(){
        auth?.createUserWithEmailAndPassword(email_edittext.text.toString(),password_edittext.text.toString())
            ?.addOnCompleteListener{//회원가입한 결과값을 받아오기 위해서
                task ->
                    if(task.isSuccessful){
                        //Creating a user account
                        //id가 생성되었을 때 필요한 코드
                    }else if(!task.exception?.message.isNullOrEmpty()){//!를 붙여서 부정문으로 만들어주라고함.
                        //로그인 에러가 났을 때 나타나는 부분으로, 에러메세지만 출력하는 코드만.
                    }
            }
    }
}