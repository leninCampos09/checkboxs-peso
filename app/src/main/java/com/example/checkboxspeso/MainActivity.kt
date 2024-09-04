package com.example.checkboxspeso

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Obtener referencias a los componentes de la interfaz
        val nombreEditText = findViewById<EditText>(R.id.editTextNombre)
        val radioLibra = findViewById<RadioButton>(R.id.radioLibra)
        val radioKilogramo = findViewById<RadioButton>(R.id.radioKilogramo)
        val checkEntrenador = findViewById<CheckBox>(R.id.checkEntrenador)
        val checkProteina = findViewById<CheckBox>(R.id.checkProteina)
        val checkDieta = findViewById<CheckBox>(R.id.checkDieta)
        val buttonRegistrar = findViewById<Button>(R.id.buttonRegistrar)
        val textViewInfo = findViewById<TextView>(R.id.textViewInfo) // Nuevo TextView

        // Configurar el botón de "Registrarse"
        buttonRegistrar.setOnClickListener {
            // Obtener el nombre ingresado
            val nombre = nombreEditText.text.toString()

            // Validar que se haya ingresado un nombre
            if (nombre.isEmpty()) {
                Toast.makeText(this, "Por favor, ingrese su nombre.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Determinar la unidad de peso seleccionada
            val unidadPeso = when {
                radioLibra.isChecked -> "Libra (Lb)"
                radioKilogramo.isChecked -> "Kilogramo (Kg)"
                else -> ""
            }

            if (unidadPeso.isEmpty()) {
                Toast.makeText(this, "Por favor, seleccione una unidad de peso.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Obtener las opciones de complementos seleccionadas
            val complementosSeleccionados = mutableListOf<String>()
            if (checkEntrenador.isChecked) complementosSeleccionados.add("Entrenador privado")
            if (checkProteina.isChecked) complementosSeleccionados.add("Bote de proteína")
            if (checkDieta.isChecked) complementosSeleccionados.add("Dieta personalizada")

            // Crear un mensaje con la información registrada
            val mensaje = """
                Nombre: $nombre
                Peso: $unidadPeso
                Complementos: ${complementosSeleccionados.joinToString(", ")}
            """.trimIndent()

            // Mostrar la información en el TextView
            textViewInfo.text = mensaje
        }
    }
}
