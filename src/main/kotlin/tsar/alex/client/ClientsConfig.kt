package tsar.alex.client

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import tsar.alex.client.filter.TokenFilter

@Configuration
class ClientsConfig {


    @Bean
    fun resourceClient(): ClientMock =
        ClientMock.ClientMockBuilder
            .builder()
            .addFilter(TokenFilter())
            .name("Resource client")
            .build()


    @Bean
    fun tokenClient(): ClientMock =
        ClientMock.ClientMockBuilder
            .builder()
            .name("Token client")
            .build()
}