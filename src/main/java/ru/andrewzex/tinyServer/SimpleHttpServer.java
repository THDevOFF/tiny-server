package ru.andrewzex.tinyServer;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.file.Files;

public class SimpleHttpServer {

    private final HttpServer server;

    public SimpleHttpServer(File dataFolder, String ip, int port) throws IOException {
        File webFolder = new File(dataFolder, "web");
        if (!webFolder.exists()) webFolder.mkdirs();

        server = HttpServer.create(new InetSocketAddress(ip, port), 0);
        server.createContext("/", new FileHandler(webFolder));
        server.setExecutor(null);
    }

    public void start() {
        server.start();
    }

    public void stop() {
        server.stop(0);
    }

    private static class FileHandler implements HttpHandler {
        private final File root;

        public FileHandler(File root) {
            this.root = root;
        }

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String path = exchange.getRequestURI().getPath();
            if (path.equals("/")) path = "/index.html"; // по умолчанию index.html
            File requested = new File(root, path).getCanonicalFile();

            if (!requested.getPath().startsWith(root.getCanonicalPath()) || !requested.exists() || requested.isDirectory()) {
                String notFound = "404 Not Found";
                exchange.sendResponseHeaders(404, notFound.length());
                exchange.getResponseBody().write(notFound.getBytes());
                exchange.close();
                return;
            }

            byte[] bytes = Files.readAllBytes(requested.toPath());
            exchange.sendResponseHeaders(200, bytes.length);
            exchange.getResponseBody().write(bytes);
            exchange.close();
        }
    }
}