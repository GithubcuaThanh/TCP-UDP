/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tcp; // Khai báo package tên là tcp

import java.util.*;  // Import tất cả các class trong thư viện java.util
import java.io.*;    // Import tất cả các class trong thư viện java.io (nhập/xuất)
import java.net.*;   // Import tất cả các class trong thư viện java.net (mạng)

/**
 *
 * @author LaptopcuaThanh
 */
public class dang1 { // Khai báo class tên là dang1
    
    public static void main(String[] args) throws Exception { // Hàm main, điểm bắt đầu của chương trình. Có thể ném ngoại lệ.
        Socket socket = new Socket("203.162.10.109", 2207); // Tạo socket kết nối đến địa chỉ IP và cổng được chỉ định
        InputStream in = socket.getInputStream(); // Lấy luồng vào từ socket
        OutputStream out = socket.getOutputStream(); // Lấy luồng ra từ socket
        
        String ma = ";B19DCCN651;LGDTzVv1"; // Khai báo chuỗi mã gửi đến server
        
        out.write(ma.getBytes()); // Gửi chuỗi mã dưới dạng byte qua luồng ra
        out.flush(); // Đảm bảo dữ liệu đã được gửi đi ngay lập tức
        
        byte buffer[] = new byte[1024]; // Tạo mảng byte làm bộ đệm để nhận dữ liệu
        int len = in.read(buffer); // Đọc dữ liệu từ luồng vào và lưu độ dài nhận được
        String s = new String(buffer, 0, len); // Chuyển đổi dữ liệu nhận được thành chuỗi
        System.out.println(s); // In ra chuỗi nhận được
        
        s = s.replace(',', ' ').trim(); // Thay thế dấu phẩy bằng khoảng trắng và loại bỏ khoảng trắng đầu/cuối
        String a[] = s.split("\\s+"); // Tách chuỗi thành mảng các chuỗi con bằng khoảng trắng
        
        ArrayList<Long> b = new ArrayList<>(); // Khởi tạo danh sách để chứa các số long
        for(String x : a){ // Duyệt qua từng chuỗi con
            b.add(Long.parseLong(x)); // Chuyển đổi chuỗi thành số long và thêm vào danh sách
        }
        
        Long res = Long.MAX_VALUE; // Biến lưu giá trị hiệu nhỏ nhất giữa 2 số bất kỳ
        Long a1 = 1L * (-1); // Biến lưu giá trị số thứ nhất của cặp có hiệu nhỏ nhất
        Long a2 = 1L * (-1); // Biến lưu giá trị số thứ hai của cặp có hiệu nhỏ nhất
        Collections.sort(b); // Sắp xếp danh sách các số theo thứ tự tăng dần
        
        for(int i = 0; i < b.size(); i++){ // Vòng lặp duyệt qua danh sách các số
            Long tam = b.get(i) - b.get(i - 1); // Tính hiệu giữa hai phần tử liên tiếp
            if(tam <= res){ // Nếu hiệu này nhỏ hơn hoặc bằng hiệu nhỏ nhất hiện tại
                res = tam; // Cập nhật hiệu nhỏ nhất
                a1 = b.get(i - 1); // Cập nhật số thứ nhất
                a2 = b.get(i); // Cập nhật số thứ hai
            }
        }
        
        String tam1 = res + "," + a1 + "," + a2; // Ghép kết quả thành chuỗi kết quả
        System.out.println(tam1); // In ra kết quả
        out.write(tam1.getBytes()); // Gửi chuỗi kết quả trở lại server
        out.flush(); // Đảm bảo dữ liệu được gửi đi
        
        in.close(); // Đóng luồng vào
        out.close(); // Đóng luồng ra
        socket.close(); // Đóng kết nối socket
    }
}
