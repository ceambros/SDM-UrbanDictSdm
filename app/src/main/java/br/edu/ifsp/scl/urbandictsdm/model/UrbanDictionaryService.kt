package br.edu.ifsp.scl.urbandictsdm.model

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import br.edu.ifsp.scl.urbandictsdm.R
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import org.json.JSONObject

class UrbanDictionaryService(val context: Context) {
    /* Fila de requisições Volley */
    private val filaRequisicoesVolley = Volley.newRequestQueue(context)
    private val gson = Gson()

    /* Acessa o WebService e retorna um LiveData que será preenchido */
    fun getMeanings(word: String): MutableLiveData<Response> {
        /* Montando URL de consulta ao WebService */
        val url = StringBuilder(UrbanDictionaryApi.URL_BASE)
            .append("${UrbanDictionaryApi.END_POINT}/")
            .append("${word}/")
            .toString()

        /* Montando requisição */
        val respostaRequisicaoLd = MutableLiveData<Response>()
        val requisicao = buildRequest(url, respostaRequisicaoLd)

        /* Adiciona a requisição na fila de requisições Volley */
        filaRequisicoesVolley.add(requisicao)

        return respostaRequisicaoLd
    }

    private fun buildRequest(url: String, respostaRequisicaoLd: MutableLiveData<Response>): JsonObjectRequest {
        return object: JsonObjectRequest(
            Method.GET,       // Método HTTP de requisição
            url,              // URL
            null,  // Objeto de requisição JSON - usando em POST
            { response: JSONObject? ->
                response?.let{
                    respostaRequisicaoLd.value = gson.fromJson(response.toString(), Response::class.java)
                }
            }, // Listener de resposta
            { error: VolleyError? ->
                Log.e(context.getString(R.string.app_name), error?.message!!)
            } // Listener de erro
        ) {
            /* Corpo do JsonObjectRequest. Sobreescrevendo a função para passar cabeçalho na requisição */
            override fun getHeaders(): MutableMap<String, String> {
                /* Cabeçalho composto por Map com X_RAPIDAPI_KEY e X_RAPIDAPI_HOST */
                return mutableMapOf(
                    Pair(
                        UrbanDictionaryApi.X_RAPIDAPI_KEY_FIELD,
                        UrbanDictionaryApi.X_RAPIDAPI_KEY_VALUE
                    ),
                    Pair(
                        UrbanDictionaryApi.X_RAPIDAPI_HOST_FIELD,
                        UrbanDictionaryApi.X_RAPIDAPI_HOST_VALUE
                    )
                )
            }
        }
    }
}