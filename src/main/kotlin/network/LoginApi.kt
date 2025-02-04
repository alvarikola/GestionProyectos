package network

import io.ktor.http.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import utils.sha512

fun apiLogIn(usuario: String, password: String, onSuccessResponse: (User) -> Unit) {
    val url = "http://127.0.0.1:5000/login"
    CoroutineScope(Dispatchers.IO).launch {
        val response = httpClient.post(url) {
            contentType(ContentType.Application.Json)
            setBody(LoginRequest(usuario, sha512(password)))
        }
        if (response.status == HttpStatusCode.OK) {
            val user = response.body<user>()
            onSuccessResponse(user)
        } else {
            println("Error: ${response.status}, Body: ${response.bodyAsText()}")
        }
    }
}