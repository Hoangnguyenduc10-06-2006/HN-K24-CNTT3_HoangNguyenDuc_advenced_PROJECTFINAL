package service;

import dao.CartDAO;
import dao.ProductDAO;
import utill.ColerUtil;

import java.util.Scanner;

public class CartService {
    public static void insertCart(int user_id, Scanner sc) {

        int productId = 0;
        int quantityInput = 0;

        boolean checkProduct;
        boolean checkQuantity;

        do {
            checkProduct = true;
            System.out.println(ColerUtil.BLUE+"Mời bạn nhập mã sản phẩm muốn thêm vào giỏ hàng:");
            String product_idInput = sc.nextLine();

            if (product_idInput.trim().isEmpty()) {
                System.out.println(ColerUtil.RED+"Không được bỏ trống!");
            } else {
                try {
                    productId = Integer.parseInt(product_idInput);


                    if (!ProductDAO.checkProductValid(productId)) {
                        System.out.println(ColerUtil.RED+"Sản phẩm không tồn tại hoặc tạm thời cửa hàng chưa có mặt hàng này!");
                    } else {
                        checkProduct = false;
                    }

                } catch (NumberFormatException e) {
                    System.out.println(ColerUtil.RED+"Chỉ được nhập số!");
                }
            }

        } while (checkProduct);


        do {
            checkQuantity = true;
            System.out.println(ColerUtil.BLUE+"Mời bạn nhập số lượng:");

            String quantity = sc.nextLine();

            if (quantity.trim().isEmpty()) {
                System.out.println(ColerUtil.RED+"Không được bỏ trống!");
            } else if (!quantity.matches("^\\d+$")) {
                System.out.println(ColerUtil.RED+"Chỉ được nhập số nguyên dương!");
            } else {
                quantityInput = Integer.parseInt(quantity);

                if (quantityInput <= 0) {
                    System.out.println(ColerUtil.RED+"Số lượng phải > 0!");
                } else {

                    int stock = ProductDAO.getStockById(productId);

                    if (quantityInput > stock) {
                        System.out.println(ColerUtil.RED+"Số lượng vượt quá tồn kho! (Kho còn: " + stock + ")");
                    } else {
                        checkQuantity = false;
                    }
                }
            }

        } while (checkQuantity);

        // ====== THÊM VÀO CART ======
        CartDAO.addToCart(user_id, productId, quantityInput);
    }
    public static void showCart(int user_id){
        System.out.println(ColerUtil.PURPLE+"========= GIỎ HÀNG CỦA BẠN ==================");
        CartDAO.selectAllCart(user_id);
    };

    public static void updateStockInCart(int user_id, Scanner sc) {

        int cartId = 0;
        int newQuantity = 0;

        boolean checkCart;
        boolean checkQuantity;

        // ====== NHẬP CART ID ======
        do {
            checkCart = true;
            System.out.println(ColerUtil.BLUE+"Mời bạn nhập mã giỏ hàng :");

            String input = sc.nextLine();

            if (input.trim().isEmpty()) {
                System.out.println(ColerUtil.RED+"Không được bỏ trống!");
            } else {
                try {
                    cartId = Integer.parseInt(input);

                    //  check cart có tồn tại không (thuộc user luôn)
                    if (!CartDAO.checkCartExist(user_id, cartId)) {
                        System.out.println(ColerUtil.RED+"Mã giỏ hàng không tồn tại!");
                    } else {
                        checkCart = false;
                    }

                } catch (NumberFormatException e) {
                    System.out.println(ColerUtil.RED+"Chỉ được nhập số!");
                }
            }

        } while (checkCart);

        // ====== NHẬP SỐ LƯỢNG ======
        do {
            checkQuantity = true;
            System.out.println(ColerUtil.BLUE+"Mời bạn nhập số lượng mới:");

            String input = sc.nextLine();

            if (input.trim().isEmpty()) {
                System.out.println(ColerUtil.RED+"Không được bỏ trống!");
            } else if (!input.matches("^\\d+$")) {
                System.out.println(ColerUtil.RED+"Chỉ được nhập số nguyên dương!");
            } else {
                newQuantity = Integer.parseInt(input);

                if (newQuantity <= 0) {
                    System.out.println(ColerUtil.RED+"Số lượng phải > 0!");
                } else {

                    //  lấy product_id từ cart
                    int productId = CartDAO.getProductIdByCart(cartId);

                    //  check stock
                    int stock = ProductDAO.getStockById(productId);

                    if (newQuantity > stock) {
                        System.out.println(ColerUtil.RED+"Vượt quá tồn kho! (Kho còn: " + stock + ")");
                    } else {
                        checkQuantity = false;
                    }
                }
            }

        } while (checkQuantity);

        // ====== UPDATE ======
        CartDAO.updateCartById(cartId, newQuantity);
    }
    public static void deleteCartById(int user_id, Scanner sc) {

        int cartId = 0;
        boolean checkDelete;

        do {
            checkDelete = true;
            System.out.println(ColerUtil.BLUE+"Mời bạn nhập mã giỏ hàng cần xóa:");

            String idCartDelete = sc.nextLine();

            if (idCartDelete.trim().isEmpty()) {
                System.out.println(ColerUtil.RED+"Không được bỏ trống!");
            } else if (!idCartDelete.matches("^\\d+$")) {
                System.out.println(ColerUtil.RED+"Chỉ được nhập số!");
            } else {
                cartId = Integer.parseInt(idCartDelete);

                // 👉 check tồn tại + đúng user
                if (!CartDAO.checkCartExist(user_id, cartId)) {
                    System.out.println(ColerUtil.RED+"Mã giỏ hàng không tồn tại!");
                } else {
                    checkDelete = false;
                }
            }

        } while (checkDelete);

        System.out.println(ColerUtil.BLUE+"bạn có muốn xóa giỏ hàng này không(y/n): ");

        String confirm =sc.nextLine().toLowerCase();
        boolean breakLoop;
       do {
           breakLoop=true;
           switch (confirm){
               case "y":
                   CartDAO.deleteCart(user_id,cartId);
                   breakLoop=false;
                   break;
               case "n":
                   System.out.println(ColerUtil.GREEN+"hủy xóa thành công!!!");
                   breakLoop=false;
                   break;
               default:
                   System.out.println(ColerUtil.RED+"lựa chọn của bạn không hợp lệ!!!");
           }
       }while (breakLoop);
    }
 }
