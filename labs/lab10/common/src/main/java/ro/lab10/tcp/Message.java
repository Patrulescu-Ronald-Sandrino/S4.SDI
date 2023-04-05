package ro.lab10.tcp;

import java.io.*;

public class Message {
    public static final String LINE_SEPARATOR = System.getProperty("line.separator");

    private final String header;
    private final String body;
    public Message(String header, String body) {
        this.header = header;
        this.body = body;
    }

    public Message(String header) {
        this.header = header;
        this.body = "";
    }

    public static Message ok(String body) {
        return new Message("ok", body);
    }

    public static Message error(String body) {
        return new Message("error", body);
    }

    public String getHeader() {
        return header;
    }

    public String getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "Message{" +
                "header='" + header + '\'' +
                ", body='" + body + '\'' +
                '}';
    }

    public static Message readFrom(InputStream inputStream) throws IOException {
        var buffer = new BufferedReader(new InputStreamReader(inputStream));
        var header = buffer.readLine();
        var body = buffer.readLine();

        return new Message(header, body);
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        outputStream.write((header + LINE_SEPARATOR + body + LINE_SEPARATOR).getBytes());
    }
}
