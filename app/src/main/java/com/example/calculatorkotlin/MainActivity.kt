package com.example.calculatorkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.calculatorkotlin.databinding.ActivityMainBinding
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var lastNumeric: Boolean = false
    var lastDot: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    }

    fun onDigit(view: View) {
        binding.tvInput.append((view as Button).text)
        lastNumeric = true
    }

    fun onCLear(view: View) {
        binding.tvInput.text = ""
        lastNumeric = false
        lastDot = false
    }

    fun onDecimalPoint(view: View) {
        if(lastNumeric && !lastDot) {
            binding.tvInput.append(".")
            lastNumeric = false
            lastDot = true
        }
    }

    fun onOperator(view: View) {
        if(lastNumeric && !isOperatorAdded(binding.tvInput.text.toString())) {
            binding.tvInput.append((view as Button).text)
            lastNumeric = false
            lastDot = false
        }
    }

    private fun isOperatorAdded(value: String): Boolean {
        return if (value.startsWith("-")) {
            false
        } else {
            value.contains("-") || value.contains("*") || value.contains("+") || value.contains("/")
        }
    }

    fun onEqual(view: View) {
        if(lastNumeric) {
            var tvValue = binding.tvInput.text.toString()
            var prefix = ""

            try {
                if(tvValue.startsWith("-")) {
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }

                    if (tvValue.contains("-")) {
                        val splitValue = tvValue.split("-")
                        var one = splitValue[0]   //99-1 :   value one: 99 and value two: 1
                        var two = splitValue[1]

                        if (!prefix.isEmpty()) {
                            one = prefix + one
                        }

                        binding.tvInput.text =
                            removeZerosAfterDot((one.toDouble() - two.toDouble()).toString())

                    } else if (tvValue.contains("+")) {
                        val splitValue = tvValue.split("+")
                        var one = splitValue[0]   //99-1 :   value one: 99 and value two: 1
                        var two = splitValue[1]

                        if (!prefix.isEmpty()) {
                            one = prefix + one
                        }

                        binding.tvInput.text =
                            removeZerosAfterDot((one.toDouble() + two.toDouble()).toString())

                    } else if (tvValue.contains("*")) {
                        val splitValue = tvValue.split("*")
                        var one = splitValue[0]   //99-1 :   value one: 99 and value two: 1
                        var two = splitValue[1]

                        if (!prefix.isEmpty()) {
                            one = prefix + one
                        }

                        binding.tvInput.text =
                            removeZerosAfterDot((one.toDouble() * two.toDouble()).toString())

                    } else if (tvValue.contains("/")) {
                        val splitValue = tvValue.split("/")
                        var one = splitValue[0]   //99-1 :   value one: 99 and value two: 1
                        var two = splitValue[1]

                        if (!prefix.isEmpty()) {
                            one = prefix + one
                        }

                        binding.tvInput.text =
                            removeZerosAfterDot((one.toDouble() / two.toDouble()).toString())
                    }

            } catch(e: ArithmeticException) {
                e.printStackTrace()
            }
        }
    }

    private fun removeZerosAfterDot(result: String): String {
        var value = result
        if(result.contains(".0")) {
            value = result.substring(0, result.length - 2)
        }
        return value
    }
}