package id.finale.countrivia.data.local.model.entities

data class CountryResponse(
    val Response: String,
    val Search: List<CountryModel>,
    val totalResult: String
)
