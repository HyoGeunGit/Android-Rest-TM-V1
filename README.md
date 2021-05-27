# Android-Rest-TM-V1

안드로이드용 RESTFUL API에 맞춘 로그인 템플릿입니다.

# Uses Library, Tech Spec
Kotlin, anko, Retrofit2, okhttp3, TedKeyBoardObserver Library, Material Design

# Server API
### auth

-   POST /auth/user : 회원가입

> Body

    userID: 유저 아이디 [String] REQUIRE
    password: 유저 비밀번호 [String] REQUIRE

    username?: 유저 이름 [String]
    email?: 이메일 [String]

> Response

    result: 결과 [Boolean]
    data: {
        _id: 고유 번호 [String]
        userID: 유저 아이디 [String]
        ...
    }

-   GET /auth/user

> Response

    result: 결과 [Boolean]
    data: [{
        _id: 고유 번호 [String]
        userID: 유저 아이디 [String]
        ...
    }]

-   GET /auth/user/:\_id

> Response

    result: 결과 [Boolean]
    data: {
        _id: 고유 번호 [String]
        userID: 유저 아이디 [String]
        ...
    }

-   PUT /auth/user/:\_id

> Header - Token

    Authorization: 유저 토큰 [String]

> Body

    username?: 유저 이름 [String]
    email?: 이메일 [String]

> Response

    result: 결과 [Boolean]
    data: [{
        _id: 고유 번호 [String]
        userID: 유저 아이디 [String]
        ...
    }]

-   DELETE /auth/user/:\_id

> Header - Token

    Authorization: 유저 토큰 [String]

> Response

    result: 결과 [Boolean]
    data: {
        _id: 고유 번호 [String]
        userID: 유저 아이디 [String]
        ...
    }

-   POST /auth/user/login

> Body

    userID: 유저 아이디 [String] REQUIRE
    password: 유저 비밀번호 [String] REQUIRE

> Response - Token

    result: 결과 [Boolean]
    data: 유저 토큰 [String]

> Response - Session

    result: 결과 [Boolean]
    data: {
        _id: 고유 번호 [String]
        userID: 유저 아이디 [String]
        ...
    }

-   POST /auth/user/logout

> Header - Token

    Authorization: 유저 토큰 [String]

> Response

    result: 결과 [Boolean]

-   POST /auth/user/:\_id/reset-password

> Header - Token

    Authorization: 유저 토큰 [String]

> Body

    password: 새 비밀번호 [String]

> Response

    result: 결과 [Boolean]
    data: {
        _id: 고유 번호 [String]
        userID: 유저 아이디 [String]
        ...
    }

-   POST /auth/user/:\_id/change-profile-image

> Header - Token

    Authorization: 유저 토큰 [String]

> Body

    img: BASE64 이미지 문자열 [String]

> Response

    result: 결과 [Boolean]
    data: {
        _id: 고유 번호 [String]
        userID: 유저 아이디 [String]
        ...
    }

-   POST /auth/user/token

> Header - Token

    Authorization: 유저 토큰 [String]

> Response

    result: 결과 [Boolean]
    data: 새 유저 토큰 [String]

-   GET /auth/user/my

> Header - Token

    Authorization: 유저 토큰 [String]

> Response

    result: 결과 [Boolean]
    data: {
        _id: 고유 번호 [String]
        userID: 유저 아이디 [String]
        ...
    }

### post

-   POST /post
-   GET /post
-   GET /post/:\_id
-   GET /post/:\_id/get-comments
-   PUT /post/:\_id
-   DELETE /post/:\_id

### comment

-   POST /comment
-   GET /comment/:\_id
-   PUT /comment/:\_id
-   DELETE /comment/:\_id


### Server Template By: [Andy0414(박종훈)'s NEM-Server Template](https://github.com/Andy-0414/NEM-typescript-v2)


