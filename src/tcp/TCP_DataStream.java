/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tcp;

import java.util.*;
import java.io.*;
import java.net.*;
/**
 *
 * @author LaptopcuaThanh
 */
public class TCP_DataStream {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("203.162.10.109",2207);
        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream()); 
        
        String ma = "B19DCCN651;lmyLzvUj";
//        out.write(ma.getBytes());
        out.writeUTF(ma);
        out.flush();
        
        int a = in.readInt();
        int b = in.readInt();
        
        System.out.println("a = "+a+ " b ="+b);
        
        int tong = a + b;
        int tich  = a*b;
        
        out.writeInt(tong);
        out.writeInt(tich);
        
        in.close();
        out.close();
        socket.close();
        
        
        
    }   

      
}
