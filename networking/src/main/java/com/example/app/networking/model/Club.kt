package at.allaboutapps.app.networking.model

import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class Club(val name: String, val country: String, val value: Int, val image: String, val european_titles: Int) :
    Serializable