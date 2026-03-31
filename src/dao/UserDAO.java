package dao;

import model.User;
import org.mindrot.jbcrypt.BCrypt;
import presentation.MenuAdmin;
import presentation.MenuCustomer;
import utill.ColerUtil;
import utill.DataConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    public static void register(User user){
        String sql = """ 
                    INSERT INTO Users(name,role,email,phone,address,password) VALUES(?,?,?,?,?,?)
                    """;
        try (Connection connection = DataConnection.openConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1,user.getNameValue());
            preparedStatement.setString(2,user.getRole());
            preparedStatement.setString(3,user.getGmailValue());
            preparedStatement.setString(4,user.getPhoneValue());
            preparedStatement.setString(5,user.getAddressValue());
            preparedStatement.setString(6,user.getPasswordValue());

            int rows = preparedStatement.executeUpdate();
            if (rows == 0){
                System.out.println(ColerUtil.YELLOW+"chưa thể lưu vào Database");
            }else {
                System.out.println(ColerUtil.GREEN+"lưu thành công vào Database");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean checkEmailExist(String gmail){
        String sql = """
                SELECT email FROM Users WHERE email = ? ;
                """;

        try(Connection connection=DataConnection.openConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,gmail);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                return true;
            }
            return false;

        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public static boolean checkPhoneExist(String phone){
        String sql = """
                SELECT phone FROM Users WHERE phone = ? ;
                """;

        try(Connection connection=DataConnection.openConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,phone);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                return true;
            }
            return false;

        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public static void login(String inputEmailinputEmail, String inputPasswordinputPassword){
        String sql = """
                SELECT * FROM Users WHERE email = ? ;
                """;
        try(Connection connection=DataConnection.openConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,inputEmailinputEmail);

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                String hashedPassword = resultSet.getString("password");

                if(BCrypt.checkpw(inputPasswordinputPassword,hashedPassword)){
                    System.out.println(ColerUtil.GREEN+"Đăng nhập thành công!!!");
                    if (resultSet.getString("role").equals("CUSTOMER")){
                        MenuCustomer.main(inputEmailinputEmail,inputPasswordinputPassword,resultSet.getInt("id"));
                    } else if (resultSet.getString("role").equals("ADMIN")) {
                        MenuAdmin.main();
                    }else {
                        System.out.println(ColerUtil.RED+"lỗi: không xác định được role!!!!");
                    }
                }
            }else {
                System.out.println(ColerUtil.RED+"tài khoản hoặc mật khẩu không đúng.vui lòng nhập lại!!!");
            }

        } catch (SQLException e) {
            System.out.println(ColerUtil.RED+"tài khoản ko hợp lệ");
        }

    }

    public static void getAllUsers() {

        String sql = "SELECT * FROM users";

        try (Connection conn = DataConnection.openConnection()) {

            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            System.out.println(ColerUtil.PURPLE + "╔═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗");

            System.out.printf("║"+ColerUtil.BLUE + " %-5s | %-20s | %-25s | %-12s | %-8s | %-20s | %-20s | %-10s "+ColerUtil.PURPLE+"║\n",
                    "ID", "Tên", "Email", "SĐT", "vai trò","Địa chỉ","ngày tạo","trạng thái");

            System.out.println(ColerUtil.PURPLE + "╠═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╣");

            while (rs.next()) {
                System.out.printf("║"+ColerUtil.BLUE +" %-5d | %-20s | %-25s | %-12s | %-8s | %-20s | %-20s | %-10s "+ColerUtil.PURPLE+"║\n",
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("role"),
                rs.getString("address"),
                rs.getString("created_at"),
                rs.getString("status"));
            }

            System.out.println(ColerUtil.PURPLE + "╚═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");

        } catch (SQLException e) {
            System.out.println("Lỗi hiển thị user!");
        }
    }
    public static void deleteUser(int userId) {

        String sql = "UPDATE users SET status = 'INACTIVE' WHERE id = ? and role =? " ;

        try (Connection conn = DataConnection.openConnection()) {

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setString(2, "CUSTOMER");

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println(ColerUtil.RED+"Đã khóa tài khoản!");
            } else {
                System.out.println(ColerUtil.RED+"Không đước khóa admin hoặc tìm thấy user!");
            }

        } catch (SQLException e) {
            System.out.println("Lỗi xóa user!");
        }
    }
}
