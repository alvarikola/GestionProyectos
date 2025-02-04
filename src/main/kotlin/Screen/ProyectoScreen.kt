package Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import modelo.Proyecto
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import modelo.Tarea
import theme.*

class ProyectoScreen(proyecto: Proyecto): Screen {

    val tareas = listOf(
        Tarea(1,"Tarea1", "es una tarea", 4, "20/1/2025", "20/1/2025", 1, 1),
        Tarea(2,"Tarea2", "es una tarea", 4, "20/1/2025", "20/1/2025", 1, 1),
        Tarea(3,"Tarea3", "es una tarea", 4, "20/1/2025", "20/1/2025", 1, 2),
        Tarea(4,"Tarea4", "es una tarea", 4, "20/1/2025", "20/1/2025", 1, 2),
        Tarea(5,"Tarea5", "es una tarea", 4, "20/1/2025", "20/1/2025", 1, 2),
        Tarea(6,"Tarea6", "es una tarea", 4, "20/1/2025", "20/1/2025", 1, 3),
        Tarea(7,"Tarea7", "es una tarea", 4, "20/1/2025", "20/1/2025", 1, 4),
    )

    val proyectoSeleccionado = proyecto

    @Composable
    override fun Content() {
        Column(modifier = Modifier.background(Color(backgroundLight)).fillMaxSize().padding(20.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(7.dp)).background(Color(foregroundDark)).padding(20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(text = proyectoSeleccionado.nombre, fontSize = 25.sp, fontWeight = FontWeight.Bold, color = Color.White)
                    Text(text = proyectoSeleccionado.descripcion, fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color.White)
                }
                Column {
                    Text(text = proyectoSeleccionado.fecha_creacion, fontSize = 25.sp, fontWeight = FontWeight.Bold, color = Color.White)
                    proyectoSeleccionado.fecha_finalizacion?.let { Text(text = it, fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color.White) }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn(
                modifier = Modifier
            ) {
                items(tareas) { tarea ->
                    if (tarea.proyecto == proyectoSeleccionado.id) {
                        TareaItem(tarea)
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }
        }

    }
}


@Composable
fun TareaItem(tarea: Tarea) {
    val navigator = LocalNavigator.current

    var colorFondo: Long = foregroundLight
    if (tarea.id % 2 == 0){
        colorFondo = foregroundSecondaryLight
    }
    Row(
        modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(7.dp)).background(Color(colorFondo)).padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(tarea.nombre)
            Text(tarea.descripcion)
        }
        Button(
            onClick = {
                navigator?.push(TareaScreen(tarea))
            },
            modifier = Modifier.padding(20.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(backgroundDark))
        ) {
            Text("Ver tarea", color = Color.White)
        }
    }
}