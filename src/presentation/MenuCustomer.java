package presentation;

import service.OrderService;
import utill.ColerUtil;

import java.util.Scanner;

public class MenuCustomer {
    public static void main(String emailUserLogging ,String passwordUserLogging,int user_id) {
        Scanner sc =new Scanner(System.in);
        int choice ;
        do {
            System.out.println(ColerUtil.PURPLE + "╔═══════════════════════════════════╗");
            System.out.println(ColerUtil.PURPLE + "║" + ColerUtil.BLUE + "           MENU CUSTOMER           " + ColerUtil.PURPLE + "║");
            System.out.println(ColerUtil.PURPLE + "╠═══════════════════════════════════╣");

            System.out.println(ColerUtil.PURPLE + "║ " + ColerUtil.BLUE + "1. Quản lý thông tin cá nhân      " + ColerUtil.PURPLE + "║");
            System.out.println(ColerUtil.PURPLE + "║ " + ColerUtil.BLUE + "2. Xem danh sách sản phẩm         " + ColerUtil.PURPLE + "║");
            System.out.println(ColerUtil.PURPLE + "║ " + ColerUtil.BLUE + "3. Quản lý giỏ hàng               " + ColerUtil.PURPLE + "║");
            System.out.println(ColerUtil.PURPLE + "║ " + ColerUtil.BLUE + "4. Đặt sản phẩm                   " + ColerUtil.PURPLE + "║");
            System.out.println(ColerUtil.PURPLE + "║ " + ColerUtil.BLUE + "5. Xem trạng thái đơn hàng        " + ColerUtil.PURPLE + "║");
            System.out.println(ColerUtil.PURPLE + "║ " + ColerUtil.BLUE + "6. Xem lịch sử đặt hàng           " + ColerUtil.PURPLE + "║");
            System.out.println(ColerUtil.PURPLE + "║ " + ColerUtil.BLUE + "7. Quay lại menu đăng nhập        " + ColerUtil.PURPLE + "║");

            System.out.println(ColerUtil.PURPLE + "╚═══════════════════════════════════╝");

            System.out.print(ColerUtil.YELLOW + "Lựa chọn của bạn: ");
            choice=Integer.parseInt(sc.nextLine());
            switch (choice){
                case 1:
                    MenuCustomerINFORMATION.main(emailUserLogging,passwordUserLogging);
                    break;
                case 2:
                    MenuCustomerSelect.main();
                    break;
                case 3:
                    MenuCart.main(user_id);
                    break;
                case 4:
                    OrderService.createOrder(user_id);
                    break;
                case 5:
                    OrderService.showStatusOrder(user_id);
                    break;
                case 6:
                    OrderService.showHistory(user_id);
                    break;
                case 7:
                    break;
                default:
                    System.out.println("lựa chọn không hợp lệ");
                    break;
            }
        }while (choice != 7);

    }


}
