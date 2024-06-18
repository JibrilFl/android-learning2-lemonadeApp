package com.example.limonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.limonade.ui.theme.LimonadeTheme
import kotlin.math.round

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LimonadeTheme {
                LemonadeApp()
            }
        }
    }
}

@Composable
fun LemonadeWithButtonImage(modifier: Modifier = Modifier) {
    var step by remember { mutableStateOf(1) }
    var squeeze by remember { mutableStateOf(0) }

    val imageResource = when(step) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }

    val stringResource = when(step) {
        1 -> R.string.lemon_select_tree
        2 -> R.string.lemon_select_squezze
        3 -> R.string.lemon_select_drink
        else -> R.string.lemon_select_again
    }

    val stringDescriptionResource = when(step) {
        1 -> R.string.lemon_description_tree
        2 -> R.string.lemon_description_lemon
        3 -> R.string.lemon_description_lemonade
        else -> R.string.lemon_description_empty
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                when(step) {
                    1 -> {
                        squeeze = (2..4).random()
                        step = 2
                    }
                    2 -> {
                        squeeze--
                        if (squeeze == 0) {
                            step = 3
                        }
                    }
                    3 -> step = 4
                    4 -> step = 1
                }
            },
            shape = RoundedCornerShape(35.dp),
            colors = ButtonColors(
                containerColor = Color(49, 160, 151),
                contentColor = Color(0,0,0),
                disabledContentColor = Color(0,0,0),
                disabledContainerColor = Color(0,0,0)
            )
        ) {
            Image(
                painter = painterResource(imageResource),
                contentDescription = stringResource(stringDescriptionResource)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = if (squeeze == 0) stringResource(stringResource) else stringResource(stringResource) + ' ' + squeeze.toString(),
            fontSize = 18.sp
        )
    }

}

@Preview()
@Composable
fun LemonadeApp() {

    Text(
        text = "Lemonade",
        textAlign = TextAlign.Center,
        fontSize = 18.sp,
        fontWeight = FontWeight(700),
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp)
            .background(color = Color(250, 250, 51))
            .padding(top = 30.dp, bottom = 15.dp)

    )

    LemonadeWithButtonImage(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center)

    )
}