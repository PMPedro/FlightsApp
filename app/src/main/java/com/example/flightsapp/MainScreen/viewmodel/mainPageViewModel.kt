package com.example.flightsapp.MainScreen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flightsapp.Common.Flights
import com.example.flightsapp.Common.FlightsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher

class mainPageViewModel : ViewModel() {
    private val allFlights = FlightsRepository.flights

    private val _flights = MutableStateFlow(allFlights)
    val flights: StateFlow<List<Flights>> = _flights
    val filterdFlights : MutableStateFlow<List<Flights>> = _flights


    fun filterFlights(airpFrom : String , airpTo : String){
        viewModelScope.launch {
            val result = withContext(Dispatchers.Default){
                allFlights.filter {
                    flight ->
                    when{
                        airpFrom.isNotEmpty() && airpTo.isNotEmpty() ->
                            flight.airportFrom.contains(airpFrom, ignoreCase = true) &&
                                    flight.airportTo.contains(airpTo, ignoreCase = true)
                        airpFrom.isNotEmpty() ->
                            flight.airportFrom.contains(airpFrom, ignoreCase = true)
                        airpTo.isNotEmpty() ->
                            flight.airportTo.contains(airpTo, ignoreCase = true)
                        else -> true
                    }
                }
            }
            filterdFlights.value = result
        }

    }

}