package au.david.church.subtitle;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.net.*;
import java.util.List;

/**
 * Hello world!
 *
 */
public class Listener
{
    public static void main(String[] args) throws SocketException {

        if (args.length<3){
            System.err.println("Please provide port line hostname");
            System.exit(1);
        }

        File outputFile = new File("outputLine.txt");

        int port = Integer.parseInt(args[0]);
        int line = Integer.parseInt(args[1]);
        String hostname = args[2];
        System.out.println("Listening:"+hostname+":"+port);
        while(true) {
            try (Socket socket = new Socket(hostname, port)) {
                InputStream input = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                String text = reader.readLine();
                if (text!=null && !outputFile.exists()){
                    FileUtils.writeStringToFile(outputFile,text,"UTF-8");
                } else if (text!=null){
                    List<String> allList = FileUtils.readLines(outputFile,"UTF-8");
                    allList.add(text);

                    List<String> outputList = allList.subList(allList.size()-line, allList.size());
                    FileUtils.writeLines(outputFile,outputList);

                    //FileUtils.writeStringToFile(outputFile,text,"UTF-8",true);
                    //FileUtils.writeStringToFile(outputFile,"\n","UTF-8",true);
                }
                System.out.println(text);
            } catch (UnknownHostException ex) {
                System.out.println("Server not found: " + ex.getMessage());
            } catch (IOException ex) {
                System.out.println("I/O error: "+hostname+" " + ex.getMessage());
            }
        }
    }



}
