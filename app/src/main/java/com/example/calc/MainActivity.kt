package com.example.calc

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val actionBar: androidx.appcompat.app.ActionBar? = supportActionBar
        actionBar?.hide()

        txMathOperation.text = savedInstanceState?.getString("textQuestion")
        txAnswer.text = savedInstanceState?.getString("textAnswer")


        btZero.setOnClickListener {
            if (!txMathOperation.text.endsWith("/")) {
                setTextMathOperation(getString(R.string.number_zero))
            }
        }

        btOne.setOnClickListener { setTextMathOperation(getString(R.string.number_one)) }
        btTwo.setOnClickListener { setTextMathOperation(getString(R.string.number_two)) }
        btThree.setOnClickListener { setTextMathOperation(getString(R.string.number_three)) }
        btFour.setOnClickListener { setTextMathOperation(getString(R.string.number_four)) }
        btFive.setOnClickListener { setTextMathOperation(getString(R.string.number_five)) }
        btSix.setOnClickListener { setTextMathOperation(getString(R.string.number_six)) }
        btSeven.setOnClickListener { setTextMathOperation(getString(R.string.number_seven)) }
        btEight.setOnClickListener { setTextMathOperation(getString(R.string.number_eight)) }
        btNine.setOnClickListener { setTextMathOperation(getString(R.string.number_nine)) }
        btLeft.setOnClickListener { setTextMathOperation(getString(R.string.number_lfrt)) }
        btRight.setOnClickListener { setTextMathOperation(getString(R.string.number_right)) }

        btPlus.setOnClickListener {
            if (changeZnak()) {
                txMathOperation.text = txMathOperation.text.dropLast(1)
                setTextMathOperation(getString(R.string.plus))
            } else {
                setTextMathOperation(getString(R.string.plus))
            }
        }

        btMinus.setOnClickListener {
            if (changeZnak()) {
                txMathOperation.text = txMathOperation.text.dropLast(1)
                setTextMathOperation(getString(R.string.minus))
            } else {
                setTextMathOperation(getString(R.string.minus))
            }
        }

        btDivision.setOnClickListener {
            if (changeZnak()) {
                txMathOperation.text = txMathOperation.text.dropLast(1)
                setTextMathOperation(getString(R.string.divide))
            } else {
                setTextMathOperation(getString(R.string.divide))
            }
        }

        btX.setOnClickListener {
            if (changeZnak()) {
                txMathOperation.text = txMathOperation.text.dropLast(1)
                setTextMathOperation(getString(R.string.mult))
            } else {
                setTextMathOperation(getString(R.string.mult))
            }
        }

        btCE.setOnClickListener {
            txMathOperation.text = ""
            txAnswer.text = ""
        }

        btEqually.setOnClickListener {
            if(!txMathOperation.text.equals("")){
                ofStrToAnswer()
            }

        }
        btBack.setOnClickListener {
            txMathOperation.text = txMathOperation.text.dropLast(1)
            txAnswer.text = ""
        }

        btComma.setOnClickListener {
            setTextMathOperation(getString(R.string.point))
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("textQuestion",txMathOperation.text.toString())
        outState.putString("textAnswer",txAnswer.text.toString())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        txAnswer.setTextColor(ContextCompat.getColor(this, R.color.black))
        txMathOperation.text = savedInstanceState.getString("textQuestion")
        txAnswer.text = savedInstanceState.getString("textAnswer")
    }

    private fun changeZnak(): Boolean {
        return if (txMathOperation.text.endsWith(getString(R.string.mult)) ||
            txMathOperation.text.endsWith(getString(R.string.plus)) ||
            txMathOperation.text.endsWith(getString(R.string.divide)) ||
            txMathOperation.text.endsWith(getString(R.string.minus)))
            true
        else {
            false
        }
    }

    private fun setTextMathOperation(str: String) {
        if (txAnswer.text != ""&& !txAnswer.text.equals(getString(R.string.mistake))) {

            txMathOperation.text = txAnswer.text
            txAnswer.text = ""
        }
        txMathOperation.append(str)
    }

    private fun ofStrToAnswer() {
        try {
            val ex = ExpressionBuilder(txMathOperation.text.toString()).build()
            val res = ex.evaluate()
            val longRes = res.toLong()
            if (res == longRes.toDouble()) {
                txAnswer.text = longRes.toString()
            } else {
                txAnswer.text = res.toString()
            }
        } catch (e: Exception) {
            Log.i("TAG", e.toString())
            txAnswer.text = getString(R.string.mistake)
        }
    }
}