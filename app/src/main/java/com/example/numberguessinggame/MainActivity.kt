package com.example.numberguessinggame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.numberguessinggame.ui.theme.NumberGuessingGameTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NumberGuessingGameTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    NumberGuessingGameScreen()
                }
            }
        }
    }
}


@Composable
fun NumberGuessingGameScreen() {
    var random by remember { mutableStateOf((1..1000).random()) }
    var getNumber by remember { mutableStateOf("") }
    var numTries by remember { mutableStateOf(0) }
    val input = getNumber.toIntOrNull()
    var hint by remember { mutableStateOf("") }

    fun guessNumber(input: Int, random: Int): String {
        if(input < random) {
            return if ((random - input) < 10){
                "So close! Your number is too low."
            }else{
                "Your number is too low."
            }
        } else if (input > random) {
            return if ((input - random) < 10){
                "So close! Your number is too high."
            }else{
                "Your number is too high."
            }
        }
        return "Congratulations!\nYou found the number in $numTries attempts."
    }

    Column(
        modifier = Modifier.padding(32.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        Spacer(Modifier.height(16.dp))
        Text(
            text = stringResource(R.string.rule),
            fontSize = 20.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(Modifier.height(32.dp))
        EditNumberField(
            value = getNumber,
            onValueChange = { getNumber = it }
        )
        Text(
            text = hint,
            fontSize = 20.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(16.dp))
        Button(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = {
                if (input != null) {
                    numTries++
                    hint = guessNumber(input, random)
                }
            }) {
            Text(stringResource(R.string.enter),
                fontSize = 24.sp)
        }
        Button(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = {
                random = (1..1000).random()
                getNumber = ""
                numTries = 0
                hint = ""
            }) {
            Text(stringResource(R.string.replay),
                fontSize = 24.sp)
        }
    }
}

@Composable
fun EditNumberField(
    value: String,
    onValueChange: (String) -> Unit
) {
    val focusManager = LocalFocusManager.current
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(stringResource(R.string.text_enter)) },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done),
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NumberGuessingGameTheme {
        NumberGuessingGameScreen()
    }
}