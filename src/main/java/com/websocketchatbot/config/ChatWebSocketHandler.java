package com.websocketchatbot.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

//@Component
//public class ChatWebSocketHandler extends TextWebSocketHandler {
//
//	@Override
//	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
//		String clientMessage = message.getPayload();
//		String botResponse = processMessage(clientMessage);
//		session.sendMessage(new TextMessage(botResponse));
//	}
//
//	private String processMessage(String message) {
//		
//		String lowerCaseMessage = message.toLowerCase();
//
//		if (lowerCaseMessage.contains("hello") || lowerCaseMessage.contains("hi")) {
//			return "Hi there! How can I help you today?";
//		} else if (lowerCaseMessage.contains("bye") || lowerCaseMessage.contains("goodbye")) {
//			return "Goodbye! Have a great day!";
//		} else if (lowerCaseMessage.contains("how are you")) {
//			return "I'm just a bot, but I'm doing great! How about you?";
//		} else if (lowerCaseMessage.contains("what is your name")) {
//			return "I'm your Sanshraya Tech's friendly chatbot, here to assist you!";
//		} else if (lowerCaseMessage.contains("what can you do")) {
//			return "I can help answer your questions and assist with various tasks. How can I help you today?";
//		} else if (lowerCaseMessage.contains("thank you") || lowerCaseMessage.contains("thanks")) {
//			return "You're welcome! If you have any other questions, feel free to ask.";
//		} else if (lowerCaseMessage.contains("help")) {
//			return "Sure! How can I assist you today? You can ask me about our services, hours, or any other questions you have.";
//		} else if (lowerCaseMessage.contains("hours of operation") || lowerCaseMessage.contains("time")) {
//			return "Our hours of operation are Monday to Friday, 9 AM to 5 PM.";
//		} else if (lowerCaseMessage.contains("location") || lowerCaseMessage.contains("where are you located")) {
//			return "We are located at Pune.";
//		} else if (lowerCaseMessage.contains("services")) {
//			return "We offer a variety of services including haircuts, styling, coloring, and more. What service are you interested in?";
//		} else if (lowerCaseMessage.contains("price")) {
//			return "Our prices vary depending on the service. For a full list of prices, please visit our website or contact us directly. At www.sanshraya.com";
//		} else {
//			return "I'm not sure how to respond to that. Can you please rephrase?";
//		}
//	}
//}

@Component
public class ChatWebSocketHandler extends TextWebSocketHandler {

	private final Map<String, String> conversationStates = new HashMap<>();

	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
		String userId = session.getId();
		String clientMessage = message.getPayload();
		String botResponse = generateResponse(clientMessage, userId);
		session.sendMessage(new TextMessage(botResponse));
	}

	private String generateResponse(String message, String userId) {
		String lowerCaseMessage = message.toLowerCase();
		String currentState = conversationStates.getOrDefault(userId, "initial");

		if (lowerCaseMessage.contains("hello") || lowerCaseMessage.contains("hi")) {
			conversationStates.put(userId, "greeting");
			return "Hi there! How can I help you today? Do you want any services or do you have any other query?";
		} else if (lowerCaseMessage.contains("welcome")) {
			return "Welcome to Sanshraya Tech.";
		} else if (lowerCaseMessage.trim().isEmpty()) {
			return "Hello sir/mam, Please ask something, How can I help you!";
		}

		switch (currentState) {
		case "initial":
		case "greeting":
		case "unknown":
		case "services":
		case "query":
			if (lowerCaseMessage.contains("services")) {
				conversationStates.put(userId, "services");
				return "We offer a variety of services including haircuts, styling, coloring, and more. Which service are you interested in?";
			} else if (lowerCaseMessage.contains("query")) {
				conversationStates.put(userId, "query");
				return "Sure! How can I assist you today? You can ask me about our hours, location, or any other questions you have.";
			} else {
				return handleQueryOrService(lowerCaseMessage, userId);
			}

		default:
			conversationStates.put(userId, "initial");
			return "Hi there! How can I help you today? Do you want any services or do you have any query?";
		}
	}

	private String handleQueryOrService(String message, String userId) {
		if (message.contains("hours") || message.contains("location") || message.contains("name")
				|| message.contains("thank you") || message.contains("thanks")) {
			conversationStates.put(userId, "query");
			return handleQuery(message);
		} else {
			conversationStates.put(userId, "services");
			return getServiceInfo(message);
		}
	}

	private String getServiceInfo(String message) {
		if (message.contains("haircut")) {
			return "We offer various types of haircuts, including trims, styles, and full cuts. Prices start at Rs. 200.";
		} else if (message.contains("styling")) {
			return "Our styling services include blowouts, updos, and special occasion styles. Prices start at Rs. 300.";
		} else if (message.contains("coloring")) {
			return "We offer full coloring, highlights, and touch-ups. Prices start at Rs. 500.";
		} else {
			return "We offer a variety of services including haircuts, styling, coloring, and more. Which service are you interested in?";
		}
	}

	private String handleQuery(String message) {
		if (message.contains("hours")) {
			return "Our hours of operation are Monday to Friday, 9 AM to 5 PM.";
		} else if (message.contains("location")) {
			return "We are located at Pune.";
		} else if (message.contains("name")) {
			return "I'm your friendly chatbot, here to assist you!";
		} else if (message.contains("thank you") || message.contains("thanks")) {
			return "You're welcome! If you have any other questions, feel free to ask.";
		} else {
			return "I'm not sure how to respond to that. Can you please rephrase or ask about our services, hours, or location?";
		}
	}
}
