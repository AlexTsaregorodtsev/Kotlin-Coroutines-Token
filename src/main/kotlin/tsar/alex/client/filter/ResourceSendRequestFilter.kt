package tsar.alex.client.filter

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import org.slf4j.LoggerFactory
import tsar.alex.client.data.Request
import tsar.alex.client.data.Response
import tsar.alex.util.REQUEST_ID_HEADER

class ResourceSendRequestFilter(
    private val clientName: String
): ClientFilter {

    private val log = LoggerFactory.getLogger(ResourceSendRequestFilter::class.java)

    override suspend fun filter(request: Request, next: ExchangeFunction): Response =
        coroutineScope {
            log.debug("{}: Отправляем запрос: {}", clientName, request)

            delay(300)

            return@coroutineScope Response(
                headers = mapOf(
                    REQUEST_ID_HEADER to request.headers[REQUEST_ID_HEADER]!!
                ),
                body = "Успешный ответ"
            )
        }

}