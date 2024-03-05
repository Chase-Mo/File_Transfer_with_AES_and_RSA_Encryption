import java.io.*;
import java.net.*;

public class serverServer {

    public serverServer(){

    }
    public void recieveSomething(String filename, int port) {

        try {
            ServerSocket serverSocket = new ServerSocket(port);


            while (true) {
                Socket clientSocket = serverSocket.accept();


                // Receive file
                InputStream inputStream = clientSocket.getInputStream();
                FileOutputStream fileOutputStream = new FileOutputStream(filename);
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    fileOutputStream.write(buffer, 0, bytesRead);
                }

                fileOutputStream.close();
                inputStream.close();
                clientSocket.close();

                break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
