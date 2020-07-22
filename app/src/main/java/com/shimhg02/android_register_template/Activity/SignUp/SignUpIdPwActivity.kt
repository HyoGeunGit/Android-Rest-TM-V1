package com.shimhg02.android_register_template.Activity.SignUp

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.shimhg02.android_register_template.R
import kotlinx.android.synthetic.main.activity_signup_idpw.*
import org.jetbrains.anko.backgroundResource
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast


class SignUpIdPwActivity : AppCompatActivity() {

    val PREFERENCE = "com.shimhg02.template"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_idpw)
        onBackPressed()
        duplicate_chk_btn.setOnClickListener {
            checkDuplicate()
        }
        checkPassWord()
    }

    fun checkPassWord(){
        val pref = getSharedPreferences(PREFERENCE, MODE_PRIVATE)
        val editor = pref.edit()
        val edit = findViewById(R.id.pw_chk_tv) as EditText
        edit.addTextChangedListener(object: TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (pw_tv.text.toString() != pw_chk_tv.text.toString()) {
                    chk_tv.text = "비밀번호가 일치하지 않습니다."
                    chk_tv.setTextColor(Color.RED)
                } else {
                    chk_tv.text = "일치합니다"
                    chk_tv.setTextColor(Color.GREEN)
                }
            }
            override fun afterTextChanged(s: Editable?) {
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                next_btn.setOnClickListener {
                    if(chk_tv.text.toString() == "" || pw_tv.text.toString() == "" || id_tv.text.toString() == ""){
                        toast("입력란을 전부 입력해주세요.")
                    }
                    else{
                        next_btn.setOnClickListener {
                            editor.putString("id_signup",id_tv.text.toString())
                            editor.putString("pw_signup",pw_tv.text.toString())
                            editor.apply()
                            startActivity<SignUpInfoActivity>()
                            finish()
                        }
                    }
                }
            }

        })
    }

    fun checkDuplicate(){
        //TODO 중복확인 기능 추가예정
//        val pref = getSharedPreferences(PREFERENCE, MODE_PRIVATE)
//        Client.retrofitService.duplicate(id_tv.text.toString()).enqueue(object :
//            Callback<Void> {
//            override fun onResponse(call: Call<Void>?, response: Response<Void>?) {
//                when (response!!.code()) {
//                    200 -> {
//                        duplicateCheckedDiaglog()
//                    }
//                    409 -> {
//                        duplicateNotCheckedDiaglog()
//                    }
//                    500 -> Toast.makeText(this@SignUpIdActivity, "서버 점검중입니다. 잠시 후 다시 시도해 주세요.", Toast.LENGTH_LONG).show()
//                }
//            }
//            override fun onFailure(call: Call<Void>?, t: Throwable?) {
//
//            }
//        })
    }

    fun duplicateCheckedDiaglog(){ //TODO Later When We Get Duplicate
        val pref = getSharedPreferences(PREFERENCE, MODE_PRIVATE)
        var dialog = AlertDialog.Builder(this@SignUpIdPwActivity)
        dialog.setTitle("중복확인 성공")
        dialog.setMessage("사용하실 수 있는 아이디입니다.")
        dialog.setIcon(R.mipmap.ic_launcher)
        dialog.show()
        duplicate_chk_btn.visibility = View.INVISIBLE
        id_tv.inputType = InputType.TYPE_NULL
        id_tv.isFocusable = false
        id_tv.isClickable = false
        id_ti.backgroundResource =
            R.drawable.textinput_locked
        duplicate_chk_btn.visibility = View.GONE
    }
    fun duplicateNotCheckedDiaglog(){
        val pref = getSharedPreferences(PREFERENCE, MODE_PRIVATE)
        val editor = pref.edit()
        var dialog = AlertDialog.Builder(this@SignUpIdPwActivity)
        dialog.setTitle("중복확인 실패")
        dialog.setMessage("사용하실 수 없는 아이디입니다.")
        dialog.setIcon(R.mipmap.ic_launcher)
        dialog.show()
    }

    override fun onBackPressed() {
        //super.onBackPressed()
    }
}