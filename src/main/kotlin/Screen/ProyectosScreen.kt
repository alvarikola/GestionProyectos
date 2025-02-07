package Screen

import modelo.Proyecto
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import theme.*
import androidx.compose.foundation.lazy.items
import modelo.User
import network.apiObtenerProyectos
import network.apiObtenerProyectosMios


class ProyectosScreen(val user: User) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current

        val proyectsList = remember { mutableStateOf(emptyList<Proyecto>()) }
        apiObtenerProyectos {
            proyectsList.value = it
        }

        val misProyectsList = remember { mutableStateOf(emptyList<Proyecto>()) }
        apiObtenerProyectosMios(user.idGestor) {
            misProyectsList.value = it
        }

        // Estado para la opción seleccionada (0 para Todos, 1 para Mios)
        var selectedIndex by remember { mutableStateOf(0) }

        // Estado para la lista de proyectos que se muestra
        val proyectosMostrados = remember { mutableStateOf<List<Proyecto>>(emptyList()) }

        // Actualizar la lista mostrada cuando cambia la selección
        LaunchedEffect(selectedIndex) {
            proyectosMostrados.value = when (selectedIndex) {
                0 -> proyectsList.value  // Todos
                1 -> misProyectsList.value  // Míos
                else -> emptyList()
            }
        }

        Column(
            modifier = Modifier.background(Color(backgroundLight)).fillMaxSize().padding(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(7.dp)).background(Color(foregroundDark)).padding(20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Proyectos", fontSize = 25.sp, fontWeight = FontWeight.Bold, color = Color.White)
                SegmentedControl(selectedIndex = selectedIndex, onIndexChanged = { selectedIndex = it })
            }
            Spacer(modifier = Modifier.height(15.dp))

            if (proyectosMostrados.value.isNotEmpty()) {
                LazyColumn(modifier = Modifier.padding(top = 5.dp)) {
                    items(proyectosMostrados.value) {
                        ProyectoItem(it)
                    }
                }
            }

        }
    }
}





@Composable
fun ProyectoItem(proyecto: Proyecto) {
    val navigator = LocalNavigator.current

    var colorFondo: Long = foregroundLight
    if (proyecto.id % 2 == 0){
        colorFondo = foregroundSecondaryLight
    }
    Row(
        modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(7.dp)).background(Color(colorFondo)).padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("${proyecto.id}")
        Column {
            Text(proyecto.nombre)
            Text(proyecto.descripcion)
        }

        Button(
            onClick = {
                navigator?.push(ProyectoScreen(proyecto))
            },
            modifier = Modifier.padding(20.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(backgroundDark))
        ) {
            Text("Ver proyecto", color = Color.White)
        }
    }
}



@Composable
fun SegmentedControl(selectedIndex: Int, onIndexChanged: (Int) -> Unit) {
    val options = listOf("Todos", "Mios")

    Row(
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        modifier = Modifier
    ) {
        options.forEachIndexed { index, label ->
            SegmentedButton(
                onClick = { onIndexChanged (index) },
                selected = index == selectedIndex,
                label = { Text(label) }
            )
        }
    }
}

@Composable
fun SegmentedButton(
    onClick: () -> Unit,
    selected: Boolean,
    label: @Composable () -> Unit
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(50), // Esto le da la forma redondeada
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (selected) Color(backgroundDark) else Color.Transparent,
            contentColor = if (selected) Color.White else Color.Black
        ),
        modifier = Modifier
            .padding(4.dp)
    ) {
        label()
    }
}