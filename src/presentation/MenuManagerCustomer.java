package presentation;

import service.CustomerService;

import utill.ColerUtil;

import java.util.Scanner;

public class MenuManagerCustomer {


    public static void main() {
        Scanner sc = new Scanner(System.in);
        int choice;
        boolean end;

        do {
            end=true;
            try {
                do {
                    System.out.println(ColerUtil.PURPLE + "╔═══════════════════════════════════════╗");
                    System.out.println("║  "+ColerUtil.BLUE+"        QUẢN LÝ KHÁCH HÀNG  " +ColerUtil.PURPLE+"         ║");
                    System.out.println("╠═══════════════════════════════════════╣");
                    System.out.println("║ "+ColerUtil.BLUE+"1. Hiển thị toàn bộ đơn của KHÁCH HÀNG"+ColerUtil.PURPLE+"║");
                    System.out.println("║ "+ColerUtil.BLUE+"2. cập nhật trạng thái KHÁCH HÀNG "+ColerUtil.PURPLE+"    ║");
                    System.out.println("║ "+ColerUtil.BLUE+"3. Thoát   "+ColerUtil.PURPLE+"                           ║");
                    System.out.println("╚═══════════════════════════════════════╝" );
                    System.out.print(ColerUtil.YELLOW + " Lựa chọn của bạn: " );
                    choice = Integer.parseInt(sc.nextLine());

                    switch (choice) {
                        case 1:
                            CustomerService.selectAllCustomer();
                            break;
                        case 2:
                            CustomerService.updateStatusCustomer(sc);
                            break;
                        case 3:
                            System.out.println(ColerUtil.GREEN+"thoát chương trình thành công");
                            break;
                        default:
                            System.out.println(ColerUtil.RED+"lựa chọn không hợp lệ");
                    }
                } while (choice != 3);
                end= false;
            }catch (NumberFormatException e) {
                System.out.println(ColerUtil.RED + "phải nhập số");
            }
        }while (end);
    }
}
