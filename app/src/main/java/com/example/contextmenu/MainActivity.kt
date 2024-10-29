package com.example.contextmenu

import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var textET: EditText
    private lateinit var randomBTN: Button
    private lateinit var randomTV: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        textET = findViewById(R.id.textET)
        registerForContextMenu(textET)

        randomBTN = findViewById(R.id.randomBTN)
        randomBTN.setOnClickListener(this)

        randomTV = findViewById(R.id.randomTV)
        registerForContextMenu(randomTV)
    }
    val listNumber = (1..50)
    override fun onClick(v: View) {
        when(v.id) {
            R.id.randomBTN -> {
                val randomNumber = listNumber.random().toString()
                randomTV.text = randomNumber
                randomTV.setBackgroundResource(R.color.white)
                Toast.makeText(applicationContext,
                    "Случайное число сгенерировано",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        when(v.id) {
            R.id.textET -> menuInflater.inflate(R.menu.context_manu, menu)
            R.id.randomTV -> menuInflater.inflate(R.menu.context_menu_random, menu)
        }
    }


    override fun onContextItemSelected(item: MenuItem): Boolean {

        when(item.itemId) {
            R.id.menu_change_color -> {
                val estimate: String = textET.text.toString()
                when(estimate) {
                    "1" -> {
                        textET.setBackgroundResource(R.color.orange)
                        Toast.makeText(applicationContext,
                            "1 - Оранжевый цвет",
                            Toast.LENGTH_LONG).show()
                    }
                    "2" -> {
                        textET.setBackgroundResource(R.color.yellow)
                        Toast.makeText(applicationContext,
                            "2 - Жёлтый цвет",
                            Toast.LENGTH_LONG).show()
                    }
                    "3" -> {
                        textET.setBackgroundResource(R.color.green)
                        Toast.makeText(applicationContext,
                            "3 - Зелёный цвет",
                            Toast.LENGTH_LONG).show()
                    }
                    "4" -> {
                        textET.setBackgroundResource(R.color.blue)
                        Toast.makeText(applicationContext,
                            "4 - Синий цвет",
                            Toast.LENGTH_LONG).show()
                    }
                    "5" -> {
                        textET.setBackgroundResource(R.color.red)
                        Toast.makeText(applicationContext,
                            "5 - Красный цвет",
                            Toast.LENGTH_LONG).show()
                    }
                    else -> Toast.makeText(applicationContext,
                        "Ошибка! Некорректная оценка!",
                        Toast.LENGTH_LONG).show()
                }
            }
            R.id.menu_random_number -> {
                val randomNumber = Integer.parseInt(randomTV.text.toString())
                when(randomNumber) {
                    in 1..10 -> {
                        randomTV.setBackgroundResource(R.color.red)
                        Toast.makeText(applicationContext,
                            "$randomNumber - Красный цвет",
                            Toast.LENGTH_LONG).show()
                    }
                    in 11..20 -> {
                        randomTV.setBackgroundResource(R.color.orange)
                        Toast.makeText(applicationContext,
                            "$randomNumber - Оранжевый цвет",
                            Toast.LENGTH_LONG).show()
                    }
                    in 21..30 -> {
                        randomTV.setBackgroundResource(R.color.yellow)
                        Toast.makeText(applicationContext,
                            "$randomNumber - Жёлтый цвет",
                            Toast.LENGTH_LONG).show()
                    }
                    in 31..40 -> {
                        randomTV.setBackgroundResource(R.color.green)
                        Toast.makeText(applicationContext,
                            "$randomNumber - Зелёный цвет",
                            Toast.LENGTH_LONG).show()
                    }
                    in 41..50 -> {
                        randomTV.setBackgroundResource(R.color.blue)
                        Toast.makeText(applicationContext,
                            "$randomNumber - Синий цвет",
                            Toast.LENGTH_LONG).show()
                    }
                    else -> {
                        randomTV.setBackgroundResource(R.color.white)
                        Toast.makeText(applicationContext,
                            "Число вне диапазона",
                            Toast.LENGTH_LONG).show()
                    }
                }
            }
            R.id.menu_exit -> {
                finish()
                Toast.makeText(applicationContext,
                    "Программа завершена",
                    Toast.LENGTH_LONG).show()
            }
            else -> return super.onContextItemSelected(item)
        }
        return true

    }
}