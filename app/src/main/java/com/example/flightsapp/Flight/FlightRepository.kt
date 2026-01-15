package com.example.flightsapp.FlightData

import android.content.Context
import com.google.gson.Gson

class FlightRepository(private val context: Context){
    fun getFlights(): List<Flights> {
        val jsonString = context.assets
            .open("mock_flights.json")
            .bufferedReader()
            .use { it.readText() }

        val gson = Gson()
        return gson.fromJson(
            jsonString,
            Array<Flights>::class.java
        ).toList()
    }
}