package jjackjjack.sopt.com.jjackjjack.activities.donate

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import jjackjjack.sopt.com.jjackjjack.R
import kotlinx.android.synthetic.main.activity_donate_payment.*
import kotlinx.android.synthetic.main.activity_donate_payment.view.*



class DonatePaymentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donate_payment)

        btn_donate.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            val dialogView = layoutInflater.inflate(R.layout.dialog_donate_payment, null)
            val dialogbutton = dialogView.findViewById<Button>(R.id.btn_close)
            builder.setView(dialogView)
            builder.show()
            dialogbutton.setOnClickListener{
                finish()
            }
        }
        btn_erase_all.setOnClickListener {
            edt_donate_berry_num.setText(null)
        }


        var edtString = edt_donate_berry_num.text.toString()

        btn_plus_10_berry.setOnClickListener {
            if(edtString.length > 0){
                edtString = (edtString.toInt() + 10).toString()
            }
            else{
                edtString = "10"
            }
            edt_donate_berry_num.setText(edtString)
        }
        btn_plus_20_berry.setOnClickListener {
            if(edtString.length > 0){
                edtString = (edtString.toInt() + 50).toString()
            }
            else{
                edtString = "20"
            }
            edt_donate_berry_num.setText(edtString)
        }
        btn_plus_50_berry.setOnClickListener {
            if(edtString.length > 0){
                edtString = (edtString.toInt() + 50).toString()
            }
            else{
                edtString = "50"
            }
            edt_donate_berry_num.setText(edtString)
        }
    }
}
