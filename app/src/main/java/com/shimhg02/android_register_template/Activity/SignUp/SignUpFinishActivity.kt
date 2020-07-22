package com.shimhg02.android_register_template.Activity.SignUp


import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.shimhg02.android_register_template.Activity.Main.MainActivity
import com.shimhg02.android_register_template.R
import com.shimhg02.android_register_template.network.Data.Users
import com.shimhg02.android_register_template.network.Retrofit.Client
import kotlinx.android.synthetic.main.activity_signup_finish.*
import org.jetbrains.anko.startActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SignUpFinishActivity : AppCompatActivity() {

    val PREFERENCE = "com.shimhg02.template"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_finish)
        val pref = getSharedPreferences(PREFERENCE, MODE_PRIVATE)
        onBackPressed()
        if(pref.getBoolean("sex_signup",false) == false){
            profile_img.setImageResource(R.drawable.profile_male)
        }
        else{
            profile_img.setImageResource(R.drawable.profile_female)
        }
        next_btn.setOnClickListener {
            signup()
        }

    }

    fun signup(){
        val pref = getSharedPreferences(PREFERENCE, MODE_PRIVATE)
        val editor = pref.edit()
        Client.retrofitService.logUp(
            pref.getString("name_signup",""),
            pref.getString("id_signup",""),
            pref.getString("pw_signup",""),
            pref.getString("phone_signup",""),
            pref.getString("birth_signup",""),
            pref.getString("mail_signup",""),
            nick_tv.text.toString(),
            pref.getBoolean("sex_signup", false),
            ""  //TODO When Server Get Finish Add Profile Add Profile Img
        )
            .enqueue(object : Callback<Users> {
                override fun onResponse(call: Call<Users>?, response: Response<Users>?) {
                    when (response!!.code()) {
                        201 -> {
                            editor.putString("userName", response.body()!!.data.nickName.toString())
                            editor.putString("token", response.body()!!.data.token.toString())
                            editor.apply()
                            startActivity<MainActivity>()
                            finish()
                        }
                        404 -> {
                            Toast.makeText(
                                this@SignUpFinishActivity,
                                "중복된 회원입니다.",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                        500 -> Toast.makeText(this@SignUpFinishActivity, "서버에러", Toast.LENGTH_LONG)
                            .show()
                    }
                }

                override fun onFailure(call: Call<Users>?, t: Throwable?) {

                }
            })
    }

    override fun onBackPressed() {
        //super.onBackPressed()
    }
}