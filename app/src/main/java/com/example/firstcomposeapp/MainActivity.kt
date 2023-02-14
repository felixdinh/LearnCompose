package com.example.firstcomposeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.firstcomposeapp.ui.theme.FirstComposeAppTheme
import java.text.NumberFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FirstComposeAppTheme {
                // A surface container using the 'background' color from the theme
                ArtSpaceScreen()
            }
        }
    }
}


@Composable
fun BirthdayGreetingWithText(name: String, from: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("Hi I'm $name!", fontSize = 36.sp)
        Text("I come from $from ", fontSize = 24.sp)
    }

}

@Composable
fun BirthdayGreetingWithImage(name: String, from: String) {
    val image = painterResource(R.drawable.androidparty)
    Box {
        Image(
            painter = image,
            contentDescription = "Birthday cake image",
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
        BirthdayGreetingWithText(name, from)
    }
}

@Composable
fun Article() {
    Column {
        Image(
            painter = painterResource(id = R.drawable.bg_compose_background),
            null,
            modifier = Modifier
                .height(120.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Jetpack Compose tutorial", fontSize = 24.sp, modifier = Modifier.padding(bottom = 16.dp))
            Text(
                "Jetpack Compose is a modern toolkit for building native Android UI. Compose simplifies and accelerates UI development on Android with less code, powerful tools, and intuitive Kotlin APIs.",
                modifier = Modifier.padding(bottom = 16.dp),
                textAlign = TextAlign.Justify,
            )
            Text(
                "In this tutorial, you build a simple UI component with declarative functions. You call Compose functions to say what elements you want and the Compose compiler does the rest. Compose is built around Composable functions. These functions let you define your app\\'s UI programmatically because they let you describe how it should look and provide data dependencies, rather than focus on the process of the UI\\'s construction, such as initializing an element and then attaching it to a parent. To create a Composable function, you add the @Composable annotation to the function name.",
                modifier = Modifier.padding(bottom = 16.dp),
                textAlign = TextAlign.Justify
            )
        }
    }
}

@Composable
fun TaskManager() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_task_completed),
            contentDescription = null,
            modifier = Modifier.wrapContentSize(),
        )
        Text(
            "All task completed",
            modifier = Modifier.padding(top = 24.dp, bottom = 8.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
        Text("Nice work", fontSize = 16.sp)
    }
}

@Composable
fun Quadrant() {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(Modifier.weight(1f)) {
            Box(
                Modifier
                    .weight(1f, fill = true)
                    .background(Color.Green)
                    .fillMaxHeight()
            ) {
                Text("hehe")
            }
            Box(
                Modifier
                    .weight(1f)
                    .background(Color.Yellow)
                    .fillMaxHeight()
            ) {
                Text("hehe")
            }
        }
        Row(Modifier.weight(1f)) {
            Box(
                Modifier
                    .weight(1f, fill = true)
                    .background(Color.Blue)
                    .fillMaxHeight()
            ) {
                Text("hehe")
            }
            Box(
                Modifier
                    .weight(1f)
                    .background(Color.Gray)
                    .fillMaxHeight()
            ) {
                Text("hehe")
            }
        }
    }
}

@Composable
fun DiceRollerApp() {
    DiceWithButtonAndImage(
        Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    )
}

@Composable
fun DiceWithButtonAndImage(modifier: Modifier) {
    var result by remember { mutableStateOf(1) }
    val imageResource = when (result) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }

    Column(modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painter = painterResource(imageResource), contentDescription = result.toString())
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            result = (1..6).random()
        }
        ) {
            Text(stringResource(R.string.roll_txt), fontSize = 20.sp)
        }
    }
}

@Composable
fun LemonScreen(text: Int, description: Int, image: Int, onImageClick: () -> Unit) {
    Column(Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally) {
        Text(stringResource(text), fontSize = 18.sp)
        Spacer(Modifier.height(16.dp))
        Image(
            painterResource(image), contentDescription = stringResource(description), modifier = Modifier
                .wrapContentSize()
                .clickable(
                    onClick = onImageClick
                )
                .border(
                    width = 1.dp, Color.Green
                )
                .padding(16.dp)
        )
    }
}

@Composable
fun lemonadeApp() {
    var currentState by remember { mutableStateOf(1) }
    var squeueCount by remember { mutableStateOf(0) }
    when (currentState) {
        1 -> {
            LemonScreen(text = R.string.tree_txt, description = R.string.tree_state, image = R.drawable.lemon_tree) {
                currentState = 2
                squeueCount = (2..4).random()
            }
        }

        2 -> {
            LemonScreen(
                text = R.string.squeeze_txt,
                description = R.string.lemon_state,
                image = R.drawable.lemon_squeeze
            ) {
                // Decrease the squeeze count by 1 for each click the user performs
                squeueCount--
                // When we're done squeezing the lemon, move to the next step
                if (squeueCount == 0) {
                    currentState = 3
                }
            }
        }

        3 -> {
            LemonScreen(text = R.string.drink_txt, description = R.string.glass_state, image = R.drawable.lemon_drink) {
                currentState = 4
            }
        }

        4 -> {
            LemonScreen(
                text = R.string.empty_txt,
                description = R.string.empty_state,
                image = R.drawable.lemon_restart
            ) {
                currentState = 1
            }
        }


    }
}
@VisibleForTesting
internal fun calculateTip(
    amount: Double,
    tipPercent: Double = 15.0,
    roundUp: Boolean
):String {
    var tip = tipPercent / 100 * amount
    if(roundUp){
        tip = kotlin.math.ceil(tip)
    }
    return NumberFormat.getCurrencyInstance().format(tip)
}

@Composable
fun EditNumberField(
    value:String,
    onChange: (String) -> Unit,
    @StringRes label:Int? = null,
    imeAction: ImeAction = ImeAction.Done,
    keyboardAction: KeyboardActions = KeyboardActions(),
) {
    if(label != null) TextField(value = value, onValueChange = onChange, label = {
        Text(stringResource(id = label))
    }, modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = imeAction
        ),
        keyboardActions = keyboardAction
    )
}
@Composable
fun RoundTipRow(
    roundUp: Boolean,
    onRoundUpChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .size(48.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(stringResource(R.string.tip_round_up))
        Switch(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.End),
            colors = SwitchDefaults.colors(
                uncheckedThumbColor = Color.DarkGray,
            ),
            checked = roundUp,
            onCheckedChange = onRoundUpChanged,
        )
    }
}


@Composable
fun TipTimeScreen() {
    var amountInput by remember { mutableStateOf("") }
    var tipInput by remember { mutableStateOf("") }
    var switchChecked by remember { mutableStateOf(false) }

    val focusManager = LocalFocusManager.current

    val billAmount = amountInput.toDoubleOrNull() ?: 0.0
    val tipPercent = tipInput.toDoubleOrNull() ?: 0.0
    val tip = calculateTip(billAmount,tipPercent, roundUp = switchChecked)

    Surface() {
        Column(
            Modifier.padding(32.dp),
            Arrangement.spacedBy(8.dp)
        ) {
            Text(
                stringResource(id = R.string.calculate_tip),
                fontSize = 24.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(Modifier.height(16.dp))
            EditNumberField(
                value = amountInput,
                onChange = { amountInput = it },
                label = R.string.bill_amount,
                ImeAction.Next,
                keyboardAction = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                )
            )
            EditNumberField(
                value = tipInput,
                onChange = { tipInput = it },
                label = R.string.tip_label,
                keyboardAction = KeyboardActions(
                    onDone = {focusManager.clearFocus()}
                )
            )
            Spacer(Modifier.height(24.dp))
            RoundTipRow(
                roundUp = switchChecked,
                onRoundUpChanged = {switchChecked = it}
            )
            Spacer(Modifier.height(24.dp))
            Text(
                stringResource(id = R.string.tip_amount,tip),
                modifier = Modifier.align(Alignment.CenterHorizontally),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    FirstComposeAppTheme {
//        BirthdayGreetingWithImage(name = "Felix", from = "Viet Nam")
//        Article()
//        TaskManager()
//        lemonadeApp()
//        TipTimeScreen()
        ArtSpaceScreen()
    }
}