package br.edu.ifsp.scl.urbandictsdm.view

import android.os.Build
import android.os.Bundle
import android.text.Html
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import br.edu.ifsp.scl.urbandictsdm.R
import br.edu.ifsp.scl.urbandictsdm.model.Response
import br.edu.ifsp.scl.urbandictsdm.viewmodel.UrbanDictionaryViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: UrbanDictionaryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.title = getString(R.string.urban_dictionary_sdm)

        viewModel = UrbanDictionaryViewModel(this)

        /* Listener de click no botão */
        definirBt.setOnClickListener {
            val palavra = palavraEt.text.toString()
            if (palavra.isNotEmpty()) {
                /* Acessar Web Service */
                viewModel.buscaSignificado(palavra).observe(
                    this@MainActivity,
                    Observer {
                        mostrarSignificados(it)
                    }
                )
            } else {
                viewModel.buscaMoedas()
            }
        }
    }

    private fun mostrarSignificados(resposta: Response) {
        val significados = StringBuffer()
        resposta.list.withIndex().forEach {
            significados.append("<font color='#6200ee'><b>Significado ${it.index + 1}:</b></font> ${it.value.definition} <br>")
            significados.append("<b>Exemplo:</b> ${it.value.example}<br>")
            significados.append("<b>Autor:</b> ${it.value.author}<br><br>")
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            significadoTv.setText(
                Html.fromHtml(
                    significados.toString(),
                    Html.FROM_HTML_MODE_LEGACY
                )
            )
        } else {
            significadoTv.setText(Html.fromHtml(significados.toString()))
        }
    }
}
