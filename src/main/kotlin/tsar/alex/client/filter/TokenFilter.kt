package tsar.alex.client.filter

import org.slf4j.LoggerFactory
import tsar.alex.client.data.Request
import tsar.alex.client.data.Response

class TokenFilter: ClientFilter {

    private val log = LoggerFactory.getLogger(TokenFilter::class.java)

    override suspend fun filter(request: Request, next: ExchangeFunction): Response {

        log.debug("Token фильтр")

        // Реализовать

        return next.exchange(request)
    }

}