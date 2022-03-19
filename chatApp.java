/*
    Project Name : ChatApp
    Author : @Omkore
    Description : This is a chatApp created using sockets.
    Purpose : MicroProject
*/

// Importing Required Packages
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;



// class is created
public class chatApp implements ActionListener {

    // Defining Constants Properties 
    public JFrame f;
    public JFrame l;
    public JFrame h;

    public JPanel left;
    public JPanel right;

    public JProgressBar jp;

    public JLabel progress;
    public JLabel warning;
    public JLabel UserNameTittle;

    public JTextField usernaemtf;
    public JTextField tf;
    public JTextArea ta;

    public JButton chat_btn;

    public String chk ;
    public String contentToSend;
    public String Content;

    ServerSocket server;
    Socket socket;
    BufferedReader br;
    PrintWriter out;


   

    // calling Constructor
    public chatApp(){

    //   creating obj of Frame 
    f = new JFrame();
    f.setUndecorated(true);

    // Setting Background using JLabel
    JLabel bg = new JLabel("",new ImageIcon("bg.jpg"),JLabel.CENTER);
    bg.setSize(900,500);
    f.add(bg);

    //Adding Titel Of the App
    JLabel titel = new JLabel("ChatApp");
    titel.setSize(900,500);
    titel.setHorizontalAlignment(SwingConstants.CENTER);
    titel.setVerticalAlignment(SwingConstants.CENTER);
    titel.setFont(new Font("Forte",Font.BOLD,40));
    titel.setForeground(Color.WHITE);
    bg.add(titel);

    //Adding SubTitel Of the App
    JLabel titel2 = new JLabel("Chat here with anyone Randomly...!");
    titel2.setBounds(300,50,500,500);
    titel2.setFont(new Font("Comic Sans MS",Font.ITALIC,20));
    titel2.setForeground(Color.WHITE);
    bg.add(titel2);

    // Adding Label for progress Display
    progress = new JLabel("");
    progress.setFont(new Font("imagine font",Font.ITALIC,14));
    progress.setForeground(Color.WHITE);
    progress.setBounds(850, 450, 50, 20);
    bg.add(progress);

    //Adding ProgressBar s
    jp = new JProgressBar();
    jp.setValue(0);
    jp.setBounds(10,480,880,10);
    bg.add(jp);

    f.setVisible(true);
    f.setSize(900,500);
    f.setLocationRelativeTo(null);
    // f.setLayout(null);

    // Adding Login Frame
    l = new JFrame();
    l.setVisible(false);
    l.setSize(900,500);
    l.setLocationRelativeTo(null);
    l.setUndecorated(true);

    //Adding Left JPanel
    left = new JPanel();
    left.setLayout(new BorderLayout());
    left.setBounds(0,0,300,500);
    left.setBackground(Color.lightGray);
    l.add(left);

    //Adding LgoText in Left JPanel

    JLabel logo = new JLabel("Chat App");
    logo.setHorizontalAlignment(SwingConstants.CENTER);
    logo.setVerticalAlignment(SwingConstants.CENTER);
    logo.setFont(new Font("imagine font",Font.ITALIC,30));
    logo.setForeground(Color.DARK_GRAY);
    left.add(logo);

    //Adding Right JPanel
    right = new JPanel();
    right.setBounds(0,0,300,500);
    right.setBackground(Color.PINK);
    right.setLayout(null);
    l.add(right);

    //Adding Components in Right JPanel
    
    JLabel username = new JLabel("Enter your name here : ");
    username.setHorizontalAlignment(SwingConstants.CENTER);
    username.setVerticalAlignment(SwingConstants.CENTER);
    username.setFont(new Font("imagine font",Font.PLAIN,20));
    username.setBounds(130,100,700,200);
    right.add(username);
    Cursor cursor = new Cursor(2);

    usernaemtf = new JTextField(25);
    usernaemtf.setFont(new Font("imagine font",Font.ITALIC,20));
    usernaemtf.setBounds(650,185,200,30);
    usernaemtf.setHorizontalAlignment(SwingConstants.CENTER);;
    usernaemtf.setBackground(Color.PINK);   
    usernaemtf.setCursor(cursor);
    usernaemtf.setBorder(BorderFactory.createMatteBorder(0, 0, 5, 0, Color.WHITE));
    right.add(usernaemtf);


    chat_btn = new JButton("Start Chatting");
    chat_btn.setFont(new Font("imagine font",Font.BOLD,15));
    chat_btn.setCursor(new Cursor(Cursor.TEXT_CURSOR));
    chat_btn.setBounds(470,260,200,30);
    chat_btn.setBackground(Color.PINK);
    chat_btn.setForeground(Color.DARK_GRAY);
    chat_btn.setBorderPainted(true);
    chat_btn.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.WHITE));
    chat_btn.addActionListener(this);
    right.add(chat_btn);

    warning = new JLabel("please enter your name !");
    warning.setBounds(420,350,300,30);
    warning.setForeground(Color.red);
    warning.setHorizontalAlignment(SwingConstants.CENTER);
    warning.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.RED));
    warning.setVisible(false);
    right.add(warning);

    //home screen Frame
    h = new JFrame();
    h.setUndecorated(true);
    
    //Jpaned added
    JPanel mainPanel = new JPanel();
    mainPanel.setBackground(Color.lightGray);
    mainPanel.setBounds(0,0,900,500);
    mainPanel.setLayout(null);
    h.add(mainPanel);
    
    //Components added
    JLabel heading =  new JLabel("ChatApp");
    heading.setForeground(Color.DARK_GRAY);
    heading.setFont(new Font("Impact",Font.PLAIN,20));
    heading.setBounds(400,0,200,40);
    mainPanel.add(heading);
    
    JButton closeBtn=  new JButton("X");
    closeBtn.setForeground(Color.RED);
    // closeBtn.setFont(new Font("Stencil",Font.PLAIN,10));
    closeBtn.setBackground(Color.LIGHT_GRAY);
    closeBtn.setVerticalTextPosition(SwingConstants.TOP);
    closeBtn.setHorizontalTextPosition(SwingConstants.LEFT);
    closeBtn.setBounds(830,05,60,30);

    mainPanel.add(closeBtn);
    
    UserNameTittle =  new JLabel();
    UserNameTittle.setForeground(Color.DARK_GRAY);
    UserNameTittle.setFont(new Font("Comic Sans MS",Font.ITALIC,15));
    UserNameTittle.setBounds(20,20,200,20);
    mainPanel.add(UserNameTittle);

    
    
    ta = new JTextArea();
    ta.setBackground(Color.darkGray);
    ta.setForeground(Color.WHITE);
    ta.setBounds(10,40,880,420);
    ta.setFont(new Font("Comic Sans MS",Font.BOLD,15));
    ta.setEditable(false);
    JScrollPane js = new JScrollPane(ta);
    js.setBounds(10,40,880,420);
    ta.setAutoscrolls(true);
    mainPanel.add(js);

   
    tf = new JTextField();
    tf.setBounds(10,470,880,25);
    tf.setBackground(Color.darkGray);
    tf.setForeground(Color.WHITE);
    tf.setFont(new Font("Comic Sans MS",Font.PLAIN,13));

    mainPanel.add(tf);

    h.setVisible(false);
    h.setSize(900,500);
    h.setLocationRelativeTo(null); 
    }

    // calling Main Method 
    public static void main(String[] args) {

        // Creating / Instantiating obj of class chatApp
        chatApp c = new chatApp();
        
        // Calling Cleint Method
        c.client();

        // calling progreessBar  Method 
        c.progressBar();

        // calling handlerEvents  Method 
         c.handlerEvents();
    }

    // Creating ProgressBar  Method 
    public void progressBar() {
        int i=0;
        try { 
            while(i<=100){
                    Thread.sleep(100); 
                    if(i==100)
                    {
                        f.setVisible(false);
                        l.setVisible(true); 
                    }
                        i=i+20;
                        jp.setValue(i);
                        progress.setText(Integer.toString(i)+"%");  
            }
            
    } catch (Exception e)
     {
        e.printStackTrace();
     }   
}  

    // Creating Btn ActionPerformed  Method 
    public void actionPerformed(ActionEvent e){

    String userName = usernaemtf.getText();

    if(userName.equals("")){
       warning.setVisible(true);
    }
    else{
        UserNameTittle.setText("Welcome "+usernaemtf.getText());
        warning.setVisible(false);
        l.setVisible(false);
        h.setVisible(true);
    }
}

    // Creating Btn handlerEvents  Method 
    public void handlerEvents() {
    tf.addKeyListener(new KeyListener(){

        @Override
        public void keyTyped(KeyEvent e) {
     
            
        }

        @Override
        public void keyPressed(KeyEvent e) {
        
            
        }

        @Override
        public void keyReleased(KeyEvent e) {
            // System.out.println()
            if(e.getKeyCode()==10){
                String contentToSend = tf.getText();
                ta.append("Me :"+ contentToSend +"\n");
                out.println(contentToSend);
                out.flush();
                tf.setText("");
                tf.requestFocus();
            }
            
        }

    });
}

    // Client Code

    public void client(){
        
        try {
            System.out.println("sending req. to server");
            socket = new Socket("192.168.204.12",7777);
            System.out.println("Conn. Done !");
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());
            startReading();
            startWriting();

        } catch (Exception e) {
           
        }
    }

    public void startReading() {
    Runnable r1 = () -> {

        System.out.println("Reader Started..");

        try{
        while(true){
            String msg = br.readLine();
            if(msg.equals("exit")){

                System.out.println("Server terminated the chat");
                JOptionPane.showMessageDialog(h, "Server Terminated the Chat");
                tf.setEnabled(false);
                socket.close();
                break;
            }

            // System.out.println("Server :"+msg);
            ta.append("Server :"+msg+"\n");
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

       try {
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
       }catch (Exception e) {
        e.printStackTrace();
    }

   };
   new Thread(r2).start();
}
}
