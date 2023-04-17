package com.mahshook.shopee

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.mahshook.shopee.ui.theme.ShopeeTheme
import com.mahshook.shopee.ui.theme.initSize

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initSize()
        setContent {
            ShopeeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen(viewModel: MainScreenViewModel= MainScreenViewModel()) {
    val greetingState =/*viewModel.textFieldState.observeAsState("")*/
         remember {
        mutableStateListOf<String>("mahshook", "jack")
    }
    val textFdState =viewModel.textFieldState.observeAsState("") /*remember {
        mutableStateOf<String>("")
    }*/
    Surface(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Greeting(
                textFdState.value,
                { newValue ->
                   viewModel.updateData(newValue) /*textFdState.value = newValue*/
                },
                { greetingState.add(textFdState.value) },
                greetingState,
            )
        }

    }
}

@Composable
fun Greeting(
    textFdState: String,
    onValueChange: (newValue: String) -> Unit,
    onClick: () -> Unit,
    greetingState: List<String>,
) {

    for (item in greetingState) {
        Text(item)
    }
    TextField(value = textFdState, onValueChange = onValueChange)
    Button(onClick =onClick) {
        Text("Add name")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ShopeeTheme {
        MainScreen()
    }
}