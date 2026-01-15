    package com.example.flightsapp.Flight

    // Basic flight data class


    data class Flights(
        val flightCode: String,
        val date: String,
        val departureTime: Double,
        val arrivalTime: Double,
        val duration: Double,
        val airportFrom: String,
        val airportTo: String,
        val airline: String,
        val seatsAvailable: Int,
        val isDirect: Boolean,
        val price: Double
    )




