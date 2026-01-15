package com.example.flightsapp.MainScreen.viewmodel

import FlightRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.flightsapp.Flight.Flights
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class mainPageViewModel (
    private val repository: FlightRepository
): ViewModel() {

    private val _from = MutableStateFlow("")
    private val _to = MutableStateFlow("")


    private val _flights = MutableLiveData<List<Flights>>()
    val flights: LiveData<List<Flights>> = _flights


    fun loadFlights() {
        _flights.value = repository.getFlights()
    }

    private val _filteredFlights = MutableLiveData<List<Flights>>()
    val filterdFlights: LiveData<List<Flights>> = _filteredFlights




    fun filterFlights(airpFrom: String, airpTo: String) {
        viewModelScope.launch {
            val sourceList = _flights.value ?: emptyList()

            val result = withContext(Dispatchers.Default) {
                sourceList.filter { flight ->
                    val matchesFrom =
                        airpFrom.isBlank() ||
                                flight.airportFrom.contains(airpFrom, ignoreCase = true)

                    val matchesTo =
                        airpTo.isBlank() ||
                                flight.airportTo.contains(airpTo, ignoreCase = true)

                    matchesFrom && matchesTo
                }
            }

            _filteredFlights.value = result
        }
    }


    fun onFromChanged(value: String) {
        _from.value = value
        filterFlights(value, _to.value)
    }

    fun onToChanged(value: String) {
        _to.value = value
        filterFlights(_from.value, value)
    }



}