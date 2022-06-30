package ru.netology.javacore;

import com.google.gson.Gson;

import javax.imageio.IIOException;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TodoServer {
    static class Request {
        String type, task;

        public Request(String type, String task) {
            this.task = task;
            this.type = type;
        }

        @Override
        public String toString() {
            return "type = '" + type + "', task = '" + task + "'";
        }

    }

    private final int port;
    private final Todos todos;

    public TodoServer(int port, Todos todos) {
        this.port = port;
        this.todos = todos;
    }

    public void start() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(this.port)) {
            System.out.println("\nStarting server at " + this.port + "... \nServer started...\n");
            while (true) {
                try (
                        Socket clientSocket = serverSocket.accept();
                        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                        PrintWriter out = new PrintWriter(clientSocket.getOutputStream());
                ) {
                    System.out.println("New connection accepted!");
                    System.out.println("Client address: " + clientSocket.getInetAddress() +
                            ", port: " + clientSocket.getPort());
                    String json = in.readLine();
                    System.out.println("Client message: " + json);
                    Request r = new Gson().fromJson(json, Request.class);
                    switch (r.type) {
                        case "Add":
                            System.out.println("Add task '" + r.task + "' to ToDo list");
                            todos.addTask(r.task);
                            break;

                        case "Remove":
                            System.out.println("Remove task '" + r.task + "' from ToDo list");
                            todos.removeTask(r.task);
                            break;
                    }
                    System.out.println("Send ToDo list to client... ");
                    out.println(todos.getAllTasks());
                    System.out.println("Complete!\n");
                }
            }
        } catch (IIOException e) {
            System.out.println("Server can't start :(");
            e.printStackTrace();
        }

    }

        @Override
        public String toString() {
            return "TodoServer { " +
                    "port = " + port +
                    ", todos = " + todos +
                    " } ";
        }

}
