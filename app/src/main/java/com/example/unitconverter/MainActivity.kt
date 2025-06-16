package com.example.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import java.nio.file.WatchEvent
import androidx.compose.runtime.getValue
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnitConverterTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    UnitConverter(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun UnitConverter(modifier: Modifier = Modifier)
{
    var inputvalue by remember { mutableStateOf("") }
    var outputvalue by remember { mutableStateOf("") }
    var inputunit by remember { mutableStateOf("Meters") }
    var outputunit by remember { mutableStateOf("Meters") }
    var iExpanded by remember { mutableStateOf(false) }
    var oExpanded by remember { mutableStateOf(false) }
    val conversionfactor = remember { mutableStateOf(1.0) }
    val oconversionfactor = remember { mutableStateOf(1.0) }

    fun ConvertUnit()
    {
        // ?: - elvis operator
        val DoubleInputValue = inputvalue.toDoubleOrNull() ?: 0.0
        val result = (DoubleInputValue * conversionfactor.value * 100.0 / oconversionfactor.value).roundToInt() / 100
        outputvalue = result.toString()
    }


    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        // Here UI component are stacked below each other
        Text("Unit Converter", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = inputvalue,
            onValueChange = {  // Here goes what should happen, when the value of OutlineTextField changes
                            inputvalue = it },
            label = { Text("Enter Value ")
            ConvertUnit()
            }
        )
        Spacer(modifier = Modifier.height(16.dp))

        Row {
            // Here UI component are stacked next to each other
            //Input Box
            Box{
                // Input Button
                Button(onClick = {iExpanded = true }) {
                    Text(inputunit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded = false}) {
                    DropdownMenuItem(
                        text = { Text("Centimeter") },
                        onClick = {
                            iExpanded = false
                            inputunit = "Centimeter"
                            conversionfactor.value = 0.01
                            ConvertUnit()
                        }
                    )

                    DropdownMenuItem(
                        text = { Text("Meter") },
                        onClick = {
                            iExpanded = false
                            inputunit = "Meter"
                            conversionfactor.value = 1.0
                            ConvertUnit()
                        }
                    )

                    DropdownMenuItem(
                        text = { Text("Feet") },
                        onClick = {
                            iExpanded = false
                            inputunit = "Feet"
                            conversionfactor.value = 0.3048
                            ConvertUnit()
                        }
                    )

                    DropdownMenuItem(
                        text = { Text("Millimeter") },
                        onClick = {
                            iExpanded = false
                            inputunit = "Millimeter"
                            conversionfactor.value = 0.001
                            ConvertUnit()
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            // Output BOx
            Box{
                // Output Button
                Button(onClick = {oExpanded = true}) {
                    Text(outputunit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = {oExpanded = false}){
                    DropdownMenuItem(
                        text = { Text("Centimeter") },
                        onClick = {
                            oExpanded = false
                            outputunit= "Centimeter"
                            oconversionfactor.value = 0.01
                            ConvertUnit()
                        }
                    )

                    DropdownMenuItem(
                        text = { Text("Meter") },
                        onClick = {
                            oExpanded = false
                            outputunit= "Meter"
                            oconversionfactor.value = 1.0
                            ConvertUnit()
                        }
                    )

                    DropdownMenuItem(
                        text = { Text("Feet") },
                        onClick = {
                            oExpanded = false
                            outputunit= "Feet"
                            oconversionfactor.value = 0.3048
                            ConvertUnit()
                        }
                    )

                    DropdownMenuItem(
                        text = { Text("Millimeter") },
                        onClick = {
                            oExpanded = false
                            outputunit= "Millimeter"
                            oconversionfactor.value = 0.001
                            ConvertUnit()
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            "Result : $outputvalue $outputunit",
            style = MaterialTheme.typography.headlineMedium
            )
    }
}

@Preview(
    showBackground = true,
    apiLevel = 33
)
@Composable
fun UnitConverterPreview(){
    UnitConverter(modifier = Modifier.padding(16.dp))
}

