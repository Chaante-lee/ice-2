my calculator ice 2 imad 

code 

activitymain
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:padding="16dp"
    android:gravity="center">

    <TextView
        android:id="@+id/resultText"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="0"
        android:textSize="32sp"
        android:gravity="end"
        android:padding="16dp"
        android:background="#E0E0E0" />

    <GridLayout
        android:id="@+id/gridLayout"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:columnCount="4"
        android:rowCount="5"
        android:layout_marginTop="16dp">

        <Button android:text="7" style="@style/CalcButton"/>
        <Button android:text="8" style="@style/CalcButton"/>
        <Button android:text="9" style="@style/CalcButton"/>
        <Button android:text="÷" style="@style/CalcButton"/>

        <!-- row 2 -->
        <Button android:text="4" style="@style/CalcButton"/>
        <Button android:text="5" style="@style/CalcButton"/>
        <Button android:text="6" style="@style/CalcButton"/>
        <Button android:text="×" style="@style/CalcButton"/>

        <!-- Row 3 -->
        <Button android:text="1" style="@style/CalcButton"/>
        <Button android:text="2" style="@style/CalcButton"/>
        <Button android:text="3" style="@style/CalcButton"/>
        <Button android:text="−" style="@style/CalcButton"/>

        <!-- Row 4 -->
        <Button android:text="C" style="@style/CalcButton"/>
        <Button android:text="0" style="@style/CalcButton"/>
        <Button android:text="=" style="@style/CalcButton"/>
        <Button android:text="+" style="@style/CalcButton"/>
    </GridLayout>

</LinearLayout>

mainactivity.kt
package com.example.ice2 //name of package

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


pictures of my working calculator 

<img width="1014" height="704" alt="Screenshot 2025-10-06 180216" src="https://github.com/user-attachments/assets/668e571f-79ee-432a-b454-6fb50cda3dbe" />


<img width="898" height="741" alt="Screenshot 2025-10-06 180234" src="https://github.com/user-attachments/assets/f8c59c07-9261-4f0a-bd3b-04dd9e8f2889" />


<img width="779" height="758" alt="Screenshot 2025-10-06 180321" src="https://github.com/user-attachments/assets/4a928847-1903-4953-8001-70186e03449f" />
