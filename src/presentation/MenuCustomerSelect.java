package presentation;

import service.CustomerService;
import service.ProductService;
import utill.ColerUtil;

import java.util.Scanner;

public class MenuCustomerSelect {
   public static void main(){
       Scanner sc = new Scanner(System.in);
       int choice;

       do {
           System.out.println(ColerUtil.PURPLE + "╔══════════════════════════════════════╗");
           System.out.println(ColerUtil.PURPLE + "║" + ColerUtil.BLUE + "   MENU HIỂN THỊ SẢN PHẨM CUSTOMER    " + ColerUtil.PURPLE + "║");
           System.out.println(ColerUtil.PURPLE + "╠══════════════════════════════════════╣");

           System.out.println(ColerUtil.PURPLE + "║ " + ColerUtil.BLUE + "1. Hiển thị tất cả sản phẩm          " + ColerUtil.PURPLE + "║");
           System.out.println(ColerUtil.PURPLE + "║ " + ColerUtil.BLUE + "2. Sắp xếp giá tăng dần              " + ColerUtil.PURPLE + "║");
           System.out.println(ColerUtil.PURPLE + "║ " + ColerUtil.BLUE + "3. Sắp xếp giá giảm dần              " + ColerUtil.PURPLE + "║");
           System.out.println(ColerUtil.PURPLE + "║ " + ColerUtil.BLUE + "4. Tìm kiếm theo tên                 " + ColerUtil.PURPLE + "║");
           System.out.println(ColerUtil.PURPLE + "║ " + ColerUtil.BLUE + "5. Tìm kiếm theo danh mục            " + ColerUtil.PURPLE + "║");
           System.out.println(ColerUtil.PURPLE + "║ " + ColerUtil.BLUE + "6. Tìm kiếm theo hãng                " + ColerUtil.PURPLE + "║");
           System.out.println(ColerUtil.PURPLE + "║ " + ColerUtil.BLUE + "7. Thêm sản phẩm yêu thích           " + ColerUtil.PURPLE + "║");
           System.out.println(ColerUtil.PURPLE + "║ " + ColerUtil.BLUE + "8. Quay lại MENU CUSTOMER            " + ColerUtil.PURPLE + "║");

           System.out.println(ColerUtil.PURPLE + "╚══════════════════════════════════════╝");

           System.out.print(ColerUtil.YELLOW + " Lựa chọn của bạn: ");
           choice = Integer.parseInt(sc.nextLine());
           switch (choice) {
               case 1:
                   ProductService.showProducts(sc);
                   break;
               case 2:
                   ProductService.sortASC(sc);
                   break;
               case 3:
                   ProductService.sortDESC(sc);
                   break;
               case 4:
                   ProductService.searchProduct(sc);
                   break;
               case 5:
                   ProductService.searchProductByCategoty(sc);
                   break;
               case 6:
                   ProductService.searchProductByBrand(sc);
                   break;
               case 7:
                   break;
               case 8:
                   System.out.println(ColerUtil.GREEN+"Quay lại MENU CUSTOMER");
                   break;

               default:
                   System.out.println("lựa chọn không hợp lệ");
           }
       } while (choice != 8);
   }

}
