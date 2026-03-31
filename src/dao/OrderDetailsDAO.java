package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderDetailsDAO {
    public static boolean insertOrderDetail(Connection conn, int orderId, int productId, int quantity, double price) {

        String sql = """
            INSERT INTO orderdetails(order_id, product_id, quantity, price)
            VALUES (?, ?, ?, ?)
        """;

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, orderId);
            ps.setInt(2, productId);
            ps.setInt(3, quantity);
            ps.setDouble(4, price);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Lỗi order detail: " + e.getMessage());
            return false;
        }
    }
}
