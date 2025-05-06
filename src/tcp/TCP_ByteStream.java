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
public class TCP_ByteStream {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("203.162.10.109",2206);
        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();
        
        String ma = "B19DCCN651;axRvjbIk";
        out.write(ma.getBytes());
        out.flush();
        
        
        byte[] buffer = new byte[1024]; // Tạo buffer để nhận dữ liệu
        int len = in.read(buffer); // Đọc dữ liệu vào buffer
        String s = new String(buffer, 0, len); // Chuyển dữ liệu nhận được thành chuỗi
        System.out.println("Chuỗi nhận từ server:");
        System.out.println(s); // In ra chuỗi để kiểm tra

        String[] numbers = s.trim().split("\\|"); // Tách chuỗi theo ký tự "|"
        int sum = 0; // Biến tính tổng
        for (String num : numbers) { // Duyệt qua từng số
            sum += Integer.parseInt(num.trim()); // Chuyển sang số nguyên và cộng vào tổng
        }

        String result = String.valueOf(sum); // Chuyển tổng thành chuỗi
        System.out.println("Tổng các số nguyên:");
        System.out.println(result); // In tổng ra màn hình

        out.write(result.getBytes()); // Gửi kết quả về server
        out.flush(); // Gửi ngay lập tức

        // Đóng kết nối
        in.close();
        out.close();
        socket.close();
        
        
        
    }
}


