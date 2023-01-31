package com.example.howlstagramclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    var auth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = FirebaseAuth.getInstance()
        email_login_button.setOnClickListener {
            signinAndSignup()
        }
    }


    fun signinAndSignup() {
        auth?.createUserWithEmailAndPassword(
            email_edittext.text.toString(),
            password_edittext.text.toString()
        )
            ?.addOnCompleteListener {//회원가입한 결과값을 받아오기 위해서
                    task ->
                if (task.isSuccessful) {
                    //Creating a user account
                    //id가 생성되었을 때 필요한 코드
                    moveMainPage(task.result.user)
                } else if (!task.exception?.message.isNullOrEmpty()) {//!를 붙여서 부정문으로 만들어주라고함.
                    Toast.makeText(this,task.exception?.message,Toast.LENGTH_LONG).show()
                    //로그인 에러가 났을 때 나타나는 부분으로, 에러메세지만 출력하는 코드만.
                } else {
                    //Login if you have account//회원가입도 아니고 에러메세지도 아닐경우 로그인으로 빠지게
                    signinEmail()
                }
            }
    }


    fun signinEmail() {
        auth?.signInWithEmailAndPassword(
            email_edittext.text.toString(),
            password_edittext.text.toString()
        )
            ?.addOnCompleteListener {//회원가입한 결과값을 받아오기 위해서
                    task ->
                if (task.isSuccessful) {
                    //Login//id와 pw가 맞았을 때
                    moveMainPage(task.result.user)
                } else {
                    //Show the error message
                    Toast.makeText(this,task.exception?.message,Toast.LENGTH_LONG).show()
                }
            }
    }

    //로그인이 성공하면 다음 페이지로 넘어가는 함수
    fun moveMainPage(user:FirebaseUser?){
        if(user != null){
            startActivity(Intent(this,MainActivity::class.java))
        }
    }

}