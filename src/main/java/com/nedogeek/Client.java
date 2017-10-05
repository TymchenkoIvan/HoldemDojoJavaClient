package com.nedogeek;

import com.nedogeek.calculate.strategy.Strategy;
import com.nedogeek.calculate.strategy.impl.LowStrategy;
import org.eclipse.jetty.websocket.WebSocket.Connection;
import org.eclipse.jetty.websocket.WebSocket.OnTextMessage;
import org.eclipse.jetty.websocket.WebSocketClient;
import org.eclipse.jetty.websocket.WebSocketClientFactory;
import com.nedogeek.entity.Board;

import java.io.IOException;
import java.net.URI;
import java.util.concurrent.TimeUnit;

import static com.nedogeek.Credentials.*;

public class Client {

    private Connection connection;
    private Strategy strategy = new LowStrategy();

    private Board board = new Board();

    public Client() {
        con();
    }

    private void con() {
        WebSocketClientFactory factory = new WebSocketClientFactory();
        try {
            factory.start();

            WebSocketClient client = factory.newWebSocketClient();

            connection = client.open(new URI(SERVER + "?user=" + USER_NAME + "&password=" + PASSWORD), new OnTextMessage() {
                public void onOpen(Connection connection) {
                    System.out.println("Opened");
                }

                public void onClose(int closeCode, String message) {
                    System.out.println("Closed");
                }

                public void onMessage(String data) {
                    board.parseMessage(data);
                    board.setMyPlayer();
                    System.out.println(data);

                    if (USER_NAME.equals(board.getMover())) {
                        try {
                            doAnswer();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).get(500, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Client();
    }

    private void doAnswer() throws IOException {
        double coeff = board.calculateProbability();
        connection.sendMessage(Commands.Call.toString());
    }

    private boolean isBlind(double coeff){
        return strategy.isBlind(coeff);
    }
}