package ro.lab10.tcp;

import ro.lab10.exceptions.AppException;

import java.io.IOException;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static ro.lab10.Tools.getDateTime;

public class TcpClient {
    public static final String HOST = "localhost";
    public static final int PORT = 1234;

    private final String host;
    private final int port;

    public TcpClient() {
        this.host = HOST;
        this.port = PORT;
    }

    public TcpClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public Message sendAndReceive(Message request) {
        try (var socket = new Socket(host, port)) {
            var inputStream = socket.getInputStream();
            var outputStream = socket.getOutputStream();

            System.out.printf("[%s] sending request: %s%n", getDateTime(), request.toString());
            request.writeTo(outputStream);
            System.out.printf("[%s] request sent%n", getDateTime());

            var response = Message.readFrom(inputStream);
            System.out.printf("[%s] received response: %s%n", getDateTime(), response);

            return response;
        } catch (IOException e) {
            throw new AppException(String.format("[%s] sendAndReceive(%s) failed\n", getDateTime(), request.toString()), e);
        }
    }
}
