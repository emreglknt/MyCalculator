package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.mycalculator.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.sleep(2000)
        installSplashScreen()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //Numbers
        binding.One.setOnClickListener { appendOnExpresstion("1", true) }
        binding.Two.setOnClickListener { appendOnExpresstion("2", true) }
        binding.Three.setOnClickListener { appendOnExpresstion("3", true) }
        binding.Four.setOnClickListener { appendOnExpresstion("4", true) }
        binding.Five.setOnClickListener { appendOnExpresstion("5", true) }
        binding.Six.setOnClickListener { appendOnExpresstion("6", true) }
        binding.Seven.setOnClickListener { appendOnExpresstion("7", true) }
        binding.Eight.setOnClickListener { appendOnExpresstion("8", true) }
        binding.Nine.setOnClickListener { appendOnExpresstion("9", true) }
        binding.Zero.setOnClickListener { appendOnExpresstion("0", true) }
        binding.Dot.setOnClickListener { appendOnExpresstion(".", true) }

        //Operators
        binding.Plus.setOnClickListener { appendOnExpresstion("+", false) }
        binding.Minus.setOnClickListener { appendOnExpresstion("-", false) }
        binding.Mul.setOnClickListener { appendOnExpresstion("*", false) }
        binding.Divide.setOnClickListener { appendOnExpresstion("/", false) }


        binding.allClear.setOnClickListener{
            binding.problemText.text= ""
            binding.resultText.text= ""
        }

        binding.Del.setOnClickListener{
            val problemText=binding.problemText
            val length = problemText.length()
            if(length>0){
                problemText.text = problemText.text.subSequence(0,length-1)
            }
        }



        binding.Equals.setOnClickListener{
            try {

                val expression = ExpressionBuilder(binding.problemText.text.toString()).build()
                val result = expression.evaluate()
                val longResult = result.toLong()
                if(result == longResult.toDouble())
                    binding.resultText.text = longResult.toString()
                else
                    binding.resultText.text = result.toString()

            }catch (e:Exception){
                Log.d("Exception"," message : " + e.message )
            }
        }





    }




    fun appendOnExpresstion(string: String, canClear: Boolean) {

        if(  binding.resultText.text.isNotEmpty()){
            binding.problemText.text = ""
        }

        if (canClear) {
            binding.resultText.text = ""
            binding.problemText.append(string)
        } else {
            binding.problemText.append(binding.resultText.text)
            binding.problemText.append(string)
            binding.resultText.text = ""
        }
    }















}

