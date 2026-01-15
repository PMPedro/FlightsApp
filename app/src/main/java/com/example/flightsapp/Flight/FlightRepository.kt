import android.content.Context
import com.example.flightsapp.Flight.Flights
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class FlightRepository(private val context: Context) {

    fun getFlights(): List<Flights> {

        val jsonString = context.assets.open("mockFlight.json")
            .bufferedReader()
            .use { it.readText() }


        val gson = Gson()
        val listType = object : TypeToken<List<Flights>>() {}.type
        val flights: List<Flights> = gson.fromJson(jsonString, listType)

        return flights
    }
}
