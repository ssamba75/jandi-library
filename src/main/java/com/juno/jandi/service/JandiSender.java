package com.juno.jandi.service;

import com.juno.jandi.model.JandiMessage;
import org.springframework.http.HttpEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class JandiSender {

	/**
	 * Webhook 메세지를 전송한다.
	 *
	 * @param jandiMessage Webhook 메세지 모델
	 */
	public static void sendMessage(JandiMessage jandiMessage) {

		HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(jandiMessage.getParameterBodyMap(), JandiMessage.getHeaderParams());
		(new RestTemplate()).postForObject(jandiMessage.getWebhookURL(), request, String.class);
	}
}
