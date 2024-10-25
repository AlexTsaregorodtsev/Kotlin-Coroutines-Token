package tsar.alex.client

import org.slf4j.LoggerFactory
import tsar.alex.client.data.Request
import tsar.alex.client.data.Response
import tsar.alex.client.filter.ClientFilter
import tsar.alex.client.filter.ExchangeFunction
import tsar.alex.client.filter.ResourceSendRequestFilter
import java.util.*


class ClientMock private constructor(
    private val name: String,
    private val filters: Queue<ClientFilter>
) {
    private lateinit var exchangeFunction: ExchangeFunction

    private val log = LoggerFactory.getLogger(ClientMock::class.java)


    suspend fun send(request: Request): Response {
        val response = ExchangeFunction(LinkedList(filters)).exchange(request)

        log.debug("Получен ответ клиента {}: {}", name, response)

        return response
    }

    class ClientMockBuilder private constructor() {

        private var name: String = "default"
        private val filters: Queue<ClientFilter> = LinkedList()

        companion object {
            fun builder() = ClientMockBuilder()
        }

        fun name(name: String): ClientMockBuilder {
            this.name = name
            return this
        }

        fun addFilter(filter: ClientFilter): ClientMockBuilder {
            this.filters.add(filter)
            return this
        }

        fun build(): ClientMock {
            this.filters.add(
                ResourceSendRequestFilter(name)
            )

            return ClientMock(name, filters)
        }
    }
}

