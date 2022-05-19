package ro.lab11.tcp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.function.UnaryOperator;

import static ro.lab11.Tools.getDateTime;

@Component
public class TcpServer implements ClientServer {
    @Autowired
    private final ExecutorService executorService;
    private final Map<String, UnaryOperator<Message>> methodsHandlers = new HashMap<>();

    public TcpServer(ExecutorService executorService) {
        this.executorService = executorService;
    }

//    private static UnaryOperator<Message> wrapHandlerWithTryCatch(UnaryOperator<Message> handler) {
//        return request -> {
//            try {
//                return handler.apply(request);
//            } catch (InterruptedException | ExecutionException e) {
//                e.printStackTrace();
//                return new Message(Message.ERROR, e.getMessage());
//            }
//        };
//    }

    public void addHandler(String methodName, UnaryOperator<Message> handler) {
        methodsHandlers.put(methodName, handler);
    }

    public static String getTCPSocketAddress(Socket socket) { // https://www.baeldung.com/java-client-get-ip-address
        var socketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();

        return socketAddress.getAddress().getHostAddress();
    }

    public void startServer() {
        try (var serverSocket = new ServerSocket(PORT)) {
            System.out.printf("[%s] server started; waiting for clients...\n", getDateTime());
            //noinspection InfiniteLoopStatement
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.printf("\n[%s] client %s connected\n", getDateTime(), ro.lab11.tools.Socket.getAddressAndPort(clientSocket));
                executorService.submit(new ClientHandler(clientSocket));
            }
        } catch (IOException e) {
            System.out.printf("[%s] server socket caught an exception:\n", getDateTime());
            e.printStackTrace();
        }
    }

    private class ClientHandler implements Runnable {
        private final Socket socket;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (var inputStream = socket.getInputStream(); var outputStream = socket.getOutputStream()) {
                var request = Message.readFrom(inputStream);
                System.out.printf("[%s] received from client %s request %s\n", getDateTime(), ro.lab11.tools.Socket.getAddressAndPort(socket), request);

                var response = methodsHandlers.get(request.getHeader()).apply(request);
                System.out.printf("[%s] computed response for client %s: %s\n", getDateTime(), ro.lab11.tools.Socket.getAddressAndPort(socket), response);

                response.writeTo(outputStream);
                System.out.printf("[%s] sent response to client %s\n", getDateTime(), ro.lab11.tools.Socket.getAddressAndPort(socket));
            }
            catch (IOException e) {
                System.out.printf("[%s] handler for client %s caught an exception:\n", getDateTime(), ro.lab11.tools.Socket.getAddressAndPort(socket));
                e.printStackTrace();
            }
        }
    }
}
