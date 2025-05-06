/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UDP;

import java.util.*;
import java.io.*;
import java.net.*;
/**
 *
 * @author LaptopcuaThanh
 */
public class UDP_DataType {
    public static void main(String[] args) throws Exception {
        // Tạo socket UDP
        DatagramSocket socket = new DatagramSocket();
        // Địa chỉ IP server và cổng giao tiếp
        InetAddress serverAddress = InetAddress.getByName("203.162.10.109");
        int serverPort = 2207;

        // a. Gửi thông điệp chứa mã sinh viên và mã câu hỏi
        String ma = ";B19DCCN651;LGDTzVv1";
        DatagramPacket dpGui = new DatagramPacket(ma.getBytes(), ma.length(), serverAddress, serverPort);
        socket.send(dpGui);

        // b. Nhận thông điệp từ server
        byte[] buffer = new byte[4096];
        DatagramPacket dpNhan = new DatagramPacket(buffer, buffer.length);
        socket.receive(dpNhan);

        String s1 = new String(dpNhan.getData(), 0, dpNhan.getLength()).trim();
        System.out.println("Nhận từ server: " + s1);

        // Parse dữ liệu nhận được
        String[] sTmp = s1.split(";");
        String requestId = sTmp[0];
        String numbersStr = sTmp[1];

        // Tách 50 số nguyên
        String[] arrNumbers = numbersStr.split(",");
        int[] numbers = new int[arrNumbers.length];
        for (int i = 0; i < arrNumbers.length; i++) {
            numbers[i] = Integer.parseInt(arrNumbers[i]);
        }

        // c. Tìm giá trị lớn nhất và nhỏ nhất
        int max = numbers[0], min = numbers[0];
        for (int num : numbers) {
            if (num > max) max = num;
            if (num < min) min = num;
        }

        // Gửi kết quả lên server
        String res = requestId + ";" + max + "," + min;
        DatagramPacket dpGui1 = new DatagramPacket(res.getBytes(), res.length(), serverAddress, serverPort);
        socket.send(dpGui1);

        System.out.println("Đã gửi kết quả: " + res);

        // d. Đóng socket
        socket.close();
    }
}
