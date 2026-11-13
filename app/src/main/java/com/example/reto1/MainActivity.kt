package com.example.reto1

import android.os.Bundle
import android.widget.SeekBar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.reto1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.webview.loadUrl("https://developer.android.com")

        binding.chkAndroid.setOnCheckedChangeListener { _, _ -> actualizarTecnologias() }
        binding.chkReact.setOnCheckedChangeListener { _, _ -> actualizarTecnologias() }
        binding.chkFlutter.setOnCheckedChangeListener { _, _ -> actualizarTecnologias() }

        actualizarSeekBar()

    }

    private fun actualizarTecnologias() {

        val listaTecnologias = mutableListOf<String>()

        if (binding.chkAndroid.isChecked) {
            listaTecnologias.add("Android")
        }

        if (binding.chkReact.isChecked) {
            listaTecnologias.add("React")
        }

        if (binding.chkFlutter.isChecked) {
            listaTecnologias.add("Flutter")
        }

        if (listaTecnologias.isEmpty()) {
            binding.txtResultado.text = "Aquí aparecerá el resultado"
        } else {
            binding.txtResultado.text =
                "Tecnologias marcadas: ${listaTecnologias.joinToString(" - ")}"
        }
    }

    private fun actualizarSeekBar() {
        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.txtSelecionado.text = "Nivel seleccionado: $progress"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }
}