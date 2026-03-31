package dao;

import model.Product;
import utill.ColerUtil;
import utill.DataConnection;

import java.sql.*;


public class ProductDAO {
    public static double getPriceById(int productId) {
        String sql = "SELECT price FROM Products WHERE id = ?";

        try (Connection conn = DataConnection.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, productId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getDouble("price");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return 0;
    }

    public static void insertProduct(Product product){
        String sql = """
                INSERT INTO Products (name, price, stock, category_id, brand, status, storage, color, description)
                   VALUES(?,?,?,?,?,?,?,?,?)
                """;

        try(Connection connection=DataConnection.openConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1,product.getName());
            preparedStatement.setDouble(2,product.getPrice());
            preparedStatement.setInt(3,product.getStock());
            preparedStatement.setInt(4,product.getCategory_id());
            preparedStatement.setString(5,product.getBrand());
            preparedStatement.setString(6,product.getStatus());
            preparedStatement.setInt(7,product.getStorage());
            preparedStatement.setString(8,product.getColor());
            preparedStatement.setString(9,product.getDescription());

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
    public static void selectAllProduct(int page,int size){
        String sql ="select * from products limit ? offset ? ";
        try(Connection connection=DataConnection.openConnection()){
            int offset = (page-1)*size;

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1,size);
            ps.setInt(2,offset);
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                System.out.println(ColerUtil.RED+"Không có dữ liệu!");
            } else {
                do {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    double price = rs.getDouble("price");
                    int stock = rs.getInt("stock");
                    int category = rs.getInt("category_id");
                    String brand = rs.getString("brand");
                    String status = rs.getString("status");

                    System.out.println(ColerUtil.PURPLE + "╔════════════════════════════════════════════╗");

                    System.out.printf(ColerUtil.PURPLE + "║ " + ColerUtil.BLUE + "%-15s" + ColerUtil.PINK + "%-25s" + ColerUtil.PURPLE + "   ║\n",
                            "Mã SP:", id);

                    System.out.printf(ColerUtil.PURPLE + "║ " + ColerUtil.BLUE + "%-15s" + ColerUtil.PINK + "%-25s" + ColerUtil.PURPLE + "   ║\n",
                            "Tên SP:", name);

                    System.out.printf(ColerUtil.PURPLE + "║ " + ColerUtil.BLUE + "%-15s" + ColerUtil.PINK + "%-25s" + ColerUtil.PURPLE + "   ║\n",
                            "Giá:", price);

                    System.out.printf(ColerUtil.PURPLE + "║ " + ColerUtil.BLUE + "%-15s" + ColerUtil.PINK + "%-25s" + ColerUtil.PURPLE + "   ║\n",
                            "Tồn kho:", stock);

                    System.out.printf(ColerUtil.PURPLE + "║ " + ColerUtil.BLUE + "%-15s" + ColerUtil.PINK + "%-25s" + ColerUtil.PURPLE + "   ║\n",
                            "Loại:", category);

                    System.out.printf(ColerUtil.PURPLE + "║ " + ColerUtil.BLUE + "%-15s" + ColerUtil.PINK + "%-25s" + ColerUtil.PURPLE + "   ║\n",
                            "Thương hiệu:", brand);

                    System.out.printf(ColerUtil.PURPLE + "║ " + ColerUtil.BLUE + "%-15s" + ColerUtil.PINK + "%-25s" + ColerUtil.PURPLE + "   ║\n",
                            "Trạng thái:", status);

                    System.out.println(ColerUtil.PURPLE + "╚════════════════════════════════════════════╝");
                } while (rs.next());
            }
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
    public static boolean checkIdExist(int id){
        String sql = """
                SELECT id FROM products WHERE id = ? ;
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
    public static boolean checkNameExist(String name){
        String sql = """
                SELECT name FROM products WHERE name = ? ;
                """;

        try(Connection connection=DataConnection.openConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,name);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                return true;
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static boolean checkBrandExist(String brand){
        String sql = """
                SELECT brand FROM products WHERE brand = ? ;
                """;

        try(Connection connection=DataConnection.openConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,brand);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                return true;
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static int countProduct(){
        String sql = """
                select count(*) as countProduct from products;
                """;

                try(Connection connection =DataConnection.openConnection()){
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    ResultSet resultSet =preparedStatement.executeQuery();

                    if(resultSet.next()){
                        return resultSet.getInt("countProduct");
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
        return 0;
    }
    public static void orderByPriceProductASC(int page, int size){
        String sql ="select * from products ORDER BY price asc limit ? offset ? ";
        try(Connection connection=DataConnection.openConnection()){
            int offset = (page-1)*size;

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1,size);
            ps.setInt(2,offset);
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                System.out.println(ColerUtil.RED+"Không có dữ liệu!");
            } else {
                do {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    double price = rs.getDouble("price");
                    int stock = rs.getInt("stock");
                    int category = rs.getInt("category_id");
                    String brand = rs.getString("brand");
                    String status = rs.getString("status");

                    System.out.println(ColerUtil.PURPLE + "╔════════════════════════════════════════════════════════════╗");
                    System.out.printf(ColerUtil.PURPLE + "║" +
                                    ColerUtil.BLUE + "%-5s | %-15s | %-10s | %-8s | %-10s" +
                                    ColerUtil.PURPLE + "║\n",
                            "ID", "NAME", "PRICE", "STOCK", "STATUS");
                    System.out.println(ColerUtil.PURPLE + "╠════════════════════════════════════════════════════════════╣");
                    System.out.printf(ColerUtil.PURPLE + "║" +
                                    ColerUtil.PINK + "%-5d | %-15s | %-10.2f | %-8d | %-10s" +
                                    ColerUtil.PURPLE + "║\n",
                            id, name, price, stock, status);
                    System.out.println(ColerUtil.PURPLE + "╚════════════════════════════════════════════════════════════╝");
                } while (rs.next());
            }
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
    public static void orderByPriveProductDESC(int page,int size){
        String sql ="select * from products ORDER BY price desc limit ? offset ? ";
        try(Connection connection=DataConnection.openConnection()){
            int offset = (page-1)*size;

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1,size);
            ps.setInt(2,offset);
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                System.out.println(ColerUtil.RED+"Không có dữ liệu!");
            } else {
                do {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    double price = rs.getDouble("price");
                    int stock = rs.getInt("stock");
                    int category = rs.getInt("category_id");
                    String brand = rs.getString("brand");
                    String status = rs.getString("status");

                    System.out.println(ColerUtil.PURPLE + "╔════════════════════════════════════════════════════════════╗");
                    System.out.printf(ColerUtil.PURPLE + "║" +
                                    ColerUtil.BLUE + "%-5s | %-15s | %-10s | %-8s | %-10s" +
                                    ColerUtil.BLUE + "║\n",
                            "ID", "NAME", "PRICE", "STOCK", "STATUS");
                    System.out.println(ColerUtil.PURPLE + "╠════════════════════════════════════════════════════════════╣");
                    System.out.printf(ColerUtil.PURPLE + "║" +
                                    ColerUtil.PINK + "%-5d | %-15s | %-10.2f | %-8d | %-10s" +
                                    ColerUtil.PURPLE + "║\n",
                            id, name, price, stock, status);
                    System.out.println(ColerUtil.PURPLE + "╚════════════════════════════════════════════════════════════╝");
                } while (rs.next());
            }
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
    public static void searchProductByName(String name) {
        String sql = """
        SELECT * FROM products
        WHERE name LIKE ?
    """;

        try (Connection connection = DataConnection.openConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, "%" + name + "%");

            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                System.out.println(ColerUtil.RED+"Không tìm thấy sản phẩm!");
            } else {
                do {
                    int id = rs.getInt("id");
                    String productName = rs.getString("name");
                    double price = rs.getDouble("price");
                    int stock = rs.getInt("stock");
                    int category = rs.getInt("category_id");
                    String brand = rs.getString("brand");
                    String color=rs.getString("color");
                    int storage= rs.getInt("storage");
                    String description=rs.getString("description");
                    String status = rs.getString("status");

                    System.out.println(ColerUtil.PURPLE + "╔═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗");
                    System.out.printf(ColerUtil.PURPLE + "║" +
                                    ColerUtil.BLUE + " %-4s | %-15s | %-10s | %-6s | %-8s | %-10s | %-10s | %-7s | %-7s | %-15s " +
                                    ColerUtil.PURPLE + "║\n",
                            "ID", "NAME", "PRICE", "STOCK", "CATEGORY", "BRAND", "STATUS", "COLOR", "STORAGE", "DESCRIPTION");
                    System.out.println(ColerUtil.PURPLE + "╠═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╣");

                    // Dữ liệu sản phẩm
                    System.out.printf(ColerUtil.PURPLE + "║" +
                                    ColerUtil.PINK + " %-4d | %-15s | %-10.2f | %-6d | %-8d | %-10s | %-10s | %-7s | %-7d | %-15s " +
                                    ColerUtil.PURPLE + "║\n",
                            id, productName, price, stock, category, brand, status, color, storage, description);

                    // Đóng bảng
                    System.out.println(ColerUtil.PURPLE + "╚═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");

                } while (rs.next());
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void searchProductByBrand(String brandCheck) {
        String sql = """
        SELECT * FROM products
        WHERE brand LIKE ?
    """;

        try (Connection connection = DataConnection.openConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, "%" + brandCheck + "%");

            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                System.out.println(ColerUtil.RED+"Không tìm thấy sản phẩm!");
            } else {
                do {
                    int id = rs.getInt("id");
                    String productName = rs.getString("name");
                    double price = rs.getDouble("price");
                    int stock = rs.getInt("stock");
                    int category = rs.getInt("category_id");
                    String brand = rs.getString("brand");
                    String color=rs.getString("color");
                    int storage= rs.getInt("storage");
                    String description=rs.getString("description");
                    String status = rs.getString("status");

                    System.out.println(ColerUtil.PURPLE + "╔═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗");
                    System.out.printf(ColerUtil.PURPLE + "║" +
                                    ColerUtil.BLUE + " %-4s | %-15s | %-10s | %-6s | %-8s | %-10s | %-10s | %-7s | %-7s | %-15s " +
                                    ColerUtil.PURPLE + "║\n",
                            "ID", "NAME", "PRICE", "STOCK", "CATEGORY", "BRAND", "STATUS", "COLOR", "STORAGE", "DESCRIPTION");
                    System.out.println(ColerUtil.PURPLE + "╠═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╣");

                    // Dữ liệu sản phẩm
                    System.out.printf(ColerUtil.PURPLE + "║" +
                                    ColerUtil.PINK + " %-4d | %-15s | %-10.2f | %-6d | %-8d | %-10s | %-10s | %-7s | %-7d | %-15s " +
                                    ColerUtil.PURPLE + "║\n",
                            id, productName, price, stock, category, brand, status, color, storage, description);

                    // Đóng bảng
                    System.out.println(ColerUtil.PURPLE + "╚═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");

                } while (rs.next());
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void searchProductById(int idcheck) {
        String sql = """
        SELECT * FROM products WHERE id = ? ;
    """;

        try (Connection connection = DataConnection.openConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, idcheck);

            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                System.out.println(ColerUtil.RED+"Không tìm thấy sản phẩm!");
            } else {
                do {
                    int id = rs.getInt("id");
                    String productName = rs.getString("name");
                    double price = rs.getDouble("price");
                    int stock = rs.getInt("stock");
                    int category = rs.getInt("category_id");
                    String brand = rs.getString("brand");
                    String color=rs.getString("color");
                    int storage= rs.getInt("storage");
                    String description=rs.getString("description");
                    String status = rs.getString("status");

                    System.out.println(ColerUtil.PURPLE + "╔═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗");
                    System.out.printf(ColerUtil.PURPLE + "║" +
                                    ColerUtil.BLUE + " %-4s | %-15s | %-10s | %-6s | %-8s | %-10s | %-10s | %-7s | %-7s | %-15s " +
                                    ColerUtil.PURPLE + "║\n",
                            "ID", "NAME", "PRICE", "STOCK", "CATEGORY", "BRAND", "STATUS", "COLOR", "STORAGE", "DESCRIPTION");
                    System.out.println(ColerUtil.PURPLE + "╠═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╣");

                    // Dữ liệu sản phẩm
                    System.out.printf(ColerUtil.PURPLE + "║" +
                                    ColerUtil.PINK + " %-4d | %-15s | %-10.2f | %-6d | %-8d | %-10s | %-10s | %-7s | %-7d | %-15s " +
                                    ColerUtil.PURPLE + "║\n",
                            id, productName, price, stock, category, brand, status, color, storage, description);

                    // Đóng bảng
                    System.out.println(ColerUtil.PURPLE + "╚═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");

                } while (rs.next());
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static void searchProductByCategory(int categoryCheck) {
        String sql = """
        SELECT * FROM products WHERE category_id = ? ;
    """;

        try (Connection connection = DataConnection.openConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, categoryCheck);

            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                System.out.println(ColerUtil.RED+"Không tìm thấy sản phẩm!");
            } else {
                do {
                    int id = rs.getInt("id");
                    String productName = rs.getString("name");
                    double price = rs.getDouble("price");
                    int stock = rs.getInt("stock");
                    int category = rs.getInt("category_id");
                    String brand = rs.getString("brand");
                    String color=rs.getString("color");
                    int storage= rs.getInt("storage");
                    String description=rs.getString("description");
                    String status = rs.getString("status");

                    System.out.println(ColerUtil.PURPLE + "╔═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗");
                    System.out.printf(ColerUtil.PURPLE + "║" +
                                    ColerUtil.BLUE + " %-4s | %-15s | %-10s | %-6s | %-8s | %-10s | %-10s | %-7s | %-7s | %-15s " +
                                    ColerUtil.PURPLE + "║\n",
                            "ID", "NAME", "PRICE", "STOCK", "CATEGORY", "BRAND", "STATUS", "COLOR", "STORAGE", "DESCRIPTION");
                    System.out.println(ColerUtil.PURPLE + "╠═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╣");

                    // Dữ liệu sản phẩm
                    System.out.printf(ColerUtil.PURPLE + "║" +
                                    ColerUtil.PINK + " %-4d | %-15s | %-10.2f | %-6d | %-8d | %-10s | %-10s | %-7s | %-7d | %-15s " +
                                    ColerUtil.PURPLE + "║\n",
                            id, productName, price, stock, category, brand, status, color, storage, description);

                    // Đóng bảng
                    System.out.println(ColerUtil.PURPLE + "╚═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");

                } while (rs.next());
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteProduct(int id) {
        String sql = "DELETE FROM products WHERE id = ?";

        try (Connection connection = DataConnection.openConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if (rows == 0) {
                System.out.println(ColerUtil.RED+"Không tìm thấy sản phẩm!");
            } else {
                System.out.println(ColerUtil.GREEN+"Xóa thành công!");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void UpdateProduct(Product product,int idUpdate) {
        String sql = """
                 UPDATE products
                        SET name = ?, price = ?, stock = ?, category_id = ?, brand = ?, status = ?, storage = ?, color = ?, description = ?
                        WHERE id = ?
                """;
        try (Connection connection = DataConnection.openConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, product.getName());
            ps.setDouble(2, product.getPrice());
            ps.setInt(3, product.getStock());
            ps.setInt(4, product.getCategory_id());
            ps.setString(5, product.getBrand());
            ps.setString(6, product.getStatus());
            ps.setInt(7, product.getStorage());
            ps.setString(8, product.getColor());
            ps.setString(9, product.getDescription());
            ps.setInt(10,idUpdate);

            int rows = ps.executeUpdate();

            if (rows == 0) {
                System.out.println(ColerUtil.RED+"Cập nhật thất bại (không tìm thấy id)!");
            } else {
                System.out.println(ColerUtil.GREEN+"Cập nhật thành công!");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean updateStock(Connection conn, int productId, int quantity) {

        String sql = "UPDATE products SET stock = stock - ? WHERE id = ? AND stock >= ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, quantity);
            ps.setInt(2, productId);
            ps.setInt(3, quantity);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Lỗi update stock: " + e.getMessage());
            return false;
        }
    }

    //method ở phầm customer
    public static boolean checkProductValid(int id) {
        String sql = """
        SELECT * FROM products 
        WHERE id = ? AND status = 'IN_STOCK' AND stock > 0
    """;

        try (Connection conn = DataConnection.openConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static int getStockById(int id) {
        String sql = "SELECT stock FROM products WHERE id = ?";

        try (Connection conn = DataConnection.openConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("stock");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return 0;
    }




}
