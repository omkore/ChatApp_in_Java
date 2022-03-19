import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;



public class server {
    ServerSocket server;
    Socket socket;
    BufferedReader br;
    PrintWriter out;

    public server(){
        try{
            server = new ServerSocket(7777);
            System.out.println("Server is ready");
            System.out.println("Waiting..");
            socket=server.accept();
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());
            startReading();
            startWriting();

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void startReading() {
        Runnable r1 = () -> {

            System.out.println("Reader Started..");

            try{
            while(true){
                String msg = br.readLine();
                if(msg.equals("exit")){

                    System.out.println("Client terminated the chat");
                    socket.close();
                    break;
                }

                System.out.println("Client :"+msg);

            }
        }catch(Exception e){
            e.printStackTrace();
       }
        };
        new Thread(r1).start();

    }

    public void startWriting() {
         Runnable r2 = () -> {
        
            System.out.println("Writer Started..");
            try{
            while(true){
               
                    BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
                    String Content = br1.readLine();
                    out.println(Content);
                    out.flush();
                    if(Content.equals("exit")){
                        socket.close();
                        break;
                    }

            }
        }catch(Exception e){
            e.printStackTrace();
        }

        };
        new Thread(r2).start();

    }
    public static void main(String[] args) {
        new server();
    }
}
