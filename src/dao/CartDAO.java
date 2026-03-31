package dao;

import model.Cart;
import utill.ColerUtil;
import utill.DataConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartDAO {
    public static void addToCart(int userId, int productId, int quantity) {

        String checkSql = """
        SELECT quantity FROM cart
        WHERE user_id = ? AND product_id = ?
    """;

        String insertSql = """
        INSERT INTO cart(user_id, product_id, quantity)
        VALUES (?, ?, ?)
    """;

        String updateSql = """
        UPDATE cart
        SET quantity = quantity + ?
        WHERE user_id = ? AND product_id = ?
    """;

        try (Connection conn = DataConnection.openConnection()) {

            // ===== 1. CHECK SẢN PHẨM ĐÃ CÓ TRONG CART =====
            PreparedStatement psCheck = conn.prepareStatement(checkSql);
            psCheck.setInt(1, userId);
            psCheck.setInt(2, productId);

            ResultSet rs = psCheck.executeQuery();

            if (rs.next()) {
                // ===== 2. ĐÃ CÓ → UPDATE =====
                int currentQuantity = rs.getInt("quantity");
                int newQuantity = currentQuantity + quantity;

                //  check lại stock trước khi update
                int stock = ProductDAO.getStockById(productId);

                if (newQuantity > stock) {
                    System.out.println(ColerUtil.RED+"Tổng số lượng vượt quá tồn kho! (Kho còn: " + stock + ")");

                }else {
                    PreparedStatement psUpdate = conn.prepareStatement(updateSql);
                    psUpdate.setInt(1, quantity);
                    psUpdate.setInt(2, userId);
                    psUpdate.setInt(3, productId);

                    psUpdate.executeUpdate();

                    System.out.println(ColerUtil.GREEN+"Đã cập nhật số lượng trong giỏ!");
                }

            } else {
                // ===== 3. CHƯA CÓ → INSERT =====
                PreparedStatement psInsert = conn.prepareStatement(insertSql);
                psInsert.setInt(1, userId);
                psInsert.setInt(2, productId);
                psInsert.setInt(3, quantity);

                psInsert.executeUpdate();

                System.out.println(ColerUtil.GREEN+"Đã thêm vào giỏ hàng!");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void selectAllCart(int userId) {

        String sql = """
        SELECT 
            c.id,
            p.name,
            p.price,
            c.quantity,
            (p.price * c.quantity) AS total_price
        FROM cart c
        JOIN products p ON c.product_id = p.id
        WHERE c.user_id = ?
    """;

        try (Connection conn = DataConnection.openConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            double totalAll = 0;

            if (!rs.next()) {
                System.out.println(ColerUtil.RED+"Giỏ hàng trống!");
            } else {
                do {
                    int cartId = rs.getInt("id");
                    String name = rs.getString("name");
                    double price = rs.getDouble("price");
                    int quantity = rs.getInt("quantity");
                    double total = rs.getDouble("total_price");

                    totalAll += total;

                    // Tiêu đề bảng
                    System.out.println(ColerUtil.PURPLE + "╔══════════════════════════════════════════════════════════════════════════════════════════════════════════════╗");
                    System.out.printf(ColerUtil.PURPLE + "║" +
                                    ColerUtil.BLUE + " %-15s | %-30s | %-20.3s | %-12s | %-20s" +
                                    ColerUtil.PURPLE + "║\n",
                            "Mã giỏ hàng", "Tên sản phẩm", "giá", "Sô lượng", "Thành tiền");
                    System.out.println(ColerUtil.PURPLE + "╠══════════════════════════════════════════════════════════════════════════════════════════════════════════════╣");

// Nội dung
                    System.out.printf(ColerUtil.PURPLE + "║" +
                                    ColerUtil.PINK + " %-15d | %-30s | %-20.2f | %-12d | %-19.2f " +
                                    ColerUtil.PURPLE + "║\n",
                            cartId, name, price, quantity, total);

// Kết thúc bảng
                    System.out.println(ColerUtil.PURPLE + "╚══════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");

                } while (rs.next());

                System.out.println(ColerUtil.PURPLE+"-----------------------------------");
                System.out.println(ColerUtil.GREEN+"TỔNG TIỀN: " + totalAll);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean checkCartExist(int userId, int cartId) {
        String sql = """
        SELECT * FROM cart 
        WHERE id = ? AND user_id = ?
    """;

        try (Connection conn = DataConnection.openConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, cartId);
            ps.setInt(2, userId);

            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int getProductIdByCart(int cartId) {
        String sql = "SELECT product_id FROM cart WHERE id = ?";

        try (Connection conn = DataConnection.openConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, cartId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("product_id");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return -1;
    }


    public static void updateCartById(int cartId, int quantity) {
        String sql = """
        UPDATE cart 
        SET quantity = ?
        WHERE id = ?
    """;

        try (Connection conn = DataConnection.openConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, quantity);
            ps.setInt(2, cartId);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println(ColerUtil.GREEN+"Cập nhật thành công!");
            } else {
                System.out.println(ColerUtil.RED+"Cập nhật thất bại!");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteCart(int userId, int cartId) {
        String sql = "DELETE FROM cart WHERE id = ? AND user_id = ?";

        try (Connection conn = DataConnection.openConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, cartId);
            ps.setInt(2, userId);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println(ColerUtil.GREEN+"Xóa thành công khỏi giỏ hàng!");
            } else {
                System.out.println(ColerUtil.RED+"Không tìm thấy hoặc không có quyền xóa!");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void clearCartByUserId(Connection conn, int userId) {

        String sql = "DELETE FROM cart WHERE user_id = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            int rows =ps.executeUpdate();
            if (rows >0){
                System.out.println(ColerUtil.GREEN+"xóa thành công khỏi giỏ hàng");
            }else {
                System.out.println(ColerUtil.RED+"xóa thất bại ở  giỏ hàng");
            }
        } catch (SQLException e) {
            System.out.println(ColerUtil.RED+"Lỗi xóa cart");
        }
    }

    public static List<Cart> getCartByUserId(int userId) {
        List<Cart> list = new ArrayList<>();

        String sql = "SELECT id, user_id, product_id, quantity FROM Cart WHERE user_id = ?";

        try (Connection conn = DataConnection.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Cart cart = new Cart();

                cart.setId(rs.getInt("id"));
                cart.setUser_id(rs.getInt("user_id"));
                cart.setProduct_id(rs.getInt("product_id"));
                cart.setQuantity(rs.getInt("quantity"));

                list.add(cart);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }
}
