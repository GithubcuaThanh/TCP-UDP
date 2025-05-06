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
public class TCP_ObjectStream {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("203.162.10.169",2209);
        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        
        String ma = "B19DCCN651;hkjdhfjksd";
        out.writeUTF(ma);
        
        int a = in.readInt();
        int b = in.readInt();
        
        int tong = a+b;
                
        out.write(tong);
        
        socket.close();
    }
}
