package com.example.calculadora_imc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import com.example.calculadora_imc.R.layout.activity_main
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setListeners()
    }


    private fun setListeners() {

        // Toast é aquela núvem que mostra os números flutuando acima do teclado
//        alturaEDT?.doAfterTextChanged { text ->
//            Toast.makeText( this, text.toString(), Toast.LENGTH_SHORT).show()
//        }

//        Mudar o texto enquanto digita
//        pesoEDT?.doOnTextChanged { text, _, _, _ ->
//            titleTXT?.text = text
//        }

        calcularBTN?.setOnClickListener {
            calcularIMC(pesoEDT.text.toString(), alturaEDT.text.toString())
        }
    }


    private fun calcularIMC(peso: String, altura: String) {
        val peso = peso.toFloatOrNull()
        val altura = altura.toFloatOrNull()

        if(peso != null && altura != null) {
            val imc = peso / (altura * altura)

            imc?.let {
                when (it) {
                    in 16.0..17.0 -> { titleTXT.text = "Seu IMC é: %.2f \n Magreza moderada".format(imc) }
                    in 17.1..18.5 -> { titleTXT.text = "Seu IMC é: %.2f \n Magreza leve".format(imc) }
                    in 18.6..25.0 -> { titleTXT.text = "Seu IMC é: %.2f \n Saudável".format(imc) }
                    in 25.1..30.0 -> { titleTXT.text = "Seu IMC é: %.2f \n Sobrepeso".format(imc) }
                    in 30.1..35.0 -> { titleTXT.text = "Seu IMC é: %.2f \n Obesidade grau I".format(imc) }
                    in 35.1..40.0 -> { titleTXT.text = "Seu IMC é: %.2f \n Obesidade grau II (severa)".format(imc) }
                    else -> {
                        if (it < 16.0) {
                            titleTXT.text = "Seu IMC é: %.2f \n Magreza grave".format(imc)
                        } else if (it > 40) {
                            titleTXT.text = "Seu IMC é: %.2f \n Obesidade grau III (mórbida)".format(imc)
                        }
                    }
                }
            }
        }
    }

}


