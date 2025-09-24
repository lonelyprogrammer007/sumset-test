package co.gov.sdp.spdd.web.socket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;


/**
 * Configuracion de el web socket
 *
 * @author Bryan Munoz
 *
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	/**
	 * Metodo que permite enviar y recibir entre servidor y cliente
	 */
	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		config.enableSimpleBroker("/topic");
		config.setApplicationDestinationPrefixes("/app");

	}

	/**
	 * Metodo que permite establecer conexion entre el web socket y el cliente
	 */
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/buzon").setAllowedOrigins("*").addInterceptors(httpSessionIdHandshakeInterceptor());

	}

	@Bean
	public HttpSessionHandshakeInterceptor httpSessionIdHandshakeInterceptor() {
		return new HttpSessionHandshakeInterceptor();
	}
}
