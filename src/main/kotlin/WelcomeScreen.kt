import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import theme.*

data class Proyecto(val id: Int, val nombre: String, val fecha: String)


class WelcomeScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        val proyectosFinalizados = listOf(
            Proyecto(1, "Proyecto1", "20/1/2025"),
            Proyecto(2, "Proyecto2", "20/1/2025"),
            Proyecto(3, "Proyecto3", "20/1/2025"),
            Proyecto(4, "Proyecto4", "20/1/2025"),
            Proyecto(5, "Proyecto5", "20/1/2025"),
            Proyecto(6, "Proyecto6", "20/1/2025"),
            Proyecto(7, "Proyecto7", "20/1/2025"),
            Proyecto(8, "Proyecto8", "20/1/2025"),
        )
        Column(
            modifier = Modifier.background(Color(backgroundLight)).fillMaxSize().padding(20.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(7.dp)).background(Color(foregroundSecondaryDark)).padding(20.dp),
                contentAlignment = Alignment.Center,
            ) {
                Text(text = "Hola caballo homosexual de las montañas, te has registrado como gestor", fontSize = 20.sp, color = Color.White)
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
                        navigator?.push(ProyectosScreen())
                    },
                    modifier = Modifier.padding(20.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(backgroundDark))
                ) {
                    Text("Ver proyectos", color = Color.White)
                }
            }

            Spacer(modifier = Modifier.height(15.dp))

            LazyColumn(
                modifier = Modifier.height(200.dp)
            ) {
                items(proyectosFinalizados) { proyecto ->
                    ProyectoItem(proyecto)
                    Spacer(modifier = Modifier.height(10.dp))
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
fun ProyectoItem(proyecto: Proyecto) {
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
        Text(proyecto.fecha, color = Color.Red)
    }
}