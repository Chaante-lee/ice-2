package com.example.ice2

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

private val Unit.gridLayout: Int
    get() {
        TODO()
    }

class MainActivity : AppCompatActivity() {

    private lateinit var resultText: TextView
    private var currentInput = ""
    private var operand1: Double? = null
    private var operator: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultText = findViewById(R.id.resultText)

        val buttons = listOf(
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
            "+", "−", "×", "÷", "=", "C"
        )

        for (text in buttons) {
            val buttonId = resources.getIdentifier("button$text", "id", packageName)
            val button = findViewById<Button?>(buttonId) ?: findViewByText(text)
            button?.setOnClickListener { handleInput(text) }
        }
    }

    private fun findViewByText(text: String): Button? {
        val grid = findViewById<android.widget.GridLayout>(R.id.gridLayout)
        for (i in 0 until grid.childCount) {
            val btn = grid.getChildAt(i)
            if (btn is Button && btn.text == text) return btn
        }
        return null
    }

    private fun handleInput(input: String) {
        when (input) {
            "C" -> {
                currentInput = ""
                operand1 = null
                operator = null
                resultText.text = "0"
            }
            "+", "−", "×", "÷" -> {
                operand1 = currentInput.toDoubleOrNull()
                operator = input
                currentInput = ""
            }
            "=" -> {
                val operand2 = currentInput.toDoubleOrNull()
                if (operand1 != null && operator != null && operand2 != null) {
                    val result = calculate(operand1!!, operand2, operator!!)
                    resultText.text = result.toString()
                    currentInput = result.toString()
                    operand1 = null
                    operator = null
                }
            }
            else -> {
                currentInput += input
                resultText.text = currentInput
            }
        }
    }

    private fun calculate(a: Double, b: Double, op: String): Double {
        return when (op) {
            "+" -> a + b
            "−" -> a - b
            "×" -> a * b
            "÷" -> if (b != 0.0) a / b else {
                resultText.text = "Error"
                0.0
            }
            else -> 0.0
        }
    }
}
