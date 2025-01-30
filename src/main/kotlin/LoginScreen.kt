import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow


class LoginScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        var user by remember { mutableStateOf("") }
        var passwd by remember { mutableStateOf("") }

        Column(
            modifier = Modifier.fillMaxSize().background(Color(0xFFe9ebbf)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                elevation = 15.dp
            ) {
                Column(
                    modifier = Modifier.background(Color(0xFFd8e472)),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier.padding(40.dp),
                        color = Color(0xFF333333),
                        text = "Log in",
                        fontSize = TextUnit(8f, TextUnitType.Em),
                        fontWeight = FontWeight.Bold
                    )
                    OutlinedTextField(
                        value = user,
                        onValueChange = {user = it},
                        label = { Text("Username") },
                        modifier = Modifier,
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color(0xFF418e8e),
                            focusedLabelColor = Color(0xFF5a4e3c)
                        )
                    )
                    OutlinedTextField(
                        value = passwd,
                        onValueChange = {passwd = it},
                        label = { Text("Password") },
                        modifier = Modifier,
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color(0xFF418e8e),
                            focusedLabelColor = Color(0xFF5a4e3c)
                        )
                    )
                    Button(
                        onClick = {
                            navigator?.push(WelcomeScreen())
                        },
                        modifier = Modifier.padding(20.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF5a4e3c))
                    ) {
                        Text("Iniciar Sesión", color = Color.White)
                    }
                }

            }

        }

    }
}
