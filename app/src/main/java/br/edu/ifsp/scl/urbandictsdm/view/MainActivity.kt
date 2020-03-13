package br.edu.ifsp.scl.urbandictsdm.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.urbandictsdm.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.title = getString(R.string.urban_dictionary_sdm)

        /* Listener de click no botão */
        definirBt.setOnClickListener {
            val palavra = palavraEt.text.toString()
            if (palavra.isNotEmpty()) {
                /* Acessar Web Service */
            }
        }
    }
}
