package ro.lab11.tcp;

import ro.lab11.Tools;
import ro.lab11.exceptions.AppException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class TcpClient implements ClientServer {

    public Message sendAndReceive(Message request) {
        try (var socket = new Socket(HOST, PORT)) {
            var outputStream = socket.getOutputStream();
            var inputStream = socket.getInputStream();
            var logsWriter = new PrintWriter(new FileOutputStream("client_logs.log", true)); // https://stackoverflow.com/questions/8210616/printwriter-append-method-not-appending

            logsWriter.printf("[%s] sending request: %s%n", Tools.getDateTime(), request.toString());

            request.writeTo(outputStream);
            logsWriter.printf("[%s] request sent%n", Tools.getDateTime());

            var response = Message.readFrom(inputStream);
            logsWriter.printf("[%s] received response: %s%n", Tools.getDateTime(), response);
            logsWriter.println();
            logsWriter.close();

            return response;
        } catch (IOException e) {
            throw new AppException(String.format("[%s] sendAndReceive(%s) failed\n", Tools.getDateTime(), request.toString()), e);
        }
    }

    public String sendAndReceiveBody(String header, String body) {
        return sendAndReceive(new Message(header, body)).getBody();
    }

    public String sendAndReceiveBody(String header) {
        return sendAndReceive(new Message(header)).getBody(); // or replace 'new Message(header)' with 'new Message(header, "")' and remove Message(String) constructor
    }
}
