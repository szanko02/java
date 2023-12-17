package org.example;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private List<Socket> clients;
    private List<String> availableImages;

    public Server() {
        clients = new ArrayList<>();
        availableImages = new ArrayList<>();
        availableImages.add("logo1.png.png");
        availableImages.add("src/logo2.png");
        availableImages.add("src/logo3.png");
    }

    public void start(int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server started. Listening on port " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                clients.add(clientSocket);
                System.out.println("Client connected: " + clientSocket.getInetAddress());

                Thread clientThread = new Thread(new ClientHandler(clientSocket));
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class ClientHandler implements Runnable {
        private Socket clientSocket;
        private BufferedReader reader;
        private PrintWriter writer;

        public ClientHandler(Socket socket) {
            clientSocket = socket;
            try {
                reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                writer = new PrintWriter(clientSocket.getOutputStream(), true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            try {
                String message;
                while ((message = reader.readLine()) != null) {
                    if (message.equals("GET_IMAGES")) {
                        sendImageList();
                    } else if (message.startsWith("SEND_IMAGE")) {
                        String[] parts = message.split(" ");
                        String imageName = parts[1];
                        int recipientIndex = -1;
                        if (parts.length > 2) {
                            recipientIndex = Integer.parseInt(parts[2]);
                        }
                        sendImageToClients(imageName, recipientIndex);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    clients.remove(this);
                    clientSocket.close();
                    System.out.println("Client disconnected: " + clientSocket.getInetAddress());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private void sendImageList() {
            StringBuilder sb = new StringBuilder();
            for (String imageName : availableImages) {
                sb.append(imageName).append(",");
            }
            writer.println(sb.toString());
        }

        private void sendImageToClient(String imageName, Socket clientSocket) {
            try {
                OutputStream outputStream = clientSocket.getOutputStream();
                FileInputStream fileInputStream = new FileInputStream(imageName);
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                outputStream.flush();
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void sendImageToClients(String imageName, int recipientIndex) {
            if (recipientIndex == -1) {
                // Send the image to all clients except the sender
                for (Socket clientSocket : clients) {
                    if (clientSocket != this.clientSocket) {
                        sendImageToClient(imageName, clientSocket);
                    }
                }
            } else if (recipientIndex >= 0 && recipientIndex < clients.size()) {
                // Send the image to a specific client
                Socket clientSocket = clients.get(recipientIndex);
                sendImageToClient(imageName, clientSocket);
            }
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.start(1234);
    }
}