package tsar.alex.client.filter

import tsar.alex.client.data.Request
import tsar.alex.client.data.Response

interface ClientFilter {

    suspend fun filter(request: Request, next: ExchangeFunction): Response
}