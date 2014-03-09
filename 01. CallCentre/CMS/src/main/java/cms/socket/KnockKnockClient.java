package cms.socket;


import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;


public class KnockKnockClient {
    public static void send(String hostName, int portNumber, String json) throws IOException, UnknownHostException {
		Socket kkSocket = new Socket(hostName, portNumber);
        ObjectOutput output = new ObjectOutputStream(kkSocket.getOutputStream());
        output.writeObject(json);
        kkSocket.close();
    }
}