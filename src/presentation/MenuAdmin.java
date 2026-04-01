package presentation;

import service.OrderService;
import service.ProductService;
import utill.ColerUtil;

import java.util.Scanner;

public class MenuAdmin {
    public static void main() {
        Scanner sc =new Scanner(System.in);
        int choice ;
        do {
            System.out.println(ColerUtil.PURPLE + "╔══════════════════════════════════╗");
            System.out.println(ColerUtil.PURPLE + "║" + ColerUtil.BLUE + "             MENU ADMIN           " + ColerUtil.PURPLE + "║");
            System.out.println(ColerUtil.PURPLE + "╠══════════════════════════════════╣");

            System.out.println(ColerUtil.PURPLE + "║ " + ColerUtil.BLUE + "1. Quản lý danh mục              " + ColerUtil.PURPLE + "║");
            System.out.println(ColerUtil.PURPLE + "║ " + ColerUtil.BLUE + "2. Quản lý sản phẩm              " + ColerUtil.PURPLE + "║");
            System.out.println(ColerUtil.PURPLE + "║ " + ColerUtil.BLUE + "3. Quản lý đơn hàng              " + ColerUtil.PURPLE + "║");
            System.out.println(ColerUtil.PURPLE + "║ " + ColerUtil.BLUE + "4. Quản lý khách hàng            " + ColerUtil.PURPLE + "║");
            System.out.println(ColerUtil.PURPLE + "║ " + ColerUtil.BLUE + "5. Báo cáo 5 sản phẩm bán chạy   " + ColerUtil.PURPLE + "║");
            System.out.println(ColerUtil.PURPLE + "║ " + ColerUtil.BLUE + "6. Quay lại menu đăng nhập       " + ColerUtil.PURPLE + "║");

            System.out.println(ColerUtil.PURPLE + "╚══════════════════════════════════╝");

            System.out.print(ColerUtil.YELLOW + "Lựa chọn của bạn: ");
            choice=Integer.parseInt(sc.nextLine());
            switch (choice){
                case 1:
                    MenuCategory.main(sc);
                    break;
                case 2:
                    MenuProduct.main(sc);
                    break;
                case 3:
                    MenuOrder.main();
                    break;
                case 4:
                    MenuManagerCustomer.main();
                    break;
                case 5:
                    OrderService.showBestSeller();
                    break;
                case 6:
                    break;
                default:
                    System.out.println("lựa chọn không hợp lệ");
            }
        }while (choice != 6);
    }

}
