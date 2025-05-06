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
//public class TCP_CharacterSteam {
//    public static void main(String[] args) throws Exception {
//        Socket socket = new Socket("203.162.10.109",2208);
//        InputStream in = socket.getInputStream();
//        OutputStream out = socket.getOutputStream();
//        
//        String ma = "B19DCCN651;V91h6qoy";
//        
//        out.write(ma.getBytes());
//        out.flush();
//        
//        
//        
//        
//    }
//}

public class TCP_CharacterSteam { // Khai báo class chính
    public static void main(String[] args) throws Exception { // Hàm main có thể ném ngoại lệ
        Socket socket = new Socket("203.162.10.109", 2208); // Tạo kết nối socket tới server
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream())); // Tạo luồng đọc
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())); // Tạo luồng ghi

        String ma = "B19DCCN651;V91h6qoy"; // Chuỗi gửi đi gồm mã sinh viên và mã câu hỏi
        writer.write(ma); // Gửi chuỗi tới server
        writer.newLine(); // Gửi ký tự xuống dòng để server biết đã hết chuỗi
        writer.flush(); // Đảm bảo dữ liệu được gửi ngay lập tức

        String s = reader.readLine(); // Đọc phản hồi từ server (danh sách tên miền)
        System.out.println("Danh sach ten mien tu server:");
        System.out.println(s); // In ra chuỗi nhận được

        String[] domains = s.split(",\\s*"); // Tách danh sách tên miền theo dấu phẩy và khoảng trắng
        ArrayList<String> eduDomains = new ArrayList<>(); // Tạo danh sách chứa tên miền .edu

        for (String domain : domains) { // Duyệt qua từng tên miền
            if (domain.trim().endsWith(".edu")) { // Kiểm tra phần đuôi tên miền
                eduDomains.add(domain.trim()); // Thêm vào danh sách nếu là .edu
            }
        }

        String result = String.join(", ", eduDomains); // Nối danh sách tên miền .edu thành một chuỗi
        System.out.println("Cac ten mien .edu:");
        System.out.println(result); // In kết quả ra màn hình

        writer.write(result); // Gửi chuỗi kết quả về cho server
        writer.newLine(); // Xuống dòng để server nhận biết kết thúc
        writer.flush(); // Đảm bảo gửi đi ngay

        reader.close(); // Đóng luồng đọc
        writer.close(); // Đóng luồng ghi
        socket.close(); // Đóng kết nối
    }
}