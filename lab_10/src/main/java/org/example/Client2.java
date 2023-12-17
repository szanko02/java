package org.example;
import java.io.*;
import java.net.Socket;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Client2 {
    private Socket clientSocket;
    private BufferedReader reader;
    private PrintWriter writer;

    public Client2(String serverIP, int serverPort) {
        try {
            clientSocket = new Socket(serverIP, serverPort);
            reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            writer = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() throws IOException {
        try {
            Scanner scanner = new Scanner(System.in);
            String message;
            while (true) {
                System.out.println("1. Get image list");
                System.out.println("2. Send image to another client");
                System.out.println("3. Quit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        getImageList();
                        break;
                    case 2:
                        System.out.print("Enter the image name: ");
                        String imageName = scanner.nextLine();
                        System.out.print("Enter the recipient index: ");
                        int recipientIndex = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        sendImage(imageName, recipientIndex);
                        break;
                    case 3:
                        System.out.println("Quitting...");
                        return;
                    default:
                        System.out.println("Invalid choice");
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void getImageList() {
        writer.println("GET_IMAGES");
        try {
            String response = reader.readLine();
            String[] imageList = response.split(",");
            System.out.println("Available Images:");
            for (String imageName : imageList) {
                System.out.println(imageName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendImage(String imageName, int recipientIndex) {
        writer.println("SEND_IMAGE " + imageName + " " + recipientIndex);
        try {
            InputStream inputStream = clientSocket.getInputStream();
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                File file = new File(imageName);
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(buffer, 0, bytesRead);
                fileOutputStream.close();
            }
            System.out.println("Image received: " + imageName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Client2 client2 = new Client2("localhost", 1234);
        try {
            client2.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}