package com.example.calculadora

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : Activity(), View.OnClickListener {

    private lateinit var textView: TextView
    private var currentNumber = StringBuilder()
    private var operand1: Double = 0.0
    private var pendingOperation = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)

        val buttons = listOf<Button>(
            findViewById(R.id.btn0),
            findViewById(R.id.btn1),
            findViewById(R.id.btn2),
            findViewById(R.id.btn3),
            findViewById(R.id.btn4),
            findViewById(R.id.btn5),
            findViewById(R.id.btn6),
            findViewById(R.id.btn7),
            findViewById(R.id.btn8),
            findViewById(R.id.btn9),
            findViewById(R.id.btnDot),
            findViewById(R.id.btnPlus),
            findViewById(R.id.btnMinus),
            findViewById(R.id.btnMultiply),
            findViewById(R.id.btnDivide),
            findViewById(R.id.btnEquals),
            findViewById(R.id.btnClear)
        )

        for (button in buttons) {
            button.setOnClickListener(this)
        }
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
            R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9 -> {
                appendNumber((view as Button).text.toString())
            }
            R.id.btnDot -> {
                appendDot()
            }
            R.id.btnPlus, R.id.btnMinus, R.id.btnMultiply, R.id.btnDivide -> {
                performOperation((view as Button).text.toString())
            }
            R.id.btnEquals -> {
                calculateResult()
            }
            R.id.btnClear -> {
                clear()
            }
        }
    }

    private fun appendNumber(number: String) {
        currentNumber.append(number)
        textView.text = currentNumber.toString()
    }

    private fun appendDot() {
        if (!currentNumber.contains(".")) {
            currentNumber.append(".")
            textView.text = currentNumber.toString()
        }
    }

    private fun performOperation(operation: String) {
        operand1 = currentNumber.toString().toDouble()
        pendingOperation = operation
        currentNumber.clear()
    }

    private fun calculateResult() {
        val operand2 = currentNumber.toString().toDouble()
        var result = 0.0
        when (pendingOperation) {
            "x" -> result = operand1 * operand2
            "+" -> result = operand1 + operand2
            "-" -> result = operand1 - operand2
            "/" -> {
                if (operand2 != 0.0) {
                    result = operand1 / operand2
                } else {
                    textView.text = "Error"
                    return
                }
            }
        }
        textView.text = result.toString()
        currentNumber.clear()
        currentNumber.append(result)
    }

    private fun clear() {
        currentNumber.clear()
        operand1 = 0.0
        pendingOperation = ""
        textView.text = "0"
    }
}