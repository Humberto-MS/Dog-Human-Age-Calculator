package com.example.calculadora_edad_perro

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import com.example.calculadora_edad_perro.ui.theme.Calculadora_edad_perroTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Calculadora_edad_perroTheme {
                Surface (
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ShowLayout(modifier = Modifier)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowLayout ( modifier: Modifier = Modifier ) {
    val imagen = painterResource(id = R.drawable.perrito)
    var edad by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Image (
            painter = imagen,
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        )
        Text (
            text = "Mi edad perruna \uD83D\uDC15",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(0.dp, 20.dp, 0.dp, 0.dp)
        )
        OutlinedTextField (
            value = edad,

            leadingIcon = { Icon (
                imageVector = Icons.Default.Edit,
                contentDescription = "editIcon"
            ) },

            onValueChange = { newEdad ->
                if ( newEdad.isDigitsOnly() ) {
                    edad = newEdad
                } else {
                    Toast.makeText(
                        context,
                        "Por favor, ingresa solo números",
                        Toast.LENGTH_LONG
                    ).show()
                }
            },
            label = { Text ( text = "Edad Humana" ) },
            placeholder = { Text ( text = "Edad Humana" ) },

            colors = OutlinedTextFieldDefaults.colors (
                focusedBorderColor = Color.DarkGray,
                unfocusedBorderColor = Color.DarkGray
            ),

            modifier = Modifier
                .width(350.dp)
                .padding(20.dp, 20.dp, 20.dp, 0.dp)
        )
        Button (
            onClick = {
                var res = 0
                res = edad.toInt() * 7
                resultado = res.toString()
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray),
            modifier = Modifier
                .width(250.dp)
                .padding(20.dp)
        ) {
            Text ( text = "Calcular" )
        }
        OutlinedTextField (
            value = resultado,

            leadingIcon = { Icon (
                imageVector = Icons.Default.Edit,
                contentDescription = "editIcon"
            ) },

            onValueChange = { newResultado -> resultado = newResultado },
            label = { Text ( text = "Edad Perruna" ) },
            placeholder = { Text ( text = "Edad Perruna" ) },
            readOnly = true,

            colors = OutlinedTextFieldDefaults.colors (
                focusedBorderColor = Color.DarkGray,
                unfocusedBorderColor = Color.DarkGray
            ),

            modifier = Modifier
                .width(350.dp)
                .padding(20.dp, 0.dp, 20.dp, 20.dp)
        )
        Button (
            onClick = {
                edad = ""
                resultado = ""
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray),
            modifier = Modifier
                .width(250.dp)
                .padding(20.dp, 0.dp, 20.dp, 30.dp)
        ) {
            Text ( text = "Borrar" )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    Calculadora_edad_perroTheme {
        ShowLayout()
    }
}