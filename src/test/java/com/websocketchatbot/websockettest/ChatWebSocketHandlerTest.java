package com.websocketchatbot.websockettest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.websocketchatbot.config.ChatWebSocketHandler;

public class ChatWebSocketHandlerTest {

    private ChatWebSocketHandler chatWebSocketHandler;
    private WebSocketSession session;

    @BeforeEach
    public void setup() {
        chatWebSocketHandler = new ChatWebSocketHandler();
        session = mock(WebSocketSession.class);
    }

    @Test
    public void testHandleTextMessage_Greeting() throws IOException {
        when(session.getId()).thenReturn("1");
        TextMessage message = new TextMessage("Hello");
        TextMessage expectedResponse = new TextMessage("Hi there! How can I help you today? Do you want any services or do you have any other query?");
        
        chatWebSocketHandler.handleTextMessage(session, message);

        verify(session).sendMessage(expectedResponse);
    }

    @Test
    public void testHandleTextMessage_Hi() throws IOException {
        when(session.getId()).thenReturn("1");
        TextMessage message = new TextMessage("Hi");
        TextMessage expectedResponse = new TextMessage("Hi there! How can I help you today? Do you want any services or do you have any other query?");
        
        chatWebSocketHandler.handleTextMessage(session, message);

        verify(session).sendMessage(expectedResponse);
    }

    @Test
    public void testHandleTextMessage_Welcome() throws IOException {
        when(session.getId()).thenReturn("1");
        TextMessage message = new TextMessage("Welcome");
        TextMessage expectedResponse = new TextMessage("Welcome to Sanshraya Tech.");
        
        chatWebSocketHandler.handleTextMessage(session, message);

        verify(session).sendMessage(expectedResponse);
    }

    @Test
    public void testHandleTextMessage_EmptyMessage() throws IOException {
        when(session.getId()).thenReturn("1");
        TextMessage message = new TextMessage("");
        TextMessage expectedResponse = new TextMessage("Hello sir/mam, Please ask something, How can I help you!");
        
        chatWebSocketHandler.handleTextMessage(session, message);

        verify(session).sendMessage(expectedResponse);
    }

    @Test
    public void testHandleTextMessage_Services() throws IOException {
        when(session.getId()).thenReturn("1");
        TextMessage message = new TextMessage("I need services");
        TextMessage expectedResponse = new TextMessage("We offer a variety of services including haircuts, styling, coloring, and more. Which service are you interested in?");
        
        chatWebSocketHandler.handleTextMessage(session, message);

        verify(session).sendMessage(expectedResponse);
    }

    @Test
    public void testHandleTextMessage_Query() throws IOException {
        when(session.getId()).thenReturn("1");
        TextMessage message = new TextMessage("I have a query");
        TextMessage expectedResponse = new TextMessage("Sure! How can I assist you today? You can ask me about our hours, location, or any other questions you have.");
        
        chatWebSocketHandler.handleTextMessage(session, message);

        verify(session).sendMessage(expectedResponse);
    }

    @Test
    public void testHandleTextMessage_HoursQuery() throws IOException {
        when(session.getId()).thenReturn("1");
        TextMessage message = new TextMessage("What are your hours?");
        TextMessage expectedResponse = new TextMessage("Our hours of operation are Monday to Friday, 9 AM to 5 PM.");
        
        chatWebSocketHandler.handleTextMessage(session, message);

        verify(session).sendMessage(expectedResponse);
    }

    @Test
    public void testHandleTextMessage_LocationQuery() throws IOException {
        when(session.getId()).thenReturn("1");
        TextMessage message = new TextMessage("Where are you located?");
        TextMessage expectedResponse = new TextMessage("We are located at Pune.");
        
        chatWebSocketHandler.handleTextMessage(session, message);

//        verify(session).sendMessage(expectedResponse);
    }

    @Test
    public void testHandleTextMessage_NameQuery() throws IOException {
        when(session.getId()).thenReturn("1");
        TextMessage message = new TextMessage("What is your name?");
        TextMessage expectedResponse = new TextMessage("I'm your friendly chatbot, here to assist you!");
        
        chatWebSocketHandler.handleTextMessage(session, message);

        verify(session).sendMessage(expectedResponse);
    }

    @Test
    public void testHandleTextMessage_ThankYou() throws IOException {
        when(session.getId()).thenReturn("1");
        TextMessage message = new TextMessage("Thank you");
        TextMessage expectedResponse = new TextMessage("You're welcome! If you have any other questions, feel free to ask.");
        
        chatWebSocketHandler.handleTextMessage(session, message);

        verify(session).sendMessage(expectedResponse);
    }

    @Test
    public void testHandleTextMessage_UnknownQuery() throws IOException {
        when(session.getId()).thenReturn("1");
        TextMessage message = new TextMessage("Tell me about something else");
        TextMessage expectedResponse = new TextMessage("I'm not sure how to respond to that. Can you please rephrase or ask about our services, hours, or location?");
        
        chatWebSocketHandler.handleTextMessage(session, message);

//        verify(session).sendMessage(expectedResponse);
    }

    @Test
    public void testHandleTextMessage_HaircutService() throws IOException {
        when(session.getId()).thenReturn("1");
        TextMessage message = new TextMessage("Tell me about haircut");
        TextMessage expectedResponse = new TextMessage("We offer various types of haircuts, including trims, styles, and full cuts. Prices start at Rs. 200.");
        
        chatWebSocketHandler.handleTextMessage(session, message);

        verify(session).sendMessage(expectedResponse);
    }

    @Test
    public void testHandleTextMessage_StylingService() throws IOException {
        when(session.getId()).thenReturn("1");
        TextMessage message = new TextMessage("Tell me about styling");
        TextMessage expectedResponse = new TextMessage("Our styling services include blowouts, updos, and special occasion styles. Prices start at Rs. 300.");
        
        chatWebSocketHandler.handleTextMessage(session, message);

        verify(session).sendMessage(expectedResponse);
    }

    @Test
    public void testHandleTextMessage_ColoringService() throws IOException {
        when(session.getId()).thenReturn("1");
        TextMessage message = new TextMessage("Tell me about coloring");
        TextMessage expectedResponse = new TextMessage("We offer full coloring, highlights, and touch-ups. Prices start at Rs. 500.");
        
        chatWebSocketHandler.handleTextMessage(session, message);

        verify(session).sendMessage(expectedResponse);
    }
}