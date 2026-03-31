package presentation;

import service.AuthService;
import utill.ColerUtil;

import java.util.Scanner;

public class MenuLogin {
    public static void main(String[] args) {
        MenuLogin.main();
    }
    public static void main() {
        Scanner sc = new Scanner(System.in);
        int choice;
        boolean end;

        do {
            end=true;
            try {
                do {
                    System.out.println(ColerUtil.PURPLE + "╔════════════════════════════════════╗");
                    System.out.println("║  "+ColerUtil.BLUE+"    ĐĂNG KÝ / ĐĂNG NHẬP   " +ColerUtil.PURPLE+"        ║");
                    System.out.println("╠════════════════════════════════════╣");
                    System.out.println("║ "+ColerUtil.BLUE+"1. Đăng ký  "+ColerUtil.PURPLE+"                       ║");
                    System.out.println("║ "+ColerUtil.BLUE+"2. Đăng nhập "+ColerUtil.PURPLE+"                      ║");
                    System.out.println("║ "+ColerUtil.BLUE+"3. Quên mật khẩu "+ColerUtil.PURPLE+"                  ║");
                    System.out.println("║ "+ColerUtil.BLUE+"4. Thoát   "+ColerUtil.PURPLE+"                        ║");
                    System.out.println("╚════════════════════════════════════╝" );
                    System.out.print(ColerUtil.YELLOW + " Lựa chọn của bạn: " );
                    choice = Integer.parseInt(sc.nextLine());

                    switch (choice) {
                        case 1:
                            AuthService.register(sc);
                            break;
                        case 2:
                            AuthService.login(sc);
                            break;
                        case 3:
                            break;
                        case 4:
                            System.out.println(ColerUtil.GREEN+"thoát chương trình thành công");
                            break;
                        default:
                            System.out.println(ColerUtil.RED+"lựa chọn không hợp lệ");
                    }
                } while (choice != 4);
                    end= false;
            }catch (NumberFormatException e) {
                System.out.println(ColerUtil.RED + "phải nhập số");
            }
        }while (end);
        System.exit(0);
    }




}
