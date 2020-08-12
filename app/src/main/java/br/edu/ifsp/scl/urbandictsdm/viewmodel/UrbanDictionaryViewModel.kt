package br.edu.ifsp.scl.urbandictsdm.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.edu.ifsp.scl.urbandictsdm.model.Currency
import br.edu.ifsp.scl.urbandictsdm.model.Response
import br.edu.ifsp.scl.urbandictsdm.model.UrbanDictionaryService

class UrbanDictionaryViewModel(context: Context): ViewModel() {
    private val model = UrbanDictionaryService(context)

    fun buscaSignificado(palavra: String): MutableLiveData<Response> {
        return model.getMeanings(palavra)
    }

    fun buscaMoedas(): String? {
        println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx")
        //return model.getMoedas()
        return ""
    }
}