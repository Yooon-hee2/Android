package jjackjjack.sopt.com.jjackjjack.activities.stamp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import jjackjjack.sopt.com.jjackjjack.R
import kotlinx.android.synthetic.main.activity_get_berry.*

class GetBerryActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_berry)

        animation_get_berry.setAnimation("berrydata3.json")
        animation_get_berry.playAnimation()

        reward_berry.text = intent.getIntExtra("rewardBerry", 0).toString()

        btn_toolbar_back.setOnClickListener {
            finish()
        }
        btn_check.setOnClickListener {
            finish()
        }
    }
}