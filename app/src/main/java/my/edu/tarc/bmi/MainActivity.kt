package my.edu.tarc.bmi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textViewStatus : TextView = findViewById(R.id.textViewStatus)
        val imageViewBMI : ImageView = findViewById(R.id.imageViewBMI)
        val editTextWeight : EditText = findViewById(R.id.editTextWeight)
        val editTextHeight : EditText = findViewById(R.id.editTextHeight)
        val buttonCalculate : Button = findViewById(R.id.buttonCalculate)
        val buttonReset : Button = findViewById(R.id.buttonReset)

        buttonCalculate.setOnClickListener {
            if (editTextWeight.length() == 0) {
                editTextWeight.error = "Weight is required"
                return@setOnClickListener
            }
            if (editTextHeight.length() == 0) {
                editTextHeight.error = "Height is required"
                return@setOnClickListener
            }

            val weight = editTextWeight.text.toString().toDouble()
            val height = editTextHeight.text.toString().toDouble()
            val result = weight / (height / 100).pow(2)

            if (result > 25) {
                textViewStatus.text = "Overweight"
                imageViewBMI.setImageResource(R.drawable.over)
            } else if (result in 18.5..24.9) {
                textViewStatus.text = "Normal"
                imageViewBMI.setImageResource(R.drawable.normal)
            } else {
                textViewStatus.text = "Underweight"
                imageViewBMI.setImageResource(R.drawable.under)
            }
        }

        buttonReset.setOnClickListener {
            editTextWeight.text.clear()
            editTextWeight.requestFocus()
            editTextHeight.text.clear()
            textViewStatus.text = ""
            imageViewBMI.setImageResource(R.drawable.empty)
        }
    }
}