import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen

class ProyectoScreen(proyecto: Proyecto): Screen {

    val proyectoSeleccionado = proyecto

    @Composable
    override fun Content() {
        Column {
            Text(text = proyectoSeleccionado.nombre)
        }

    }
}