package au.david.church.subtitle;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Hello world!
 *
 */
public class Listener
{
    public static void main(String[] args) {

        if (args.length<2){
            System.err.println("Please provide hostname and port");
            System.exit(1);
        }

        File outputFile = new File("outputLine.txt");

        String hostname = args[0];
        int port = Integer.parseInt(args[1]);
        System.out.println("Listening:"+hostname+":"+port);
        while(true) {
            try (Socket socket = new Socket(hostname, port)) {
                InputStream input = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                String text = reader.readLine();
                if (text!=null){
                    FileUtils.writeStringToFile(outputFile,text,"UTF-8",true);
                    FileUtils.writeStringToFile(outputFile,"\n","UTF-8",true);
                }
                System.out.println(text);
            } catch (UnknownHostException ex) {
                System.out.println("Server not found: " + ex.getMessage());
            } catch (IOException ex) {
                System.out.println("I/O error: " + ex.getMessage());
                try {
                    Thread.sleep(1000);
                } catch (Exception e){}
            }
        }
    }
}
