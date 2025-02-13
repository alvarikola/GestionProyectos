package Screen

import modelo.Proyecto
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import modelo.User
import network.apiObtenerHistorial
import theme.*



class WelcomeScreen(val user: User) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        val historyList = remember { mutableStateOf(emptyList<Proyecto>()) }
        apiObtenerHistorial {
            historyList.value = it
        }
        Column(
            modifier = Modifier.background(Color(backgroundLight)).fillMaxSize().padding(20.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(7.dp)).background(Color(foregroundSecondaryDark)).padding(20.dp),
                contentAlignment = Alignment.Center,
            ) {
                Text(text = "Hola " + user.nombre + " te has registrado como gestor", fontSize = 20.sp, color = Color.White)
            }

            Spacer(modifier = Modifier.height(15.dp))

            Row(
                modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(7.dp)).background(Color(foregroundDark)).padding(20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Proyectos activos", fontSize = 25.sp, fontWeight = FontWeight.Bold, color = Color.White)
                Button(
                    onClick = {
                        navigator?.push(ProyectosScreen(user))
                    },
                    modifier = Modifier.padding(20.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(backgroundDark))
                ) {
                    Text("Ver proyectos", color = Color.White)
                }
            }

            Spacer(modifier = Modifier.height(15.dp))

            if (historyList.value.isNotEmpty()) {
                Text("Historial", fontSize = 25.sp, fontWeight = FontWeight.Bold)
                LazyColumn(modifier = Modifier.padding(top = 5.dp)) {
                    items(historyList.value) {
                        ProyectoFinItem(it)
                    }
                }
            }

            Button(
                onClick = {
                    navigator?.pop()
                },
                modifier = Modifier.padding(20.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(backgroundDark))
            ) {
                Text("Cerrar Sesión", color = Color.White)
            }
        }
    }
}

@Composable
fun ProyectoFinItem(proyecto: Proyecto) {
    var colorFondo: Long = foregroundLight
    if (proyecto.id % 2 == 0){
        colorFondo = foregroundSecondaryLight
    }
    Row(
        modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(7.dp)).background(Color(colorFondo)).padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(proyecto.nombre)
        proyecto.fecha_finalizacion?.let { Text(it, color = Color.Red) }
    }
}