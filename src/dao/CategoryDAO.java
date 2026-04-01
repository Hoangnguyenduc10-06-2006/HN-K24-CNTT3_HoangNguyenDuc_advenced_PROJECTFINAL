package dao;

import model.Category;
import utill.ColerUtil;
import utill.DataConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryDAO {
    public static void insert(Category category){
        String sql = """
                INSERT INTO categories (name, description,status )VALUES(?,?,?);
                """;

        try(Connection connection=DataConnection.openConnection()){

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1,category.getName());
            preparedStatement.setString(2,category.getDescription());
            preparedStatement.setString(3,category.getStatus());


            int rows = preparedStatement.executeUpdate();
            if (rows == 0){
                System.out.println(ColerUtil.RED+"chưa thể lưu vào Database");
            }else {
                System.out.println(ColerUtil.GREEN+"lưu thành công vào Database");
            }

        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

    }
    public static boolean checkNameExist(String nameCategory){
        // check trùng nhưng: vì xóa mềm nên là sẽ xem điều kiện nếu trùng nhưng status= inactive thì đổi thành active còn nếu mà active thì hiển thị thông báo
        String sql = """
                SELECT * FROM categories WHERE name = ? ;
                """;

        try(Connection connection=DataConnection.openConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,nameCategory);

            ResultSet resultSet = preparedStatement.executeQuery();


            if(resultSet.next()){
                String check =resultSet.getString("status");

                if(check.equals("ACTIVE")){
                    return true;
                }else if(check.equals("INACTIVE")){

                    PreparedStatement ps = connection.prepareStatement("UPDATE Categories SET status = 'ACTIVE' WHERE name = ?");
                    ps.setString(1, nameCategory);

                    int rows = ps.executeUpdate();
                    if(rows == 0){
                        System.out.println(ColerUtil.RED+"khôi phục chưa thành công");
                        return true;
                    }else {
                        System.out.println(ColerUtil.GREEN+"khôi phục thành công trong databasse");
                        return false;
                    }
                }
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean checkIdExist(int id){
        String sql = """
                SELECT id FROM categories WHERE id = ? ;
                """;

        try(Connection connection=DataConnection.openConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                return true;
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void showlistCategory(){
        String sql = """
                select * from categories;
                """;

        try(Connection connection=DataConnection.openConnection()){
            PreparedStatement preparedStatement =connection.prepareStatement(sql);
            ResultSet resultSet =preparedStatement.executeQuery();

            boolean isEmpty = true;

            while (resultSet.next()){
                isEmpty = false;
                System.out.println(ColerUtil.PURPLE + "╔═════════════════════════════════════════════════════════════════════════════════════════════════════════════╗");
                System.out.printf(ColerUtil.PURPLE + "║" +
                                ColerUtil.BLUE + " %-5s | %-30s | %-30s | %-20s | %-10s " +
                                ColerUtil.PURPLE + "║\n",
                        "ID", "Tên danh mục", "Mô tả", "Ngày tạo", "Trạng thái");
                System.out.println(ColerUtil.PURPLE + "╠═════════════════════════════════════════════════════════════════════════════════════════════════════════════╣");

                // Nội dung bảng
                while (resultSet.next()) {
                    System.out.printf(ColerUtil.PURPLE + "║" +
                                    ColerUtil.PINK + " %-5d | %-30s | %-30s | %-20s | %-10s " +
                                    ColerUtil.PURPLE + "║\n",
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("description"),
                            resultSet.getString("created_at"),
                            resultSet.getString("status")
                    );
                }

                // Kết thúc bảng
                System.out.println(ColerUtil.PURPLE + "╚═════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");
            }
            if (isEmpty) {
                System.out.println(ColerUtil.RED+"Không có danh mục nào!");
            }
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

    }


    public static void getAllNameCategory(){
        String sql = """
                select id,name from categories;
                """;

        try(Connection connection=DataConnection.openConnection()){
            PreparedStatement preparedStatement =connection.prepareStatement(sql);
            ResultSet resultSet =preparedStatement.executeQuery();

            boolean isEmpty = true;

            while (resultSet.next()){
                isEmpty = false;
                // Tiêu đề bảng
                System.out.println(ColerUtil.PURPLE + "╔════════════════════════════════════════╗");
                System.out.printf(ColerUtil.PURPLE + "║" +
                        ColerUtil.BLUE + " %-5s | %-30s " +
                        ColerUtil.PURPLE + "║\n", "ID", "Tên danh mục");
                System.out.println(ColerUtil.PURPLE + "╠════════════════════════════════════════╣");

// Nội dung
                while (resultSet.next()) {
                    System.out.printf(ColerUtil.PURPLE + "║" +
                                    ColerUtil.PINK + " %-5d | %-30s " +
                                    ColerUtil.PURPLE + "║\n",
                            resultSet.getInt("id"),
                            resultSet.getString("name"));
                }

// Kết thúc bảng
                System.out.println(ColerUtil.PURPLE + "╚════════════════════════════════════════╝");
            }
            if (isEmpty) {
                System.out.println(ColerUtil.RED+"Không có danh mục nào!");
            }
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

    }
    public static void main(String[] args) {
        CategoryDAO.getAllNameCategory();
    }
    public static void updateCategory(Category category,int id){
        String sql = "UPDATE Categories SET name=?, description=? WHERE id=?";

        try(Connection conn = DataConnection.openConnection()){
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, category.getName());
            ps.setString(2, category.getDescription());
            ps.setInt(3, id);

            int rows = ps.executeUpdate();
            if(rows == 0){
                System.out.println(ColerUtil.RED+"cập nhật chưa thành công");
            }else {
                System.out.println(ColerUtil.GREEN+"cập nhật thành công trong databasse");
            }
        }catch(Exception e){
            e.printStackTrace();
        }

    }
    public static void deleteCategory(int id){
        String sql = "UPDATE Categories SET status = 'INACTIVE' WHERE id = ?";

        try(Connection conn = DataConnection.openConnection()){
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            if(rows == 0){
                System.out.println(ColerUtil.RED+"cập nhật chưa thành công. Đã thực hiện thay đổi trạng thái!!!");
            }else {
                System.out.println(ColerUtil.GREEN+"cập nhật thành công trong databasse");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
