package ro.lab10.tools;

public class Socket {
    public static String getAddressAndPort(java.net.Socket socket) {
        return "%s:%d".formatted(socket.getInetAddress(), socket.getPort());
    }
}
