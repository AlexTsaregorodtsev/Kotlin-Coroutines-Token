package tsar.alex.client.data

data class Request(
    val headers: Map<String, String>,
    val body: String?
)