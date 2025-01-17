package jjackjjack.sopt.com.jjackjjack.network

import com.google.gson.JsonObject
import jjackjjack.sopt.com.jjackjjack.network.response.get.*
import jjackjjack.sopt.com.jjackjjack.network.response.post.*
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface NetworkService {

    //----------로그인/회원가입---------
    //로그인
    @POST("/user/signin")
    fun postLoginResponse(
        @Header("Content-Type") content_type: String,
        @Body() body: JsonObject
    ): Call<PostLoginResponse>

    //회원가입
    @POST("/user/signup")
    fun postSignupResponse(
        @Header("Content-Type") content_type: String,
        @Body() body: JsonObject
    ): Call<PostSignUpResponse>

    //닉네임 중복 검사
    @POST("/user/nickname")
    fun postNicknameCheckResponse(
        @Header("Content-Type") content_type: String,
        @Body() body: JsonObject
    ): Call<PostNicknameCheckResponse>

    //---------------
//    //홈 카테고리별 기부 카드 조회: 필요없을 듯
//    @GET("/program/:categoryId")
//    fun getCategoryDonateListResponse(
//        @Path("categoryId") categoryId : Int
//    )

    //홈 카테고리별 기부 카드 필터링
    @GET("/program/{categoryId}/{filterId}")
    fun getDonateSortedListResponse(
        @Path("categoryId") categoryId : Int,
        @Path("filterId") filterId : Int
    ): Call<GetDonateSortedListResponse>

    //------기부 카드 상세(스토리 & 계획)
    @GET("/program/detail/{programId}")
    fun getDonateDetailedResponse(
        @Path("programId") programId: String
    ): Call<GetDonateDetailedResponse>

    //기부 횟수, 총 베리
    @GET("/donate")
    fun getDonateRecordResponse(
        @Header("token") token: String
    ): Call<GetDonateRecordResponse>

    //기부 참여 현황
    @GET("/history")
    fun getDonateParticipationResponse(
        @Header("token") token: String
    ): Call <GetDonateParticipationResponse>

   //베리 충전
    @POST("/berryHistory")
    fun postBerryChargeResponse(
        @Header("Content-Type") content_type: String,
        @Header("token") token: String,
        @Body() body: JsonObject
    ): Call<PostBerryChargeResponse>

    //기부 베리 조회
    @GET("/history/berry")
    fun getDonateParticipationBerryNumResponse(
        @Header("token") token: String
    ): Call<GetDonateParticipationBerryNumResponse>

    //마이페이지 이용 내역
    @GET("/banking")
    fun getBerryHistoryResponse(
        @Header("token") token: String
    ): Call<GetBerryHistoryResponse>

    //기부하기
    @POST("/userHistory/{programId}")
    fun postDonateResponse(
        @Header("token") token: String,
        @Path("programId") programId: String,
        @Body() body: JsonObject
    ): Call<PostDonateResponse>

    //기부 참여 상세 조회
    @GET("/history/detail/{programId}")
    fun getDonateParticipationDetailResponse(
        @Header("Content-Type") content_type: String,
        @Header("token") token: String,
        @Path("programId") programId : String
    ): Call<GetDonateParticipationDetailResponse>

    //보유베리
    @GET("/berryHistory/myBerry")
    fun getmyBerryResponse(
        @Header("Content-Type") content_type: String,
        @Header("token") token: String
    ): Call<GetmyBerryResponse>

    //스탬프 조회
    @GET("/stamp")
    fun getStampResponse(
        @Header("token") token: String
    ):Call<GetStampResponse>

    //전달 후기
    @GET("/ranking")
    fun getDeliveryReviewResponse(
    ): Call<GetDonateParticipationDetailResponse>

    //전체 프로그램 기부 금액
    @GET("/program")
    fun gettotalDonateResponse(
    ):Call<GettotalDonateResponse>

    //닉네임 수정
    @POST("/mypage/nickname")
    fun postNicknameResponse(
        @Header("Content-Type") content_type: String,
        @Header("token") token: String,
        @Body() body: JsonObject
    ):Call<PostNicknameResponse>

    //프로필 수정
    @POST("/mypage/profile")
    fun postProfileModifyResponse(
        @Header("Content-Type") content_type: String,
        @Header("token") token: String,
        @Body() body: JsonObject
    ):Call<PostProfileModifyResponse>

    //이미지 등록
    @Multipart
    @POST("/image")
    fun postImageRegisterResponse(
        @Header("token") token: String,
        @Part image: MultipartBody.Part?
    ):Call<PostImageResponse>
}
