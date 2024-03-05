import java.io.*;
import java.net.*;

public class clientServer {
    public clientServer() {
        }

    public void sendSomething(String filename, String addr, int port)
    {


        try {
            while (true) {
                Socket socket = new Socket(addr, port);

                // Send file
                File fileToSend = new File(filename);
                byte[] buffer = new byte[(int) fileToSend.length()];
                FileInputStream fileInputStream = new FileInputStream(fileToSend);
                OutputStream outputStream = socket.getOutputStream();

                int bytesRead;
                while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }

                fileInputStream.close();
                outputStream.close();
                socket.close();

                //Kill!
                break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}