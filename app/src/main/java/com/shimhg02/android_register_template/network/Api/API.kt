package com.shimhg02.android_register_template.network.Api


import com.shimhg02.android_register_template.network.Data.Users
import retrofit2.Call
import retrofit2.http.*

/**
 * @description API 관리 인터페이스
 */

interface API {

    @POST("/auth/user/login")
    @FormUrlEncoded
    fun logIn(@Field("userID") id : String, @Field("password") pw : String) :  Call<Users>


    @POST("/auth/user/my")
    @FormUrlEncoded
    fun autoLogin(@Header("Authorization") token : String) :  Call<Users>

    @POST("/auth/user")
    @FormUrlEncoded
    fun logUp(@Field("userName") name: String?,
              @Field("userID") id: String?,
              @Field("password") pw: String?,
              @Field("phone") phoneNum: String?,
              @Field("birth") birth: String?,
              @Field("email") email: String?,
              @Field("nickName") nickName: String?,
              @Field("sex") sex: Boolean?,
              @Field("img") profileImg: String?
    ): Call<Users>

}