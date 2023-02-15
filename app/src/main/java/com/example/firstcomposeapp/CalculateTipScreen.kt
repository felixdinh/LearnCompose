package com.example.firstcomposeapp

import androidx.annotation.StringRes
import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.NumberFormat

@Composable
fun CalculateTipScreen() {
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
