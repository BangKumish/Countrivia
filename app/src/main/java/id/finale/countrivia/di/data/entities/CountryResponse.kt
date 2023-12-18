package id.finale.countrivia.di.data.entities

data class CountryResponse(
    val Response: String,
    val Search: List<CountryModel>,
    val totalResult: String
)
