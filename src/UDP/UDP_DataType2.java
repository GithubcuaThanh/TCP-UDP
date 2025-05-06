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
public class UDP_DataType2 {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress serverAddress = InetAddress.getByName("203.162.10.109");
        int serverPort = 2207;

        // a. Gửi thông điệp chứa mã sinh viên và mã câu hỏi
        String studentCode = "B19DCCN651"; // Thay bằng mã sinh viên của bạn
        String qCode = "mQGsmoVd";       // Mã câu hỏi từ đề bài
        String message = ";" + studentCode + ";" + qCode;

        DatagramPacket sendPacket = new DatagramPacket(
            message.getBytes(), 
            message.length(), 
            serverAddress, 
            serverPort
        );
        socket.send(sendPacket);
        System.out.println("Sent identification: " + message);

        // b. Nhận thông điệp từ server
        byte[] receiveBuffer = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
        socket.receive(receivePacket);

        String receivedData = new String(receivePacket.getData()).trim();
        System.out.println("Received from server: " + receivedData);

        // Phân tích dữ liệu nhận được
        String[] parts = receivedData.split(";");
        if (parts.length < 2) {
            System.out.println("Invalid data format from server");
            socket.close();
            return;
        }

        String requestId = parts[0];
        String numbersStr = parts[1];

        // Chuyển chuỗi số thành mảng
        String[] numberArray = numbersStr.split(",");
        int[] numbers = new int[numberArray.length];
        for (int i = 0; i < numberArray.length; i++) {
            try {
                numbers[i] = Integer.parseInt(numberArray[i]);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format: " + numberArray[i]);
                socket.close();
                return;
            }
        }

        // Kiểm tra nếu không đủ 2 phần tử
        if (numbers.length < 2) {
            System.out.println("Not enough numbers to find second max/min");
            socket.close();
            return;
        }

        // c. Tìm số lớn thứ hai và số nhỏ thứ hai
        Arrays.sort(numbers);
        int secondMax = numbers[numbers.length - 2]; // Số lớn thứ 2
        int secondMin = numbers[1];                 // Số nhỏ thứ 2

        // Tạo kết quả để gửi lại server
        String result = requestId + ";" + secondMax + "," + secondMin;
        System.out.println("Sending result: " + result);

        // Gửi kết quả về server
        DatagramPacket resultPacket = new DatagramPacket(
            result.getBytes(), 
            result.length(), 
            serverAddress, 
            serverPort
        );
        socket.send(resultPacket);

        // d. Đóng socket
        socket.close();
        System.out.println("Socket closed. Program finished.");
    }
}
