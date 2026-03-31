package presentation;

import service.AuthService;
import service.CustomerService;
import utill.ColerUtil;

import java.util.Scanner;

public class MenuCustomerINFORMATION {
    public static void main(String emailUserLogging,String passwordUserLogging ){
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println(ColerUtil.PURPLE + "╔═════════════════════════════════╗");
            System.out.println(ColerUtil.PURPLE + "║" + ColerUtil.BLUE + "  MENU QUẢN LÝ THÔNG TIN CÁ NHÂN " + ColerUtil.PURPLE + "║");
            System.out.println(ColerUtil.PURPLE + "╠═════════════════════════════════╣");

            System.out.println(ColerUtil.PURPLE + "║ " + ColerUtil.BLUE + "1. Xem thông tin cá nhân        " + ColerUtil.PURPLE + "║");
            System.out.println(ColerUtil.PURPLE + "║ " + ColerUtil.BLUE + "2. Sửa thông tin cá nhân        " + ColerUtil.PURPLE + "║");
            System.out.println(ColerUtil.PURPLE + "║ " + ColerUtil.BLUE + "3. Quay lại MENU CUSTOMER       " + ColerUtil.PURPLE + "║");

            System.out.println(ColerUtil.PURPLE + "╚═════════════════════════════════╝");

            System.out.print(ColerUtil.YELLOW + "Lựa chọn của bạn: ");
            choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    CustomerService.selectINformationCustomer(emailUserLogging ,passwordUserLogging);
                    break;
                case 2:
                    CustomerService.UpdateINformationCustomer(emailUserLogging ,passwordUserLogging,sc);
                    break;
                case 3:
                    break;
                default:
                    System.out.println("lựa chọn không hợp lệ");
            }
        } while (choice != 3);
    }


}
