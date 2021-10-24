package com.example.test_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.test_compose.ui.theme.TestComposeTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestComposeTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp() {
    var isNext by remember { mutableStateOf(false) }

    if (!isNext) {
        SnowboardingScreen(onContinueClicked = { isNext = true })
    } else {
        Main()
    }
}

@Composable
fun Main() {
    val list: List<String> = listOf(
        "Buy a phone",
        "Complete deadline",
        "Create video on YTB",
        "Meeting",
        "Online learning"
    )

    LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
        items(items = list) { item ->
            TaskItem(item = item)
        }

    }
}

@Composable
fun TaskItem(item: String) {
    val (task, setTask) = remember { mutableStateOf(item) }
    val (isDone, setDone) = remember { mutableStateOf(false) }
    val (isEdit, setEdit) = remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .padding(15.dp)
            .fillMaxWidth(),
        elevation = if (!isDone) 15.dp else 0.dp,
        backgroundColor = if (!isDone) Color.White else Color.LightGray,
    ) {
        Column() {
            Row(
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 15.dp)
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Text(
                        "Task: $task",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = 10.dp)
                    )
                    if (isEdit) {
                        OutlinedTextField(
                            value = task,
                            onValueChange = { setTask(it) },
                            label = { Text("Task Description") },
                        )
                    }
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 20.dp)
            ) {
                OutlinedButton(
                    onClick = { setEdit(!isEdit) },
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 10.dp),
                    enabled = !isDone
                ) {
                    Text(if (isEdit) "Update" else "Edit")
                }

                Button(
                    onClick = {
                        setDone(!isDone)
                        setEdit(false)
                    }, modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 10.dp)
                ) {
                    Text(text = if (isDone) "Open" else "Complete")

                }
            }
        }
    }
}

@Composable
fun SnowboardingScreen(onContinueClicked: () -> Unit) {
    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Jetpack Compose Basics!")
            Button(
                modifier = Modifier.padding(vertical = 24.dp),
                onClick = onContinueClicked
            ) {
                Text("Continue")
            }
        }
    }
}

@Preview()
@Composable
fun DefaultPreview() {
    TestComposeTheme {
        MyApp()
    }
}