package presentation;

import dao.CartDAO;
import service.CartService;
import utill.ColerUtil;


import java.util.Scanner;

public class MenuCart {
    public static void main(int user_id) {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println(ColerUtil.PURPLE + "╔═══════════════════════════════════╗");
            System.out.println(ColerUtil.PURPLE + "║" + ColerUtil.BLUE + "        MENU QUẢN LÝ GIỎ HÀNG      " + ColerUtil.PURPLE + "║");
            System.out.println(ColerUtil.PURPLE + "╠═══════════════════════════════════╣");

            System.out.println(ColerUtil.PURPLE + "║ " + ColerUtil.BLUE + "1. Thêm sản phẩm vào giỏ hàng     " + ColerUtil.PURPLE + "║");
            System.out.println(ColerUtil.PURPLE + "║ " + ColerUtil.BLUE + "2. Hiển thị giỏ hàng              " + ColerUtil.PURPLE + "║");
            System.out.println(ColerUtil.PURPLE + "║ " + ColerUtil.BLUE + "3. Sửa số lượng sản phẩm          " + ColerUtil.PURPLE + "║");
            System.out.println(ColerUtil.PURPLE + "║ " + ColerUtil.BLUE + "4. Xóa sản phẩm khỏi giỏ hàng     " + ColerUtil.PURPLE + "║");
            System.out.println(ColerUtil.PURPLE + "║ " + ColerUtil.BLUE + "5. Quay lại MENU CUSTOMER         " + ColerUtil.PURPLE + "║");

            System.out.println(ColerUtil.PURPLE + "╚═══════════════════════════════════╝");

            System.out.print(ColerUtil.YELLOW + "Lựa chọn của bạn: ");
            choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    CartService.insertCart(user_id,sc);
                    break;
                case 2:
                    CartService.showCart(user_id);
                    break;
                case 3:
                   CartService.updateStockInCart(user_id,sc);
                    break;
                case 4:
                    CartService.deleteCartById(user_id,sc);
                    break;
                case 5:
                    break;
                default:
                    System.out.println("lựa chọn không hợp lệ");
            }
        } while (choice != 5);
    }


}
