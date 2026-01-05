package com.example.flightsapp.MainScreen.view

import android.provider.Settings.Global.getString
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flightsapp.Common.Flights
import com.example.flightsapp.Common.FlightsRepository
import com.example.flightsapp.MainScreen.viewmodel.mainPageViewModel
import com.example.flightsapp.R
import com.example.flightsapp.ui.theme.AppSpacing

@Composable
fun MainPage(
    viewModel: mainPageViewModel = viewModel()
) {


    val flights by viewModel.flights.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        var from = remember { mutableStateOf("") }
        var to = remember { mutableStateOf("") }
        //Spacer(modifier = Modifier.padding(AppSpacing.XL))
        TopSearchFlights(from, to)
        Spacer(modifier = Modifier.padding(AppSpacing.XL))
        DispFlights(flights)
    }

}

@Composable
fun TopSearchFlights(
    from: MutableState<String>,
    to: MutableState<String>

) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
            .fillMaxHeight(0.2f),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        SearchFlightsTextField(
            to, stringResource(R.string.Flight_To), R.drawable.ic_to
        )
        Spacer(Modifier.padding(AppSpacing.S))
        SearchFlightsTextField(
            from, stringResource(R.string.Flight_From), R.drawable.ic_from
        )

    }
}

@Composable
fun SearchFlightsTextField(
    fromOrTo: MutableState<String>, placeholderText: String, IconRes: Int
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
    ) {
        val planeOffsetX by animateDpAsState(
            targetValue = if (fromOrTo.value.isNotEmpty()) 100.dp else 0.dp,
            animationSpec = tween(durationMillis = 800, easing = FastOutSlowInEasing)
        )


        val planeOffsetY by animateDpAsState(
            targetValue = if (fromOrTo.value.isNotEmpty()) (-20).dp else 0.dp,
            animationSpec = tween(durationMillis = 800, easing = FastOutSlowInEasing)
        )

        TextField(
            value = fromOrTo.value, placeholder = {
            Text(placeholderText)
        }, onValueChange = {
            fromOrTo.value = it
        }, colors = TextFieldDefaults.colors(
            unfocusedTextColor = MaterialTheme.colorScheme.onPrimary,
            focusedTextColor = MaterialTheme.colorScheme.primary,
            unfocusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
            focusedContainerColor = MaterialTheme.colorScheme.primaryContainer
        )
        )

        Icon(
            painter = painterResource(IconRes),
            contentDescription = placeholderText + fromOrTo.value,
            tint = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier
                .padding(horizontal = AppSpacing.M)
                .offset(x = planeOffsetX, y = planeOffsetY)
                .size(32.dp)
        )
    }
}

@Composable
fun DispFlights(
    flights: List<Flights>
){
    Column()
    {
        LazyColumn() {
            items(flights){
                item ->
                DispFlightItem(item)

            }

        }
    }
}

@Composable
fun DispFlightItem(
    flights: Flights
){
    Card(
        modifier = Modifier
                .padding(AppSpacing.M)
                .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
    )
    {
        Spacer(Modifier.padding(AppSpacing.S))
        AirportsRow(flights)
        Spacer(Modifier.padding(AppSpacing.L))


    }
}

@Composable
fun AirportsRow(
    flights: Flights
){
    Row(
        modifier = Modifier.fillMaxWidth() ,
        horizontalArrangement = Arrangement.SpaceBetween ,
        verticalAlignment = Alignment.CenterVertically

    ){
        Text(flights.airportFrom ,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold)

        Icon(painter = painterResource(R.drawable.arrow_right), contentDescription = "")

        Text(flights.airportTo ,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold)
    }
}

@Composable
fun TimeRows(
    flights: Flights
){
    Row(   modifier = Modifier.fillMaxWidth() ,
        horizontalArrangement = Arrangement.SpaceBetween ,
        verticalAlignment = Alignment.CenterVertically){
    }
    

}




@Preview
@Composable
fun MainPagePrev() {
    MainPage()
}