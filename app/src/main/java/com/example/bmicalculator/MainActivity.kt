package com.example.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Intent
import androidx.preference.PreferenceManager
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //이전에 입력한 값을 읽어오기
        loadData()


        resultButton.setOnClickListener{

            //마지막에 입력한 데이터 저장
            saveData(heightEditText.text.toString().toInt(),
                weightEditText.text.toString().toInt())    //???

            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("weight", weightEditText.text.toString())
            intent.putExtra("height", heightEditText.text.toString())
            startActivity(intent)
        }
    }

    private fun loadData() {

        val pref=PreferenceManager.getDefaultSharedPreferences(this)

        val height=pref.getInt("KEY_HEIGHT",0)
        val weight=pref.getInt("KEY_WEIGHT", 0)

        //toast("$height")
        //toast("$weight")

        if (height !=0 && weight !=0){
            heightEditText.setText(height.toString())
            weightEditText.setText(weight.toString())
        }



    }



    private fun saveData(height:Int, weight : Int){
        val pref=PreferenceManager.getDefaultSharedPreferences(this)
        val editor = pref.edit()

        editor.putInt("KEY_HEIGHT",height)
            .putInt("KEY_WEIGHT",weight)
            .apply()
    }
}
