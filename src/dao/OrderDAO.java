package dao;

import utill.ColerUtil;
import utill.DataConnection;

import java.sql.*;

public class OrderDAO {
    public static int insertOrder(Connection conn, int userId, double totalAmount) {

        String sql = """
                    INSERT INTO orders(user_id, total_amount, status)
                    VALUES (?, ?, 'SHIPPING')
                """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setDouble(2, totalAmount);

            int rows = ps.executeUpdate();
            if (rows == 0) return -1;

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT LAST_INSERT_ID()");

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return -1;
    }

    public static void selectStatusOrder(int user_id) {
        String statusColor;
        String sql = """
                select * from orders WHERE user_id = ? ORDER BY created_at DESC
                """;

        try(Connection connection = DataConnection.openConnection()){
            PreparedStatement preparedStatement =connection.prepareStatement(sql);
            preparedStatement.setInt(1,user_id);
            ResultSet resultSet =preparedStatement.executeQuery();
            if(resultSet.next()){
                int id = resultSet.getInt("id");
                double total = resultSet.getDouble("total_amount");
                String status = resultSet.getString("status");
                String date = resultSet.getString("created_at");



                System.out.println(ColerUtil.PURPLE + "╔══════════════════════════════════════════════════════════════════════╗");

                System.out.printf("║"+ColerUtil.BLUE + " %-8s | %-13s | %-13s | %-25s" +ColerUtil.PURPLE+ " ║\n",
                        "Mã đơn", "Tổng tiền", "Trạng thái", "Ngày tạo");

                System.out.println(ColerUtil.PURPLE + "║══════════════════════════════════════════════════════════════════════║");

                switch (status) {
                    case "SHIPPING":
                        statusColor = ColerUtil.YELLOW;
                        break;
                    case "COMPLETED":
                        statusColor = ColerUtil.GREEN;
                        break;
                    case "CANCELLED":
                        statusColor = ColerUtil.ORANGE;
                        break;
                    default:
                        statusColor = ColerUtil.BLUE;
                }

                System.out.printf("║"+ColerUtil.PINK + " %-8d | %-13.2f | " + statusColor + "%-13s" + ColerUtil.PINK + " | %-25s "+ColerUtil.PURPLE+"║\n",
                        id,
                        total,
                        status,
                        date);
                System.out.println(ColerUtil.PURPLE + "╚══════════════════════════════════════════════════════════════════════╝");

            }
        }catch (SQLException e){
            System.out.println(ColerUtil.RED+e);
        }
    }

    public static void selectHistoryOrder(int user_Id){


            String sql = """
        SELECT 
            o.id AS order_id,
            o.total_amount,
            o.status,
            o.created_at,
            p.name,
            od.quantity,
            od.price
        FROM orders o
        JOIN orderdetails od ON o.id = od.order_id
        JOIN products p ON od.product_id = p.id
        WHERE o.user_id = ?
        ORDER BY o.id DESC
    """;

            try (Connection conn = DataConnection.openConnection()) {

                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, user_Id);

                ResultSet rs = ps.executeQuery();

                int currentOrderId = -1;

                while (rs.next()) {

                    int orderId = rs.getInt("order_id");

                    if (orderId != currentOrderId) {

                        currentOrderId = orderId;

                        System.out.println(ColerUtil.PURPLE + "════════════════════════════════════════════════════════════════════════════════════");
                        System.out.println("Mã đơn: " + orderId +
                                " | Tổng tiền: " + rs.getDouble("total_amount") +
                                " | Trạng thái: " + rs.getString("status") +
                                " | Ngày: " + rs.getString("created_at"));
                        System.out.println(ColerUtil.PURPLE + "════════════════════════════════════════════════════════════════════════════════════");
                        System.out.println("------------------------------- SẢN PHẨM -------------------------------------------");
                    }

                    System.out.printf(
                            "Tên: %-22s | số lượng: %-20s | giá: %-20f" ,rs.getString("name"),rs.getInt("quantity"),rs.getDouble("price")
                    );
                    System.out.println(ColerUtil.PURPLE + "\n════════════════════════════════════════════════════════════════════════════════════");
                }

            } catch (SQLException e) {
                System.out.println("Lỗi hiển thị lịch sử!");
            }
        }

    public static void getAllOrders() {

        String sql = """
        SELECT 
            o.id AS order_id,
            u.name AS user_name,
            o.total_amount,
            o.status,
            o.created_at
        FROM orders o
        JOIN users u ON o.user_id = u.id
        ORDER BY o.created_at DESC
    """;

        try (Connection conn = DataConnection.openConnection()) {

            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                System.out.println("Không có đơn hàng nào!");
            } else {

                System.out.println(ColerUtil.PURPLE + "╔════════════════════════════════════════════════════════════════════════════════════╗");

                System.out.printf(ColerUtil.BLUE + "║ %-6s | %-20s | %-12s | %-12s | %-20s ║\n",
                        "ID", "Khách hàng", "Tổng tiền", "Trạng thái", "Ngày tạo");

                System.out.println(ColerUtil.PURPLE + "╠════════════════════════════════════════════════════════════════════════════════════╣");

                do {
                    System.out.printf(ColerUtil.BLUE + "║ %-6d | %-20s | %-12.2f | %-12s | %-20s ║\n",
                            rs.getInt("order_id"),
                            rs.getString("user_name"),
                            rs.getDouble("total_amount"),
                            rs.getString("status"),
                            rs.getString("created_at"));

                } while (rs.next());

                System.out.println(ColerUtil.PURPLE + "╚════════════════════════════════════════════════════════════════════════════════════╝");
            }

        } catch (SQLException e) {
            System.out.println("Lỗi hiển thị đơn hàng!");
        }
    }
    public static void updateOrderStatus(int orderId, String status) {

        String sql = "UPDATE orders SET status = ? WHERE id = ?";

        try (Connection conn = DataConnection.openConnection()) {

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, status);
            ps.setInt(2, orderId);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println(ColerUtil.GREEN+"Cập nhật trạng thái thành công!");
            } else {
                System.out.println(ColerUtil.RED+"Không tìm thấy đơn hàng!");
            }

        } catch (SQLException e) {
            System.out.println("Lỗi cập nhật trạng thái!");
        }
    }
}
