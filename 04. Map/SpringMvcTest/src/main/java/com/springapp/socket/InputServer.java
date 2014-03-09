package com.springapp.socket;

import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;


public class InputServer extends  Thread{
    int portNumber;
    boolean listening;

    @Override
    public void run() {
        listening = true;
        try {
            ServerSocket serverSocket = new ServerSocket(portNumber);
            System.out.println("server started at " + InetAddress.getLocalHost() + ":" + serverSocket.getLocalPort());
            while (listening) {
                new InputServerThread(serverSocket.accept()).start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void start() {
        if (!this.isAlive())
            super.start();
    }
    public void setPortNumber(int portNumber) {
        this.portNumber = portNumber;
    }
}
