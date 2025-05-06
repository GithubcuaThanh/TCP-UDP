/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UDP;

import java.util.*;
import java.net.*;
import java.io.*;
/**
 *
 * @author LaptopcuaThanh
 */
public class UDP_String {
//    public static void main(String[] args) throws Exception {
//        DatagramSocket socket = new DatagramSocket();
//        InetAddress sA = InetAddress.getByName("203.162.10.109");
//        int sP = 2208; // Cổng 2208 theo đề bài
//        
//        String ma = ";B19DCCN651;rOmZV4c8"; // Không được tự ý thay đổi đoạn String ma
//        DatagramPacket dpGui = new DatagramPacket(ma.getBytes(), ma.length(), sA, sP);
//        socket.send(dpGui);
//        
//        // Nhận phản hồi từ server
//        byte[] buffer = new byte[1024];
//        DatagramPacket dpNhan = new DatagramPacket(buffer, buffer.length);
//        socket.receive(dpNhan);
//        
//        String s1 = new String(dpNhan.getData(), 0, dpNhan.getLength()); // Đọc đúng số byte nhận được
//        System.out.println("Chuỗi nhận từ server: " + s1);
//        
//        // Phân tách chuỗi theo định dạng requestId;data
//        String[] sTmp = s1.trim().split(";", 2);
//        String requestId = sTmp[0];
//        String data = sTmp[1];
//        
//        // Chuẩn hóa chuỗi data
//        String[] words = data.trim().split("\\s+");
//        StringBuilder sb = new StringBuilder();
//        for (String word : words) {
//            sb.append(Character.toUpperCase(word.charAt(0)));
//            if (word.length() > 1) {
//                sb.append(word.substring(1).toLowerCase());
//            }
//            sb.append(" ");
//        }
//        
//        String dataChuanHoa = sb.toString().trim();
//        String res = requestId + ";" + dataChuanHoa;
//        
//        System.out.println("Chuỗi sau chuẩn hóa: " + res);
//        
//        // Gửi kết quả chuẩn hóa về server
//        DatagramPacket dpGui1 = new DatagramPacket(res.getBytes(), res.length(), sA, sP);
//        socket.send(dpGui1);
//        
//        // Đóng socket
//        socket.close();
//    }
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress serverAddress = InetAddress.getByName("203.162.10.109");
        int serverPort = 2208;
        
        // a. Gửi thông điệp chứa mã sinh viên và mã câu hỏi
        String studentCode = "B19DCCN651";  // Thay bằng mã sinh viên của bạn
        String qCode = "rOmZV4c8";         // Mã câu hỏi từ đề bài
        String ma = ";" + studentCode + ";" + qCode;
        
        DatagramPacket sendPacket = new DatagramPacket(ma.getBytes(), ma.length(), serverAddress, serverPort);
        socket.send(sendPacket);
        
        // b. Nhận thông điệp từ server
        byte[] receiveBuffer = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
        socket.receive(receivePacket);
        
        String receivedData = new String(receivePacket.getData()).trim();
        System.out.println("Received from server: " + receivedData);
        
        String[] parts = receivedData.split(";");
        String requestId = parts[0];
        String data = parts[1];
        
        // c. Xử lý chuẩn hóa chuỗi
        String[] words = data.trim().split("\\s+");
        StringBuilder normalizedData = new StringBuilder();
        
        for (String word : words) {
            if (word.length() > 0) {
                // Ký tự đầu in hoa, các ký tự sau in thường
                String normalizedWord = word.substring(0, 1).toUpperCase() + 
                                      word.substring(1).toLowerCase();
                normalizedData.append(normalizedWord).append(" ");
            }
        }
        
        String result = requestId + ";" + normalizedData.toString().trim();
        System.out.println("Sending to server: " + result);
        
        // Gửi kết quả đã chuẩn hóa về server
        DatagramPacket resultPacket = new DatagramPacket(
            result.getBytes(), 
            result.length(), 
            serverAddress, 
            serverPort
        );
        socket.send(resultPacket);
        
        // d. Đóng socket
        socket.close();
    }
}
