package com.springapp.socket;

import java.net.InetAddress;
import java.net.ServerSocket;

@Deprecated
public class InputServerSingleton extends  Thread{
    private static InputServerSingleton ourInstance = new InputServerSingleton();

    public static InputServerSingleton getInstance() {
        return ourInstance;
    }

    private InputServerSingleton() {
    }


    //	int portNumber = Config.SERVER_PORT;
    int portNumber = 0;
    boolean listening;

    @Override
    public void run(){
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
}
