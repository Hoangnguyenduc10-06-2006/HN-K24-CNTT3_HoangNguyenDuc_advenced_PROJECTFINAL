package service;

import dao.CartDAO;
import dao.OrderDAO;
import dao.OrderDetailsDAO;
import dao.ProductDAO;
import model.Cart;
import utill.ColerUtil;
import utill.DataConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class OrderService {
    //1 lấy cart
    //2 kiểm tra số lượng
    //3 tính giá tiền
    //4. tạo order
    //5,tạo orderdetal và trư số lươgj
    //6.xóa cart
   public static void createOrder(int user_id){
       Connection connection = null;

       try {
           connection= DataConnection.openConnection();
           connection.setAutoCommit(false);
           List<Cart> cartList = CartDAO.getCartByUserId(user_id);

           if (cartList.isEmpty()){
               System.out.println(ColerUtil.RED+"danh sách giỏ hàng của bạn đang trống");
               connection.rollback();
               return;
           }
            // tính total tiền

           double total=0.0;
           for(Cart cart: cartList){
               int stockItem = ProductDAO.getStockById(cart.getProduct_id());

               if(cart.getQuantity()>stockItem){
                   System.out.println(ColerUtil.RED+"sản phẩm ko đủ hàng!!");
                   connection.rollback();
                   return;
               }
               total+=cart.getQuantity() *ProductDAO.getPriceById(cart.getProduct_id());
           }

           int insertOrderId = OrderDAO.insertOrder(connection,user_id,total);

           if(insertOrderId ==-1){
               System.out.println(ColerUtil.RED+"tạo đơn hàng thất bại!!!");
               connection.rollback();
               return;
           }

           for(Cart cart : cartList){

                boolean createOrderDetail= OrderDetailsDAO.insertOrderDetail(connection, insertOrderId,cart.getProduct_id(),cart.getQuantity(),ProductDAO.getPriceById(cart.getProduct_id()));

                if(!createOrderDetail){
                    System.out.println(ColerUtil.RED+"Thêm vào orderDetail thất bại!!!");
                    connection.rollback();
                    return;
                }
                boolean updateStockProduct =ProductDAO.updateStock(connection,cart.getProduct_id(),cart.getQuantity());
                if(!updateStockProduct){
                    System.out.println(ColerUtil.RED+"cập nhật số lượng không thành công");
                    connection.rollback();
                    return;
                }
                // xoa giỏ hàng vì tạo đơn hàng là lấy ơt dỏ hàn ra
               for (Cart item : cartList) {
                   CartDAO.clearCartByUserId(connection,user_id);
               }
               connection.commit();
               System.out.println(ColerUtil.GREEN+"Đặt hàng thành công. Bạn có thể xem trạng thái đơn hàng");
           }

       } catch (SQLException e) {try {
           if (connection != null) connection.rollback();
       } catch (SQLException ex) {
           System.out.println(ColerUtil.RED+"Rollback lỗi!");
       }
           System.out.println(ColerUtil.RED+"Có lỗi xảy ra: " + e.getMessage());
       }
   }

    public static void showStatusOrder(int userId){
        System.out.println(ColerUtil.PURPLE+"═══════════════════ "+ColerUtil.BLUE+"DANH SACH TRẠNG THÁI ĐƠN HÀNG "+ColerUtil.PURPLE+"═════════════════════");
            OrderDAO.selectStatusOrder(userId);
    }
    public static void showHistory(int user_id){
        System.out.println(ColerUtil.PURPLE+"═════════════════════════════ "+ColerUtil.BLUE+"LỊCH SỬ MUA HÀNG "+ColerUtil.PURPLE+"═════════════════════════════════════");
       OrderDAO.selectHistoryOrder(user_id);
    }

    public static void updateOrder(Scanner sc) {

        System.out.println(ColerUtil.PURPLE+"Nhập mã đơn:");
        int orderId = Integer.parseInt(sc.nextLine());

        System.out.println(ColerUtil.BLUE+"Nhập trạng thái ("+ColerUtil.YELLOW+"SHIPPING,"+ColerUtil.GREEN+" COMPLETED,"+ColerUtil.ORANGE+" CANCELLED):");
        String status = sc.nextLine().toUpperCase();

        if (!status.matches("SHIPPING|COMPLETED|CANCELLED")) {
            System.out.println("Trạng thái không hợp lệ!");
            return;
        }

        OrderDAO.updateOrderStatus(orderId, status);
    }

    public static void showBestSeller(){
        int month = LocalDate.now().getMonthValue();
        int year = LocalDate.now().getYear();
        System.out.println(ColerUtil.PURPLE+"═══════════════════ "+ColerUtil.BLUE+"DANH SÁCH TOP 5 SẢN PHẨM BÁN CHẠY TRONG THÁNG "+ month+"/"+year+ColerUtil.PURPLE+" ═══════════════════════════ ");
        OrderDAO.selectBestSeller();

    }
}
