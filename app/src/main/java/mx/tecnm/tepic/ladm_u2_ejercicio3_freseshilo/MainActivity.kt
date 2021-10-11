package mx.tecnm.tepic.ladm_u2_ejercicio3_freseshilo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val h = Hilo(this)

        btniniciar.setOnClickListener {
            try {
                h.start()
            } catch (io: Exception) {
                Toast.makeText(this, "Error el hilo ya habia sido ejecutado", Toast.LENGTH_LONG)
                    .show()
            }
        }
        btnpausar.setOnClickListener {
            h.pausar()
        }

        btnterminar.setOnClickListener {
            h.terminar()
        }
    }
}

class Hilo(p:MainActivity):Thread(){
    var posicion = 0
    val puntero = p

    var pausado = false
    var contunuarCiclo = true

    val frases = arrayListOf<String>("Aquel que no espera vencer, ya está vencido","¿Qué armas más poderosas que las ideas?","La libertad no necesita alas, lo que necesita es echar raíces","No estudio para saber más, sino para ignorar menos","Si nadie te garantiza el mañana, el hoy se vuelve inmenso")

    fun pausar(){
        pausado = !pausado
    }

    fun terminar(){
        contunuarCiclo=false
    }

    override fun run() {
        super.run()
        if (posicion>=4){
            posicion=1
        }
        while (contunuarCiclo) {
            if (pausado == false) {
                puntero.runOnUiThread {
                    puntero.txtH.text =  "${frases.get(posicion-1)}"
                }
                posicion++
            }
            sleep(60000)
        }
    }
}