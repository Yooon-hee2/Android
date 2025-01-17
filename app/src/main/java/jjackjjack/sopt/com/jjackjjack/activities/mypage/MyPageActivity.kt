package jjackjjack.sopt.com.jjackjjack.activities.mypage

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.Gravity
import android.view.View
import android.webkit.URLUtil
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import jjackjjack.sopt.com.jjackjjack.activities.donateparicipation.DonateParticipationActivity
import jjackjjack.sopt.com.jjackjjack.activities.home.MainActivity
import jjackjjack.sopt.com.jjackjjack.R
import jjackjjack.sopt.com.jjackjjack.activities.berrycharge.BerryChargeActivity
import jjackjjack.sopt.com.jjackjjack.activities.berryusehistory.BerryHistoryActivity
import jjackjjack.sopt.com.jjackjjack.activities.donate.DonateActivity
import jjackjjack.sopt.com.jjackjjack.activities.deliveryreview.DeliveryReviewActivity
import jjackjjack.sopt.com.jjackjjack.db.SharedPreferenceController
import jjackjjack.sopt.com.jjackjjack.db.SharedPreferenceController.getUserEmail
import jjackjjack.sopt.com.jjackjjack.db.SharedPreferenceController.getUserImg
import jjackjjack.sopt.com.jjackjjack.db.SharedPreferenceController.getUserNickname
import jjackjjack.sopt.com.jjackjjack.interfaces.onDrawer
import jjackjjack.sopt.com.jjackjjack.network.ApplicationController
import jjackjjack.sopt.com.jjackjjack.network.NetworkService
import jjackjjack.sopt.com.jjackjjack.network.response.get.GetmyBerryResponse
import jjackjjack.sopt.com.jjackjjack.utillity.ColorToast
import jjackjjack.sopt.com.jjackjjack.utillity.Constants
import jjackjjack.sopt.com.jjackjjack.utillity.Secret
import kotlinx.android.synthetic.main.activity_my_page.*
import kotlinx.android.synthetic.main.content_activity_mypage.*
import kotlinx.android.synthetic.main.fragment_berryuse_review.*
import kotlinx.android.synthetic.main.nav_drawer.*
import kotlinx.android.synthetic.main.toolbar_with_hamburger.*
import org.jetbrains.anko.imageResource
import org.jetbrains.anko.startActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.DecimalFormat

class MyPageActivity : AppCompatActivity(), onDrawer {
    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }

    lateinit var btnFset: Array<ImageView>

    lateinit var btnAset: Array<View>

    lateinit var actSet: Array<Class<out AppCompatActivity>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_page)
        initialUI()
        Log.d("tokennnnnnn", SharedPreferenceController.getAuthorization(this))
    }

    override fun onResume() { //로그인 후에 이 뷰는 꺼지게
        super.onResume()

        if ((SharedPreferenceController.getUserImg(this))!!.isNotEmpty()) {
            Glide.with(this@MyPageActivity)
                .load(getUserImg(this))
                .apply(RequestOptions.circleCropTransform())?.into(img_temp_profile)
        } else {
            Glide.with(this@MyPageActivity)
                .load(R.drawable.pofile)
                .apply(RequestOptions.circleCropTransform())?.into(img_temp_profile)
        }

        Log.d("url_get", getUserImg(this))

        mypage_nickname.text = getUserNickname(this)
        tv_mypage_email.text = getUserEmail(this)
    }

    private fun initialUI() {
        btn_home.setOnClickListener {
            startActivity<MainActivity>()
            finish()
        }

        btn_logout.setOnClickListener {
            SharedPreferenceController.clearUserSharedPreferences(this)
            finish()
        }

        btn_nickname_edit.setOnClickListener {
            val intent = Intent(this, MyPageModifyActivity::class.java)
            startActivity(intent)
        }
        btn_berry_history.setOnClickListener {
            val intent = Intent(this, BerryHistoryActivity::class.java)
            startActivity(intent)
        }
        drawerUI()
        getmyBerryResponse()

        if (URLUtil.isValidUrl(getUserImg(this))) {
            Log.d("aaaaaaaaa", "sdf")
        }
    }

    override fun onBackPressed() {

        if (ly_drawer.isDrawerOpen(Gravity.END)) {
            ly_drawer.closeDrawer(Gravity.END)
        } else {
            //doubleBackPress()
            super.onBackPressed()
        }
    }


//    private var backPressedTime: Long = 0
//    private fun doubleBackPress(){
//        var temp: Long = System.currentTimeMillis()
//        var intervalTime: Long = temp - backPressedTime
//        if(intervalTime in 0..2000){
//            ActivityCompat.finishAffinity(this)
//        }
//        else{
//            backPressedTime = temp
//            toast("버튼을 한번 더 누르면 종료됩니다.")
//        }
//    }

    override fun drawerUI() {
        actSet = arrayOf(
            MainActivity::class.java, DonateParticipationActivity::class.java,
            DeliveryReviewActivity::class.java, MyPageActivity::class.java,
            BerryChargeActivity::class.java, BerryHistoryActivity::class.java
        )

        btnFset = arrayOf( //프래그먼트로 가는 버튼
            btn_drawer_f_child, btn_drawer_f_senior, btn_drawer_f_animal, btn_drawer_f_disabled,
            btn_drawer_f_env, btn_drawer_f_emergency
        )

        btnAset = arrayOf(
            btn_drawer_home, btn_drawer_donate_record, btn_drawer_rank,
            btn_drawer_mypage, btn_drawer_berrycharge, btn_drawer_usehistory
        )

        drawerBtnSetting(Constants.ACTIVITY_MY_PAGE)
    }

    override fun drawerBtnSetting(activityType: Int) {
        btn_hambuger.setOnClickListener {
            if (!ly_drawer.isDrawerOpen(Gravity.END)) {
                ly_drawer.openDrawer(Gravity.END)
            }
            getmyBerryResponse()
            if ((SharedPreferenceController.getUserImg(this))!!.isNotEmpty()) {
                Glide.with(this@MyPageActivity)
                    .load(SharedPreferenceController.getUserImg(this))
                    .apply(RequestOptions.circleCropTransform())?.into(iv_drawer_profileimg)
            } else {
                Glide.with(this@MyPageActivity)
                    .load(R.drawable.pofile)
                    .apply(RequestOptions.circleCropTransform())?.into(iv_drawer_profileimg)
            }
        }

        btn_cancel.setOnClickListener {
            if (ly_drawer.isDrawerOpen(Gravity.END)) {
                ly_drawer.closeDrawer(Gravity.END)
            }
        }

        tv_drawer_nickname.text = SharedPreferenceController.getUserNickname(this)//닉네임 DB 저장한 거 가져오는거
        tv_drawer_email.text = SharedPreferenceController.getUserEmail(this) // 이메일 DB 저장한 거
        if (URLUtil.isValidUrl(SharedPreferenceController.getUserImg(this))) {
            Glide.with(this).load(SharedPreferenceController.getUserImg(this))
                .apply(RequestOptions.circleCropTransform())?.into(iv_drawer_profileimg)
        }
        //이미지 DB에서 가져오기 나중에 없을때 default 이미지 뜨게 처리해야함
        //img_temp_profile.


        for (i in 0 until btnAset.size) {
            btnAset[i].setOnClickListener {
                val intent = Intent(this, actSet[i])
                ly_drawer.closeDrawer(Gravity.END)
                startActivity(intent)
                finish()
            }
        }

        for (i in 0 until btnFset.size) {
            btnFset[i].setOnClickListener {
                startActivity<DonateActivity>("fragment" to i)
                Handler().postDelayed({ ly_drawer.closeDrawer(Gravity.END) }, 110)
                finish()
            }
        }
    }

    private fun getmyBerryResponse() {
        var token: String = SharedPreferenceController.getAuthorization(this)

        val getmyBerryResponse =
            networkService.getmyBerryResponse("application/json", token)

        getmyBerryResponse.enqueue(object : Callback<GetmyBerryResponse> {
            override fun onFailure(call: Call<GetmyBerryResponse>, t: Throwable) {
                ColorToast(this@MyPageActivity, "잠시 후 다시 접속해주세요")
            }

            override fun onResponse(call: Call<GetmyBerryResponse>, response: Response<GetmyBerryResponse>) {
                if (response.isSuccessful) {
                    if (response.body()!!.status == Secret.NETWORK_LIST_SUCCESS) {
                        val receiveData = response.body()?.data

                        val dec = DecimalFormat("#,000")
                        val dec_berry: String

                        if (receiveData.toString().length <= 3) {
                            dec_berry = receiveData.toString()
                        } else {
                            dec_berry = dec.format(receiveData)
                        }

                        mypage_myberry.text = dec_berry
                        drawer_myberry.text = dec_berry
                    }
                }
            }
        })
    }

}
