package presentation;

import service.AuthService;
import service.CategoryService;
import utill.ColerUtil;

import java.util.Scanner;

public class MenuCategory {
    public static void main(Scanner sc ) {

        int choice ;

        do {
            System.out.println(ColerUtil.PURPLE + "╔═══════════════════════════════╗");
            System.out.println(ColerUtil.PURPLE + "║" + ColerUtil.BLUE + "          MENU DANH MỤC        " + ColerUtil.PURPLE + "║");
            System.out.println(ColerUtil.PURPLE + "╠═══════════════════════════════╣");

            System.out.println(ColerUtil.PURPLE + "║ " + ColerUtil.BLUE + "1. Thêm danh mục              " + ColerUtil.PURPLE + "║");
            System.out.println(ColerUtil.PURPLE + "║ " + ColerUtil.BLUE + "2. Hiển thị danh mục          " + ColerUtil.PURPLE + "║");
            System.out.println(ColerUtil.PURPLE + "║ " + ColerUtil.BLUE + "3. Cập nhật danh mục          " + ColerUtil.PURPLE + "║");
            System.out.println(ColerUtil.PURPLE + "║ " + ColerUtil.BLUE + "4. Xóa danh mục               " + ColerUtil.PURPLE + "║");
            System.out.println(ColerUtil.PURPLE + "║ " + ColerUtil.BLUE + "5. Quay lại MENU ADMIN        " + ColerUtil.PURPLE + "║");

            System.out.println(ColerUtil.PURPLE + "╚═══════════════════════════════╝");

            System.out.print(ColerUtil.YELLOW + "Lựa chọn của bạn: ");
            choice =Integer.parseInt(sc.nextLine());

            switch (choice){
                case 1:
                    CategoryService.insertNewCategory(sc);
                    break;
                case 2:
                    CategoryService.showCategory();
                    break;
                case 3:
                    CategoryService.showCategory();
                    CategoryService.updateCategory(sc);
                    break;
                case 4:
                    CategoryService.deleteCategory(sc);
                    break;
                case 5:
                    System.out.println(ColerUtil.GREEN+"Quay Trở lại MENU ADMIN thành công");
                    break;
                default:
                    System.out.println("lựa chọn không hợp lệ");
            }
        }while (choice !=5);
    }


}
