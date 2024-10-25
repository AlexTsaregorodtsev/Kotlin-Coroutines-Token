package tsar.alex.client.data

data class Response(
    val headers: Map<String, String>,
    val body: String?
)