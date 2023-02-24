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

        // Linking between UI and code
        val textViewBMI : TextView = findViewById(R.id.textViewBMI)
        val textViewStatus : TextView = findViewById(R.id.textViewStatus)
        val imageViewBMI : ImageView = findViewById(R.id.imageViewBMI)
        val editTextWeight : EditText = findViewById(R.id.editTextWeight)
        val editTextHeight : EditText = findViewById(R.id.editTextHeight)
        val buttonCalculate : Button = findViewById(R.id.buttonCalculate)
        val buttonReset : Button = findViewById(R.id.buttonReset)

        buttonCalculate.setOnClickListener {
            if (editTextWeight.text.isEmpty()) {
                editTextWeight.error = getString(R.string.errWeight)
                return@setOnClickListener // Terminate program execution
            }
            if (editTextHeight.text.isEmpty()) {
                editTextHeight.error = getString(R.string.errHeight)
                return@setOnClickListener
            }

            val weight = editTextWeight.text.toString().toDouble()
            val height = editTextHeight.text.toString().toDouble()

            val result = weight / (height / 100).pow(2)

            if (result > 25) {
                textViewBMI.text = String.format("%s : %.2f", getString(R.string.bmi), result)
                textViewStatus.text = String.format("%s : %s", getString(R.string.status), getString(R.string.over))
                imageViewBMI.setImageResource(R.drawable.over)
            } else if (result in 18.5..24.9) {
                textViewBMI.text = String.format("%s : %.2f", getString(R.string.bmi), result)
                textViewStatus.text = String.format("%s : %s", getString(R.string.status), getString(R.string.normal))
                imageViewBMI.setImageResource(R.drawable.normal)
            } else {
                textViewBMI.text = String.format("%s : %.2f", getString(R.string.bmi), result)
                textViewStatus.text = String.format("%s : %s", getString(R.string.status), getString(R.string.under))
                imageViewBMI.setImageResource(R.drawable.under)
            }
        }

        buttonReset.setOnClickListener {
            editTextWeight.text.clear()
            editTextWeight.requestFocus()
            editTextHeight.text.clear()
            textViewStatus.text = getString(R.string.status)
            textViewBMI.text = getString(R.string.bmi)
            imageViewBMI.setImageResource(R.drawable.empty)
        }
    }
}