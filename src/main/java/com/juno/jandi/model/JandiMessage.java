package com.juno.jandi.model;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 잔디 Webhook 메세지 모델
 */
public class JandiMessage {

	private String body;
	private String connectColor;
	private List<ConnectInfo> connectInfos;
	private String webhookURL;

	/**
	 * 기본 생성자
	 */
	public JandiMessage() {
		this.connectInfos = null;
	}

	/**
	 * 잔디용 Webhook 메세지 모델을 생성합니다.
	 *
	 * @param webhookURL   JANDI Connect로 생성된 URL
	 * @param body         Webhook 타이틀
	 * @param connectColor 컬러(세로줄)
	 * @return the webhook message
	 */
	public static JandiMessage make(String webhookURL, String body, String connectColor) {

		JandiMessage jandiMessage = new JandiMessage();
		jandiMessage.setWebhookURL(webhookURL);
		jandiMessage.setBody(body);
		jandiMessage.setConnectColor(connectColor);

		return jandiMessage;
	}

	/**
	 * 기존 connectInfos에 개별 ConnectInfo를 추가 합니다.
	 *
	 * @param connectInfo 세부 메세지
	 * @return the jandi message
	 */
	public JandiMessage addConnectInfo(ConnectInfo connectInfo) {

		if (Objects.isNull(this.connectInfos))
			this.connectInfos = new ArrayList<>();

		this.connectInfos.add(connectInfo);

		return this;
	}

	/**
	 * connectInfos를 대체 합니다.
	 *
	 * @param connectInfos the connect infos
	 * @return the jandi message
	 */
	public JandiMessage putConnectInfos(List<ConnectInfo> connectInfos) {

		this.connectInfos = connectInfos;

		return this;
	}

	/**
	 * 잔디 Webhook 메세지 전송에 필요한 헤더 파라미터를 반환합니다.
	 *
	 * @return the header params
	 */
	public static MultiValueMap<String, String> getHeaderParams() {

		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", "application/vnd.tosslab.jandi-v2+json");
		headers.setContentType(MediaType.APPLICATION_JSON);

		return headers;
	}

	/**
	 * 잔디 Webhook 메세지 전송에 사용될 파라미터들을 MultiValueMap 형태로 반환합니다.
	 *
	 * @return the parameter body map
	 */
	public MultiValueMap<String, Object> getParameterBodyMap() {

		MultiValueMap<String, Object> parameters = new LinkedMultiValueMap<>();
		parameters.add("body", this.body);
		parameters.add("connectColor", this.connectColor);
		if (Objects.nonNull(connectInfos)) {
			connectInfos.stream().forEach(connectInfo -> parameters.add("connectInfo", connectInfo));
		}

		return parameters;
	}

	/**
	 * Gets body.
	 *
	 * @return the body
	 */
	public String getBody() {
		return body;
	}

	/**
	 * Sets body.
	 *
	 * @param body the body
	 */
	public void setBody(String body) {
		this.body = body;
	}

	/**
	 * Gets connect color.
	 *
	 * @return the connect color
	 */
	public String getConnectColor() {
		return connectColor;
	}

	/**
	 * Sets connect color.
	 *
	 * @param connectColor the connect color
	 */
	public void setConnectColor(String connectColor) {
		this.connectColor = connectColor;
	}

	/**
	 * Gets connect infos.
	 *
	 * @return the connect infos
	 */
	public List<ConnectInfo> getConnectInfos() {
		return connectInfos;
	}

	/**
	 * Sets connect infos.
	 *
	 * @param connectInfos the connect infos
	 */
	public void setConnectInfos(List<ConnectInfo> connectInfos) {
		this.connectInfos = connectInfos;
	}

	/**
	 * Gets webhook url.
	 *
	 * @return the webhook url
	 */
	public String getWebhookURL() {
		return webhookURL;
	}

	/**
	 * Sets webhook url.
	 *
	 * @param webhookURL the webhook url
	 */
	public void setWebhookURL(String webhookURL) {
		this.webhookURL = webhookURL;
	}

	/**
	 * 잔디 Webhook 메세지의 세부 정보 단위
	 */
	public static class ConnectInfo {

		private String title;
		private String description;
		private String imageUrl;

		/**
		 * ConnectInfo를 생성 합니다.
		 *
		 * @param title       타이틀
		 * @param description 세부내용
		 * @param imageUrl    이미지주소
		 * @return the connect info
		 */
		public static ConnectInfo make(String title, String description, String imageUrl) {

			ConnectInfo connectInfo = new ConnectInfo();
			connectInfo.setTitle(title);
			connectInfo.setDescription(description);
			connectInfo.setImageUrl(imageUrl);

			return connectInfo;
		}

		/**
		 * Gets title.
		 *
		 * @return the title
		 */
		public String getTitle() {
			return title;
		}

		/**
		 * Sets title.
		 *
		 * @param title the title
		 */
		public void setTitle(String title) {
			this.title = title;
		}

		/**
		 * Gets description.
		 *
		 * @return the description
		 */
		public String getDescription() {
			return description;
		}

		/**
		 * Sets description.
		 *
		 * @param description the description
		 */
		public void setDescription(String description) {
			this.description = description;
		}

		/**
		 * Gets image url.
		 *
		 * @return the image url
		 */
		public String getImageUrl() {
			return imageUrl;
		}

		/**
		 * Sets image url.
		 *
		 * @param imageUrl the image url
		 */
		public void setImageUrl(String imageUrl) {
			this.imageUrl = imageUrl;
		}

	}
}
