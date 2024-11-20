package com.example.sliderswitchhw

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyContent()

        }
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun MyContent(context: Context = LocalContext.current) {

    var checked = remember { mutableStateOf(true) }
    val userList = listOf(
        "Виктор Иванов", "Иван Смирнов", "Дмитрий Иванников", "Сергей Сереев",
        "Василий Терки", "Федор Достоевский", "Елена Викторова"
    )
    val startFunction = remember { mutableStateOf(false) }



    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {

            LazyColumn {

                if (startFunction.value) {
                    items(userList) { user ->
                        Row { Text(text = user) }
                    }
                }

            }
        }
        Row {
            Button(onClick = {
                if (checked.value) {
                    startFunction.value = true
                } else {
                    Toast.makeText(context, "Нет доступа к базе", Toast.LENGTH_SHORT).show()
                }
            }) { Text(text = "Загрузка данных", fontSize = 20.sp) }
        }
        Row {
            MySwitch(checked)
        }
    }
}

@Composable
fun MySwitch(checked: MutableState<Boolean>) {

    Switch(
        checked = checked.value,
        onCheckedChange = {
            checked.value = it
        },
        thumbContent = {
            Icon(
                imageVector = if (checked.value) Icons.Filled.Check else Icons.Filled.Close,
                contentDescription = null,
                modifier = Modifier.size(SwitchDefaults.IconSize)

            )
        }

    )
}

@Composable
fun DataBaseRow(item: String) {
    Row { Text(text = item, fontSize = 22.sp, fontWeight = FontWeight.Bold) }
}