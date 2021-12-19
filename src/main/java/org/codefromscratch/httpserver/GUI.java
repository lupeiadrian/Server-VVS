package org.codefromscratch.httpserver;

import org.codefromscratch.httpserver.config.Configuration;
import org.codefromscratch.httpserver.config.ConfigurationManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class GUI extends JFrame{
    private JPanel panel1;
    private JButton STARTSERVERButton;
    private JCheckBox switchmain;
    private JTextField textField1;
    protected JLabel srvstatus;
    protected JLabel srvaddress;
    protected JLabel srvlist;
    private JPanel panelc1;
    private JPanel panelc2;
    private JPanel panelc3;

    public GUI(String title){
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panel1);
        this.pack();

        STARTSERVERButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                srvstatus.setText("running...") ;
                srvaddress.setText("10.4.76.83");
                srvlist.setText("8080");
                switchmain.setEnabled(true);
                panelc2.setVisible(true);
                panelc3.setVisible(true);


                System.out.println("Server starting...");

                ConfigurationManager.getInstace().loadConfigurationFile("src/main/resources/http.json");
                Configuration conf = ConfigurationManager.getInstace().getCurrentConfiguration();

                System.out.println("Using Port:"+ conf.getPort());
                System.out.println("Using webRoot:"+ conf.getWebroot());

                ServerSocket serverSocket = null;
                try {
                    serverSocket = new ServerSocket(conf.getPort());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                Socket socket = null;
                try {
                    socket = serverSocket.accept();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                InputStream inputStream = null;
                try {
                    inputStream = socket.getInputStream();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                OutputStream outputStream = null;
                try {
                    outputStream = socket .getOutputStream();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                String html= "<html lang=\"en\"><head><meta name=\"description\" content=\"Server VVS\" /><meta charset=\"utf-8\"><title>Server VVS</title><meta name=\"viewport\" content=\"width=device-width, initial-scale=1\"><meta name=\"author\" content=\"\"><link rel=\"stylesheet\" href=\"css/style.css\"><script src=\"http://code.jquery.com/jquery-latest.min.js\"></script></head><body><div class=\"container\"></div><script></script></body></html>";

                final String CRLF = "\n\r"; //13, 10

                String response =
                        "HTTP/1.1 200 OK" + CRLF + //Status line : HTTP VERSION RESPONSE CODE RESPONSE MESSAGE
                                "Content-Lenght" + html.getBytes().length + CRLF + //Header
                                CRLF +
                                html +
                                CRLF + CRLF;
                try {
                    outputStream.write(response.getBytes());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                try {
                    inputStream.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                try {
                    outputStream.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                try {
                    socket.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                try {
                    serverSocket.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }



            }



        });
        switchmain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                srvstatus.setText("maintenance...");
                panelc3.setVisible(false);
                switchmain.setEnabled(false);
            }
        });
    }

    public static void main(String[] args){
        JFrame frame =  new GUI("Server VVS");
        frame.setVisible(true);
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}



