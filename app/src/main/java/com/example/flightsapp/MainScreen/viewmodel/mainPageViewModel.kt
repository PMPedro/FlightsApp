package com.example.flightsapp.MainScreen.viewmodel

import androidx.lifecycle.ViewModel
import com.example.flightsapp.Common.Flights
import com.example.flightsapp.Common.FlightsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class mainPageViewModel : ViewModel() {
    private val allFlights = FlightsRepository.flights

    private val _flights = MutableStateFlow(allFlights)
    val flights: StateFlow<List<Flights>> = _flights


}