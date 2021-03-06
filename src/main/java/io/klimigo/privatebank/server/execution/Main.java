package io.klimigo.privatebank.server.execution;

import io.klimigo.privatebank.server.execution.jersey.JerseyBootstrapper;


public class Main {
    public static void main(String... args) {
        JerseyBootstrapper bootstrapper = new JerseyBootstrapper();

        bootstrapper.setupServer();

        try {
            bootstrapper.startServer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}