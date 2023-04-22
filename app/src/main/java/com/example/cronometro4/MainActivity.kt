package com.example.cronometro4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import com.example.cronometro4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    var stop: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val inicializar = binding.iniciar
        inicializar.setOnClickListener {
            iniciarCronometro()
        }

        val stop = binding.pausar
        stop.setOnClickListener {
            pausarCronometro()
        }

        val zerar = binding.zerar
        zerar.setOnClickListener {
            zerarCronometro()
        }
    }

    //A base do cronômetro vai começar a contar. Quando pausar, ele vai continuar contanto, então quando iniciar novamente vai pegar o tempo que passou
    //e subtrair pelo tempo em que foi pausado. Fazendo assim com que essa seja a nova base dele.
    fun iniciarCronometro() {

        binding.cronometro.base = SystemClock.elapsedRealtime() - stop
        binding.cronometro.start()
    }

    //a variável stop vai receber o momento em que o cronômetro parou e subtrair da base, momento em que começou a contar.
    fun pausarCronometro() {

        stop = SystemClock.elapsedRealtime() - binding.cronometro.base
        binding.cronometro.stop()
    }

    fun zerarCronometro() {

        binding.cronometro.base = SystemClock.elapsedRealtime()
        pausarCronometro()
    }
}