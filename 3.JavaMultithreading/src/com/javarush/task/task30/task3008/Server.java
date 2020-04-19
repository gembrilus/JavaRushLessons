package com.javarush.task.task30.task3008;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class Server {

    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();

    public static void sendBroadcastMessage(Message message) {
        connectionMap.forEach((name, con) -> {
            try {
                con.send(message);
            } catch (IOException e) {
                System.out.println("Ошибка. Сообщение не было отправлено");
            }
        });
    }

    private static class Handler extends Thread {
        private Socket socket;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        private String serverHandshake(Connection connection)
                throws IOException, ClassNotFoundException {
            String name = "";
            do {
                connection.send(new Message(MessageType.NAME_REQUEST));
                Message message = connection.receive();
                if (message.getType() == MessageType.USER_NAME) {
                    name = message.getData();
                    if (!name.isEmpty() && !connectionMap.containsKey(name)) {
                        connectionMap.put(name, connection);
                        connection.send(new Message(MessageType.NAME_ACCEPTED));
                        break;
                    }
                }
            } while (true);

            return name;
        }

        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException{
            while (true){
                Message msg = connection.receive();
                if(msg != null){
                    if(msg.getType() == MessageType.TEXT){
                        String newMessage = userName + ": " + msg.getData();
                        sendBroadcastMessage(new Message(MessageType.TEXT, newMessage));
                    }
                    else {
                        ConsoleHelper.writeMessage("Неверный формат сообщения");
                    }
                }
            }
        }

        private void notifyUsers(Connection connection, String userName)
                throws IOException{
            for(Map.Entry<String, Connection> pair: connectionMap.entrySet()){
                String name = pair.getKey();
                if(!userName.equals(name)){
                    connection.send(new Message(MessageType.USER_ADDED, name));
                }
            }
        }

        @Override
        public void run() {
            System.out.println("Установлено соединение с удаленным адресом " + socket.getRemoteSocketAddress());
            String user = null;
            try (Connection connection = new Connection(socket)){

                user = serverHandshake(connection);
                sendBroadcastMessage(new Message(MessageType.USER_ADDED, user));
                notifyUsers(connection, user);
                serverMainLoop(connection, user);

            } catch (IOException | ClassNotFoundException e){
                ConsoleHelper.writeMessage("Произошла ошибка во время обмена данными");
            } finally {
                if (user != null) {
                    connectionMap.remove(user);
                    sendBroadcastMessage(new Message(MessageType.USER_REMOVED, user));
                }
                ConsoleHelper.writeMessage("Соединение с удаленным адресом закрыто");
            }
        }
    }


    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        int port = ConsoleHelper.readInt();
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Соединение установлено");
            while (true) {
                Socket newSocket = serverSocket.accept();
                new Handler(newSocket).start();
            }
        } catch (IOException e) {
            System.out.println("Не удалось установить соединение");
        } finally {
            try {
                Objects.requireNonNull(serverSocket).close();
            } catch (IOException e) {
                System.out.println("Не удалось закрыть соединение");
            } catch (NullPointerException ne) {
                System.out.println("Не возможно закрыть соединение. Соединение отсутствует");
            }
        }

    }

}
