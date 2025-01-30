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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator

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
            modifier = Modifier.background(Color(0xFFe9ebbf)).fillMaxSize().padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(7.dp)).background(Color(0xFFd8e472)).padding(20.dp),
                contentAlignment = Alignment.Center,
            ) {
                Button(
                    onClick = {
                        navigator?.pop()
                    },
                    modifier = Modifier.padding(20.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF5a4e3c))
                ) {
                    Text("Cerrar Sesión", color = Color.White)
                }
                Text(text = "Hola caballo homosexual de las montañas, te has registrado como gestor", fontSize = 20.sp)
            }

            Spacer(modifier = Modifier.height(15.dp))

            Row(
                modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(7.dp)).background(Color(0xFFd8e472)).padding(20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Proyectos activos", fontSize = 25.sp, color = Color.White)
                Button(
                    onClick = {
                        navigator?.push(ProyectosScreen())
                    },
                    modifier = Modifier.padding(20.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF5a4e3c))
                ) {
                    Text("Ver proyectos", color = Color.White)
                }
            }

            Spacer(modifier = Modifier.height(15.dp))

            LazyColumn {
                items(proyectosFinalizados) { proyecto ->
                    ProyectoItem(proyecto)
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
    }
}

@Composable
fun ProyectoItem(proyecto: Proyecto) {
    Row(
        modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(7.dp)).background(Color(0xFFd8e472)).padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(proyecto.nombre)
        Text(proyecto.fecha)
    }
}