package com.rogermiranda1000.tester;

import com.rogermiranda1000.entities.*;

import java.net.Socket;

public class Tester {
    private final TesterConnector connector;

    public Tester(TesterConnector connector) {
        this.connector = connector;
    }

    public static void main(String[] args) {
        try {
            Socket serversManagerSocket = new Socket("172.31.103.244", 8000);
            Tester tester = new Tester(new TesterConnector(serversManagerSocket));
            tester.connector.startServer(null, null, new Map[]{}, new Plugin[]{}, ServerType.Spigot, "1.17.1", new ConfigFile[]{});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
