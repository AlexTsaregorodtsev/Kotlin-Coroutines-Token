package tsar.alex.client.filter

import tsar.alex.client.data.Request
import tsar.alex.client.data.Response
import java.util.Queue

class ExchangeFunction(
    private val filters: Queue<ClientFilter>
) {

    suspend fun exchange(request: Request): Response =
        filters.poll().filter(request, this)
}