package presentation;

import service.AuthService;
import service.ProductService;
import utill.ColerUtil;

import java.util.Scanner;

public class MenuProduct {
    public static void main(Scanner sc) {
        int choice;
        do {
            System.out.println(ColerUtil.PURPLE + "╔═══════════════════════════════════════╗" );
            System.out.println(ColerUtil.PURPLE + "║" + ColerUtil.BLUE + "      MENU QUẢN LÝ SẢN PHẨM            " + ColerUtil.PURPLE + "║" );
            System.out.println(ColerUtil.PURPLE + "╠═══════════════════════════════════════╣" );

            System.out.println(ColerUtil.PURPLE + "║ " + ColerUtil.BLUE + "1. Hiển thị danh sách sản phẩm        " + ColerUtil.PURPLE + "║" );
            System.out.println(ColerUtil.PURPLE + "║ " + ColerUtil.BLUE + "2. Thêm mới sản phẩm                  " + ColerUtil.PURPLE + "║" );
            System.out.println(ColerUtil.PURPLE + "║ " + ColerUtil.BLUE + "3. Sửa thông tin sản phẩm             " + ColerUtil.PURPLE + "║" );
            System.out.println(ColerUtil.PURPLE + "║ " + ColerUtil.BLUE + "4. Xóa thông tin sản phẩm             " + ColerUtil.PURPLE + "║");
            System.out.println(ColerUtil.PURPLE + "║ " + ColerUtil.BLUE + "5. Tìm kiếm sản phẩm theo tên         " + ColerUtil.PURPLE + "║" );
            System.out.println(ColerUtil.PURPLE + "║ " + ColerUtil.BLUE + "6. Sắp xếp giá tăng                   " + ColerUtil.PURPLE + "║" );
            System.out.println(ColerUtil.PURPLE + "║ " + ColerUtil.BLUE + "7. Sắp xếp giá giảm                   " + ColerUtil.PURPLE + "║" );
            System.out.println(ColerUtil.PURPLE + "║ " + ColerUtil.BLUE + "8. Quay lại MENU ADMIN                " + ColerUtil.PURPLE + "║" );

            System.out.println(ColerUtil.PURPLE + "╚═══════════════════════════════════════╝" );

            System.out.print(ColerUtil.YELLOW + " Lựa chọn của bạn: " );
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    ProductService.showProducts(sc);
                    break;
                case 2:
                    ProductService.addProduct(sc);
                    break;
                case 3:
                    ProductService.updateProduct(sc);
                    break;
                case 4:
                    ProductService.deleteProduct(sc);
                    break;
                case 5:
                    ProductService.searchProduct(sc);
                    break;
                case 6:
                    ProductService.sortASC(sc);
                    break;
                case 7:
                    ProductService.sortDESC(sc);
                    break;
                case 8:
                    System.out.println("thoát chương trình thành công");
                    break;
                default:
                    System.out.println("lựa chọn không hợp lệ");
            }
        } while (choice != 8);
    }



}
