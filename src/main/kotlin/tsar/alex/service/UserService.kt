package tsar.alex.service

import jakarta.annotation.PostConstruct
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import tsar.alex.client.ClientMock
import tsar.alex.client.data.Request
import tsar.alex.client.data.Response
import tsar.alex.util.REQUEST_ID_HEADER
import kotlin.random.Random

@Service
class UserService(
    private val resourceClient: ClientMock
) {

    private val log = LoggerFactory.getLogger(UserService::class.java)

    @Scheduled(initialDelay = 1_000L, fixedDelay = 1_000_000L)
    suspend fun scheduler() {

        log.info("---Имитируем запросы пользователей---")

        coroutineScope {
            generateSequence(1) { it + 1 }
                .take(100)
                .map { id ->
                    async { sendRequest(id) }
                }
                .toList()
                .awaitAll()
        }
    }


    suspend fun sendRequest(id: Int): Response {

        delay(Random.nextInt(100).toLong())

        val request = Request(
            headers = mapOf(
                REQUEST_ID_HEADER to "${Random.nextInt(999)}_$id",
            ),
            body = "Запрос пользователя $id"
        )
        log.debug("Пользователь {} отправляет запрос {}", id, request)

        return resourceClient.send(request)
    }
}