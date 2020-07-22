package com.shimhg02.android_register_template.Activity.SignIn

import com.shimhg02.android_register_template.Activity.Main.MainActivity
import com.shimhg02.android_register_template.R
import com.shimhg02.android_register_template.Activity.SignUp.SignUpIdPwActivity
import com.shimhg02.android_register_template.Utils.BaseActivity
import com.shimhg02.android_register_template.network.Data.Users
import com.shimhg02.android_register_template.network.Retrofit.Client
import kotlinx.android.synthetic.main.activity_signin.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * @description 로그인 액티비티
 */


@Suppress("DEPRECATION")
class LoginActivity : BaseActivity() {
    val PREFERENCE = "com.shimhg02.template"
    override var viewId: Int =
        R.layout.activity_signin

    override fun onCreate() {
        val pref = getSharedPreferences(PREFERENCE, MODE_PRIVATE)
        AutoLogin()
        System.out.println("token Test1 : "+ pref.getString("fbToken",""))
        System.out.println("token Test2 : "+ pref.getString("ggToken",""))
        login_btn.setOnClickListener { login() }
        signup_go.setOnClickListener { startActivity<SignUpIdPwActivity>()  }
    }


    private fun AutoLogin(){
        val pref = getSharedPreferences(PREFERENCE, MODE_PRIVATE)
        if (pref.getString("token", "").toString() !== "") {
            Client.retrofitService.autoLogin(pref.getString("token","").toString()).enqueue(object :
                Callback<Users> {
                override fun onResponse(call: Call<Users>?, response: Response<Users>?) {
                    when (response!!.code()) {
                        200 -> {
                            startActivity<MainActivity>()
                            finish()
                        }
                        401 -> toast("자동 로그인 실패, 다시 로그인 해주세요")
                        500 -> toast("서버 에러")
                    }
                }
                override fun onFailure(call: Call<Users>?, t: Throwable?) {

                }
            })
        }
    }

    private fun login() {
        login_btn.isClickable = false
        val pref = getSharedPreferences(PREFERENCE, MODE_PRIVATE)
        val editor = pref.edit()
        Client.retrofitService.logIn(id_tv.text.toString(), pw_tv.text.toString()).enqueue(object : Callback<Users> {
            override fun onResponse(call: Call<Users>?, response: Response<Users>?) {
                when (response!!.code()) {
                    200 -> {
                        startActivity<MainActivity>()
                        finish()
                    }
                    404 -> {
                        login_btn.isClickable = true
                        toast("존재하지 않습니다. 아이디 또는 비밀번호를 확인해주세요.")

                    }
                    500 -> {
                        toast("서버 에러")
                        login_btn.isClickable = true
                    }
                }
            }
            override fun onFailure(call: Call<Users>?, t: Throwable?) {

            }
        })
    }

}