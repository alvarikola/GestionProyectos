package Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import modelo.Proyecto
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
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
import modelo.Tarea
import network.apiObtenerTareas
import theme.*

class ProyectoScreen(proyecto: Proyecto): Screen {

    val proyectoSeleccionado = proyecto

    @Composable
    override fun Content() {

        val tareasList = remember { mutableStateOf(emptyList<Tarea>()) }
        apiObtenerTareas(proyectoSeleccionado.id) {
            tareasList.value = it
        }

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

            if (tareasList.value.isNotEmpty()) {
                Text("Tareas", fontSize = 25.sp, fontWeight = FontWeight.Bold)
                LazyColumn(modifier = Modifier.padding(top = 5.dp)) {
                    items(tareasList.value) {
                        TareaItem(it)
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }

            Text("Asignar", fontSize = 25.sp, fontWeight = FontWeight.Bold)
            DropdownMenuExample()
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

@Composable
fun DropdownMenuExample() {
    // Estado para controlar la visibilidad del menú
    val expanded = remember { mutableStateOf(false) }
    val selectedOption = remember { mutableStateOf("Seleccione una opción") }

    Column(modifier = Modifier.padding(16.dp)) {
        // Botón que muestra el menú desplegable
        Button(onClick = { expanded.value = !expanded.value }) {
            Text(text = "Tareas")
        }

        // Mostrar la opción seleccionada
        Text(text = "Tarea a asignar: ${selectedOption.value}", modifier = Modifier.padding(top = 8.dp))

        // Menú desplegable
        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false }  // Cerrar el menú si se hace clic fuera
        ) {
            // Opciones del menú
            DropdownMenuItem(onClick = {
                selectedOption.value = "Opción 1"
                expanded.value = false
            }) {
                Text("Opción 1")
            }
            DropdownMenuItem(onClick = {
                selectedOption.value = "Opción 2"
                expanded.value = false
            }) {
                Text("Opción 2")
            }
            DropdownMenuItem(onClick = {
                selectedOption.value = "Opción 3"
                expanded.value = false
            }) {
                Text("Opción 3")
            }
        }
    }
}