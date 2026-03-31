package dao;

import presentation.MenuLogin;
import utill.ColerUtil;
import utill.DataConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAO {
    public static void main(String[] args) {
        CustomerDAO.selectINformationCustomer("duc@gmail.com","1234567899");
    }
    //PHƯƠNG THỨC Case 1
    public static void selectINformationCustomer(String emailUserLogging,String passwordUserLogging){
        String sql = """
        SELECT name, email, phone, address, password 
        FROM users 
        WHERE email = ?
    """;

        try (Connection connection = DataConnection.openConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, emailUserLogging);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String dbPassword = rs.getString("password");


                if (org.mindrot.jbcrypt.BCrypt.checkpw(passwordUserLogging, dbPassword)) {

                    String name = rs.getString("name");
                    String phone = rs.getString("phone");
                    String address = rs.getString("address");

                    // Tiêu đề bảng
                    System.out.println(ColerUtil.PURPLE + "╔═══════════════════════════════════════════════════════════════════════════════════╗");
                    System.out.printf(ColerUtil.PURPLE + "║" +
                                    ColerUtil.BLUE + " %-15s | %-25s | %-12s | %-20s " +
                                    ColerUtil.PURPLE + "║\n",
                            "Tên", "Email", "SĐT", "Địa chỉ");
                    System.out.println(ColerUtil.PURPLE + "╠═══════════════════════════════════════════════════════════════════════════════════╣");

                    // Nội dung
                    System.out.printf(ColerUtil.PURPLE + "║" +
                                    ColerUtil.PINK + " %-15s | %-25s | %-12s | %-20s " +
                                    ColerUtil.PURPLE + "║\n",
                            name, emailUserLogging, phone, address);

                    // Kết thúc bảng
                    System.out.println(ColerUtil.PURPLE + "╚═══════════════════════════════════════════════════════════════════════════════════╝");

                } else {
                    System.out.println(ColerUtil.RED+"Sai mật khẩu!");
                }

            } else {
                System.out.println(ColerUtil.RED+"Email không tồn tại!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    };

    public static void updateINformationCustomer(String emailUserLogging,String name,String email,String phone,String address){
        String sql = """
                UPDATE users
                SET  name= ?,
                     email= ?,
                    phone= ?,
                    address= ?
                WHERE email =? ;
                """;
            try (Connection connection =DataConnection.openConnection()){
                PreparedStatement preparedStatement =connection.prepareStatement(sql);
                preparedStatement.setString(1,name);
                preparedStatement.setString(2,email);
                preparedStatement.setString(3,phone);
                preparedStatement.setString(4,address);
                preparedStatement.setString(5,emailUserLogging);


                int row =preparedStatement.executeUpdate();

                if(row ==0){
                    System.out.println(ColerUtil.RED+"chưa cập nhật thành công!!!!");
                }else {
                    System.out.println(ColerUtil.GREEN+"cập nhật thành công! tiến hành Đăng xuất tài khoản");
                    MenuLogin.main();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

    }
    public static void deleteUser(int userId) {

        String sql = "UPDATE users SET status = 'INACTIVE' WHERE id = ?";

        try (Connection conn = DataConnection.openConnection()) {

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Đã khóa tài khoản!");
            } else {
                System.out.println("Không tìm thấy user!");
            }

        } catch (SQLException e) {
            System.out.println("Lỗi xóa user!");
        }
    }
}
