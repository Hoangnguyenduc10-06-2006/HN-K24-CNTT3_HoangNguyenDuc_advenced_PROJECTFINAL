package service;

import dao.CategoryDAO;
import dao.ProductDAO;
import model.Product;
import utill.ColerUtil;

import java.awt.*;
import java.util.Scanner;

public class ProductService {
    static  int pageStatic =1 ;
    static int sizeStatic  = 5;
    public static void addProduct(Scanner sc) {
        String nameInput;
        double priceInput = 0;
        int stockInput = 0;
        int categoryInput = 0;
        String brandInput;
        String statusInput;
        int storageInput = 0;
        String colorInput;
        String descriptionInput;

        boolean checkPrice;
        boolean checkStock;
        boolean checkStatus;
        boolean checkStorage;
        boolean checkIdCategory;
        System.out.println(ColerUtil.PURPLE + "═══════ " + ColerUtil.BLUE + "NHẬP THÔNG TIN SẢN PHẨM" + ColerUtil.PURPLE + " ═══════");
        do {
            System.out.println(ColerUtil.BLUE+"Mời bạn nhập tên sản phẩm: ");
            nameInput = sc.nextLine();

            if (nameInput.trim().isEmpty()) {
                System.out.println(ColerUtil.RED+"Không được bỏ trống!");
            } else if (!nameInput.matches("^[\\p{L}\\d\\s-]+$")) {
                System.out.println(ColerUtil.RED+"Tên sản phẩm không hợp lệ (chỉ gồm chữ, số, khoảng trắng, dấu '-')!");
            }

        } while (nameInput.trim().isEmpty() || !nameInput.matches("^[\\p{L}\\d\\s-]+$"));

        do {
            checkPrice = true;
            System.out.println(ColerUtil.BLUE+"mời bạn nhập giá sản phẩm: ");
            String priceTemp = sc.nextLine();

            if (priceTemp.trim().isEmpty()) {
                System.out.println(ColerUtil.RED+"không được bỏ trống ");
            } else {
                try {
                    priceInput = Double.parseDouble(priceTemp);
                    if (priceInput > 0) {
                        checkPrice = false;
                    } else {
                        System.out.println(ColerUtil.RED+"Giá phải lớn hơn 0!");
                    }
                } catch (NumberFormatException e) {
                    System.out.println(ColerUtil.RED+"Chỉ được nhập số!");
                }
            }
            ;
        } while (checkPrice);

        do {
            checkStock = true;
            System.out.println(ColerUtil.BLUE+"Mời bạn nhập số lượng trong kho sản phẩm: ");
            String stockTemp = sc.nextLine();

            if (stockTemp.trim().isEmpty()) {
                System.out.println(ColerUtil.RED+"Không được bỏ trống!");
            } else {
                try {
                    stockInput = Integer.parseInt(stockTemp);

                    if (stockInput <= 0) {
                        System.out.println(ColerUtil.RED+"Số lượng phải lớn hơn 0!");
                    } else {
                        checkStock = false;
                    }

                } catch (NumberFormatException e) {
                    System.out.println(ColerUtil.RED+"Chỉ được nhập số nguyên!");
                }
            }
        } while (checkStock);
        System.out.println(ColerUtil.PURPLE + "═══════ " + ColerUtil.BLUE + "NHẬP THÔNG TIN SẢN PHẨM" + ColerUtil.PURPLE + " ═══════");
        CategoryDAO.getAllNameCategory();

        do {
            checkIdCategory = true;
            System.out.println(ColerUtil.BLUE+"Mời bạn nhập id loại sản phẩm thuộc danh mục: ");
            String input = sc.nextLine();

            try {
                categoryInput = Integer.parseInt(input);
                if (CategoryDAO.checkIdExist(categoryInput)) {
                    checkIdCategory = false;
                } else {
                    System.out.println(ColerUtil.RED+"ID không tồn tại!");
                }
            } catch (NumberFormatException e) {
                System.out.println(ColerUtil.RED+"Chỉ được nhập số nguyên!");
            }
        } while (checkIdCategory);

        do {
            System.out.println(ColerUtil.BLUE+"Mời bạn nhập thương hiệu sản phẩm: ");
            brandInput = sc.nextLine();

            if (brandInput.trim().isEmpty()) {
                System.out.println(ColerUtil.RED+"Không được bỏ trống!");
            } else if (!brandInput.matches("^[\\p{L} ]+$")) {
                System.out.println(ColerUtil.RED+"Chỉ được nhập chữ cái!");
            }

        } while (brandInput.trim().isEmpty() || !brandInput.matches("^[\\p{L} ]+$"));

        do {
            checkStatus = true;
            System.out.println(ColerUtil.BLUE+"mời bạn nhập trạng thái('IN_STOCK', 'OUT_OF_STOCK', 'DISCONTINUED') sản phẩm: ");
            statusInput = sc.nextLine().toUpperCase();

            if (statusInput.trim().isEmpty()) {
                System.out.println(ColerUtil.RED+"không được bỏ trống ");
            } else if (!statusInput.matches("^[A-Z_]+$")) {
                System.out.println(ColerUtil.RED+"Không được nhập số hoặc ký tự đặc biệt!");
            } else {
                switch (statusInput) {
                    case "IN_STOCK":
                        checkStatus = false;
                        break;
                    case "OUT_OF_STOCK":
                        checkStatus = false;
                        break;
                    case "DISCONTINUED":
                        checkStatus = false;
                        break;
                    default:
                        System.out.println(ColerUtil.RED+"bạn chọn nhập trạng thái ko đúng!!!");
                }
            }

        } while (checkStatus);

        do {
            checkStorage = true;
            System.out.println(ColerUtil.BLUE+"Mời bạn nhập dung lượng sản phẩm: ");
            String storageTemp = sc.nextLine();

            if (storageTemp.trim().isEmpty()) {
                System.out.println(ColerUtil.RED+"Không được bỏ trống!");
            } else {
                try {
                    storageInput = Integer.parseInt(storageTemp);

                    if (storageInput < 0) {
                        System.out.println(ColerUtil.RED+"Không được nhập số âm!");
                    } else {
                        checkStorage = false;
                    }

                } catch (NumberFormatException e) {
                    System.out.println(ColerUtil.RED+"Chỉ được nhập số nguyên!");
                }
            }

        } while (checkStorage);

        do {
            System.out.println(ColerUtil.BLUE+"Mời bạn nhập màu sắc sản phẩm: ");
            colorInput = sc.nextLine();

            if (colorInput.trim().isEmpty()) {
                System.out.println(ColerUtil.RED+"Không được bỏ trống!");
            } else if (!colorInput.matches("^[\\p{L} ]+$")) {
                System.out.println(ColerUtil.RED+"Không được nhập số hoặc ký tự đặc biệt!");
            }

        } while (colorInput.trim().isEmpty() || !colorInput.matches("^[\\p{L} ]+$"));


        System.out.println(ColerUtil.BLUE+"mời bạn nhập mô tả sản phẩm: ");
        descriptionInput = sc.nextLine();

        Product newProduct = new Product(nameInput, priceInput, stockInput, categoryInput, brandInput, statusInput, storageInput, colorInput, descriptionInput);

        ProductDAO.insertProduct(newProduct);
    }

    public static void showProducts(Scanner sc) {
        int page = pageStatic;
        int size = sizeStatic;

        int total = ProductDAO.countProduct();
        int totalPage = (int) Math.ceil((double) total / size);

        int choice;
        System.out.println(ColerUtil.BLUE+"======== danh sách thông tin tất cả sản phẩm  =======");
        do {

            ProductDAO.selectAllProduct(page, size);
            System.out.println("---------------------------------------------------------------------------------");
            if (page > 1) {
                System.out.print(ColerUtil.BLUE+"<<< 1.trang trước |");
            } else {
                System.out.print("<<< "+ColerUtil.BLUE+"1.Đã là trang đầu "+ColerUtil.PURPLE+"|");
            } ;

            System.out.print(" =========== "+ColerUtil.BLUE+ + page + "/" + totalPage + ColerUtil.PURPLE+" =============== ");

            if (page < totalPage) {
                System.out.println(ColerUtil.BLUE+"| 2.trang sau >>>");
            } else {
                System.out.println(ColerUtil.BLUE+"| 2.Đã là trang cuối! "+ColerUtil.PURPLE+">>>");
            } ;
            System.out.println(ColerUtil.PURPLE+"---------------------------------------------------------------------------------");


            System.out.println(ColerUtil.BLUE+"1. trang trước");
            System.out.println("2. trang sau");
            System.out.println("3. quay trở lại menu QUẢN LÝ SẢN PHẨM ");
            try {
                System.out.println(ColerUtil.YELLOW+"Lựa chọn của bạn: ");
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                throw new RuntimeException(e);
            }
            switch (choice) {
                case 2:
                    if (page < totalPage) {
                        page++;
                    }
                    break;
                case 1:
                    if (page > 1) {
                        page--;
                    }
                    break;
                case 3:
                    break;
            }
        } while (choice != 3);
    }

    public static void updateProduct(Scanner sc) {
        String nameInput;
        double priceInput = 0;
        int stockInput = 0;
        int categoryInput = 0;
        String brandInput;
        String statusInput;
        int storageInput = 0;
        String colorInput;
        String descriptionInput;

        boolean checkPrice;
        boolean checkStock;
        boolean checkStatus;
        boolean checkStorage;
        boolean checkIdCategory;




        boolean checkid;
        String idUpdate;
            do {
                checkid =true;
                System.out.println(ColerUtil.BLUE+" Mời bạn nhập id sản phẩm muốn cập nhật: ");
                idUpdate=sc.nextLine();
                if(idUpdate.trim().isEmpty()){
                    System.out.println(ColerUtil.RED+"ko được để trống!!!");
                };
                if (ProductDAO.checkIdExist(Integer.parseInt(idUpdate))){
                    System.out.println(ColerUtil.PURPLE+"=================thông tin hiện tại của sản phẩm ====================");
                    ProductDAO.searchProductById(Integer.parseInt(idUpdate));
                    checkid =false;
                }else {
                    System.out.println(ColerUtil.RED+"không tìm thấy id cần sửa trong danh sach!!!");
                }
            }while (checkid);


        System.out.println(ColerUtil.PURPLE+"===== NHẬP THÔNG TIN mới CỦA SẢN PHẨM cần cập nhật ========");
        do {
            System.out.println(ColerUtil.BLUE+"Mời bạn nhập tên mới cho sản phẩm: ");
            nameInput = sc.nextLine();

            if (nameInput.trim().isEmpty()) {
                System.out.println(ColerUtil.RED+"Không được bỏ trống!");
            } else if (!nameInput.matches("^[\\p{L}\\d\\s-]+$")) {
                System.out.println(ColerUtil.RED+"Tên sản phẩm không hợp lệ (chỉ gồm chữ, số, khoảng trắng, dấu '-')!");
            }

        } while (nameInput.trim().isEmpty() || !nameInput.matches("^[\\p{L}\\d\\s-]+$"));

        do {
            checkPrice = true;
            System.out.println(ColerUtil.BLUE+"mời bạn nhập giá mới cho sản phẩm: ");
            String priceTemp = sc.nextLine();

            if (priceTemp.trim().isEmpty()) {
                System.out.println(ColerUtil.RED+"không được bỏ trống ");
            } else {
                try {
                    priceInput = Double.parseDouble(priceTemp);
                    if (priceInput > 0) {
                        checkPrice = false;
                    } else {
                        System.out.println(ColerUtil.RED+"Giá phải lớn hơn 0!");
                    }
                } catch (NumberFormatException e) {
                    System.out.println(ColerUtil.RED+"Chỉ được nhập số!");
                }
            };
        } while (checkPrice);

        do {
            checkStock = true;
            System.out.println(ColerUtil.BLUE+"Mời bạn nhập số lượng mới cho sản phẩm: ");
            String stockTemp = sc.nextLine();

            if (stockTemp.trim().isEmpty()) {
                System.out.println(ColerUtil.RED+"Không được bỏ trống!");
            } else {
                try {
                    stockInput = Integer.parseInt(stockTemp);

                    if (stockInput <= 0) {
                        System.out.println(ColerUtil.RED+"Số lượng phải lớn hơn 0!");
                    } else {
                        checkStock = false;
                    }

                } catch (NumberFormatException e) {
                    System.out.println(ColerUtil.RED+"Chỉ được nhập số nguyên!");
                }
            }
        } while (checkStock);
        System.out.println(ColerUtil.PURPLE+"========= DANH SÁCH DANH MỤC HIỆN ĐANG CÓ ============");
        CategoryDAO.getAllNameCategory();

        do {
            checkIdCategory = true;
            System.out.println(ColerUtil.BLUE+"Mời bạn nhập id loại sản phẩm: ");
            String input = sc.nextLine();

            try {
                categoryInput = Integer.parseInt(input);
                if (CategoryDAO.checkIdExist(categoryInput)) {
                    checkIdCategory = false;
                } else {
                    System.out.println(ColerUtil.RED+"ID không tồn tại!");
                }
            } catch (NumberFormatException e) {
                System.out.println(ColerUtil.RED+"Chỉ được nhập số nguyên!");
            }
        } while (checkIdCategory);

        do {
            System.out.println(ColerUtil.BLUE+"Mời bạn nhập thương hiệu mới cho sản phẩm: ");
            brandInput = sc.nextLine();

            if (brandInput.trim().isEmpty()) {
                System.out.println(ColerUtil.RED+"Không được bỏ trống!");
            } else if (!brandInput.matches("^[\\p{L} ]+$")) {
                System.out.println(ColerUtil.RED+"Chỉ được nhập chữ cái!");
            }

        } while (brandInput.trim().isEmpty() || !brandInput.matches("^[\\p{L} ]+$"));

        do {
            checkStatus = true;
            System.out.println(ColerUtil.BLUE+"mời bạn nhập trạng thái mới ('IN_STOCK', 'OUT_OF_STOCK', 'DISCONTINUED') sản phẩm: ");
            statusInput = sc.nextLine().toUpperCase();

            if (statusInput.trim().isEmpty()) {
                System.out.println(ColerUtil.RED+"không được bỏ trống ");
            } else if (!statusInput.matches("^[A-Z_]+$")) {
                System.out.println(ColerUtil.RED+"Không được nhập số hoặc ký tự đặc biệt!");
            } else {
                switch (statusInput) {
                    case "IN_STOCK":
                        checkStatus = false;
                        break;
                    case "OUT_OF_STOCK":
                        checkStatus = false;
                        break;
                    case "DISCONTINUED":
                        checkStatus = false;
                        break;
                    default:
                        System.out.println(ColerUtil.RED+"bạn chọn nhập trạng thái ko đúng!!!");
                }
            }

        } while (checkStatus);

        do {
            checkStorage = true;
            System.out.println(ColerUtil.BLUE+"Mời bạn nhập dung lượng mới cho sản phẩm: ");
            String storageTemp = sc.nextLine();

            if (storageTemp.trim().isEmpty()) {
                System.out.println(ColerUtil.RED+"Không được bỏ trống!");
            } else {
                try {
                    storageInput = Integer.parseInt(storageTemp);

                    if (storageInput < 0) {
                        System.out.println(ColerUtil.RED+"Không được nhập số âm!");
                    } else {
                        checkStorage = false;
                    }

                } catch (NumberFormatException e) {
                    System.out.println(ColerUtil.RED+"Chỉ được nhập số nguyên!");
                }
            }

        } while (checkStorage);

        do {
            System.out.println(ColerUtil.BLUE+"Mời bạn nhập màu sắc mới cho sản phẩm: ");
            colorInput = sc.nextLine();

            if (colorInput.trim().isEmpty()) {
                System.out.println(ColerUtil.RED+"Không được bỏ trống!");
            } else if (!colorInput.matches("^[\\p{L} ]+$")) {
                System.out.println(ColerUtil.RED+"Không được nhập số hoặc ký tự đặc biệt!");
            }

        } while (colorInput.trim().isEmpty() || !colorInput.matches("^[\\p{L} ]+$"));


        System.out.println(ColerUtil.BLUE+"mời bạn nhập mô tả mới sản phẩm: ");
        descriptionInput = sc.nextLine();

        Product product =new Product( nameInput,priceInput,stockInput,categoryInput,brandInput,statusInput,storageInput,colorInput,descriptionInput);
        ProductDAO.UpdateProduct(product,Integer.parseInt(idUpdate));

    }
    public static void deleteProduct(Scanner sc) {
        int idInput = 0;
        boolean check = true;

        do {
            System.out.println(ColerUtil.BLUE+"Mời bạn nhập id cần xóa: ");
            String input = sc.nextLine();

            try {
                idInput = Integer.parseInt(input);
                if (idInput <= 0) {
                    System.out.println(ColerUtil.RED+"ID phải > 0!");
                } else if (!ProductDAO.checkIdExist(idInput)) {
                    System.out.println(ColerUtil.RED+"ID không tồn tại!");
                } else {
                    check = false;
                }
            } catch (NumberFormatException e) {
                System.out.println(ColerUtil.RED+"Chỉ được nhập số!");
            }
        } while (check);

        System.out.println(ColerUtil.BLUE+"Bạn có chắc chắn muốn XÓA HẲN? (y/n)");
        String confirm = sc.nextLine().toLowerCase();
            boolean breakLoop;
            do {
                breakLoop=true;
                switch (confirm){
                    case "y":
                        ProductDAO.deleteProduct(idInput);
                        breakLoop=false;
                        break;
                    case "n":
                        System.out.println(ColerUtil.RED+"Đã hủy!");
                        breakLoop=false;
                        break;
                    default:
                        System.out.println(ColerUtil.RED+"lựa chọn ko hợp lệ!!!");
                }
            }while (breakLoop);
    }
    public static void sortASC(Scanner sc) {
        int page = pageStatic;
        int size = sizeStatic;

        int total = ProductDAO.countProduct();
        int totalPage = (int) Math.ceil((double) total / size);

        int choice;
            System.out.println(ColerUtil.BLUE+"======== Sắp xếp tăng dần  =======");
        do {

            ProductDAO.orderByPriceProductASC(page, size);
            System.out.println("---------------------------------------------------------------------------------");
            if (page > 1) {
                System.out.print(ColerUtil.BLUE+"<<< 1.trang trước |");
            } else {
                System.out.print("<<< "+ColerUtil.BLUE+"1.Đã là trang đầu "+ColerUtil.PURPLE+"|");
            } ;

            System.out.print(" =========== "+ColerUtil.BLUE+ + page + "/" + totalPage + ColerUtil.PURPLE+" =============== ");

            if (page < totalPage) {
                System.out.println(ColerUtil.BLUE+"| 2.trang sau >>>");
            } else {
                System.out.println(ColerUtil.BLUE+"| 2.Đã là trang cuối! "+ColerUtil.PURPLE+">>>");
            } ;
            System.out.println(ColerUtil.PURPLE+"---------------------------------------------------------------------------------");


            System.out.println(ColerUtil.BLUE+"1. trang trước");
            System.out.println("2. trang sau");
            System.out.println("3. quay trở lại menu QUẢN LÝ SẢN PHẨM ");
            try {
                System.out.println(ColerUtil.YELLOW+"Lựa chọn của bạn: ");
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                throw new RuntimeException(e);
            }
            switch (choice) {
                case 2:
                    if (page < totalPage) {
                        page++;
                    }
                    break;
                case 1:
                    if (page > 1) {
                        page--;
                    }
                    break;
                case 3:
                    break;
            }
        } while (choice != 3);

    }

    public static void sortDESC(Scanner sc) {
        int page = 1;
        int size = 5;

        int total = ProductDAO.countProduct();
        int totalPage = (int) Math.ceil((double) total / size);

        int choice;
        System.out.println(ColerUtil.BLUE+"======== Sắp xếp giảm dần  =======");
        do {

            ProductDAO.orderByPriveProductDESC(page, size);
            System.out.println("---------------------------------------------------------------------------------");
            if (page > 1) {
                System.out.print(ColerUtil.BLUE+"<<< 1.trang trước |");
            } else {
                System.out.print("<<< "+ColerUtil.BLUE+"1.Đã là trang đầu "+ColerUtil.PURPLE+"|");
            } ;

            System.out.print(" =========== "+ColerUtil.BLUE+ + page + "/" + totalPage + ColerUtil.PURPLE+" =============== ");

            if (page < totalPage) {
                System.out.println(ColerUtil.BLUE+"| 2.trang sau >>>");
            } else {
                System.out.println(ColerUtil.BLUE+"| 2.Đã là trang cuối! "+ColerUtil.PURPLE+">>>");
            } ;
            System.out.println(ColerUtil.PURPLE+"---------------------------------------------------------------------------------");


            System.out.println(ColerUtil.BLUE+"1. trang trước");
            System.out.println("2. trang sau");
            System.out.println("3. quay trở lại menu QUẢN LÝ SẢN PHẨM ");
            try {
                System.out.println(ColerUtil.YELLOW+"Lựa chọn của bạn: ");
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                throw new RuntimeException(e);
            }
            switch (choice) {
                case 2:
                    if (page < totalPage) {
                        page++;
                    }
                    break;
                case 1:
                    if (page > 1) {
                        page--;
                    }
                    break;
                case 3:
                    break;
            }
        } while (choice != 3);
    }
    public static void searchProduct(Scanner sc) {
        String nameInput;
        boolean check ;
        do {
            check=true;
            System.out.println(ColerUtil.BLUE+"mời bạn nhập tên hãng sản phẩm muốn tìm kiếm");
            nameInput = sc.nextLine();

            if (nameInput.trim().isEmpty()) {
                System.out.println(ColerUtil.RED+"Không được bỏ trống!");
            } else if (!nameInput.matches("^[\\p{L}\\d\\s-]+$")) {
                System.out.println(ColerUtil.RED+"Tên sản phẩm không hợp lệ (chỉ gồm chữ, số, khoảng trắng, dấu '-')!");
            } else if (ProductDAO.checkNameExist(nameInput)) {
                ProductDAO.searchProductByName(nameInput) ;
                check=false;
            }else {
                System.out.println(ColerUtil.RED+"ko tìm thấy tên sản phẩm cần tìm kiếm");
            }
        } while (check);

    }
    public static  void searchProductByCategoty(Scanner sc){
        int categoryInput = 0;
        boolean checkIdCategory;

            System.out.println(ColerUtil.PURPLE+"========= DANH SÁCH DANH MỤC HIỆN ĐANG CÓ ============");
            CategoryDAO.getAllNameCategory();

            do {
                checkIdCategory = true;
                System.out.println(ColerUtil.BLUE+"Mời bạn nhập id loại danh mục muốn tìm kiếm: ");
                String input = sc.nextLine();

                try {
                    categoryInput = Integer.parseInt(input);
                    if (CategoryDAO.checkIdExist(categoryInput)) {
                        ProductDAO.searchProductByCategory(categoryInput); ;
                        checkIdCategory = false;
                    } else {
                        System.out.println(ColerUtil.RED+"ID không tồn tại!");
                    }
                } catch (NumberFormatException e) {
                    System.out.println(ColerUtil.RED+"Chỉ được nhập số nguyên!");
                }
            } while (checkIdCategory);
    }

    public static void searchProductByBrand(Scanner sc){
        String brandInput;
        boolean check ;
        do {
            check=true;
            System.out.println(ColerUtil.BLUE+"Mời bạn nhập tên hãng sản phẩm muốn tìm kiếm: ");
            brandInput = sc.nextLine();

            if (brandInput.trim().isEmpty()) {
                System.out.println(ColerUtil.RED+"Không được bỏ trống!");
            } else if (!brandInput.matches("^[\\p{L}\\d\\s-]+$")) {
                System.out.println(ColerUtil.RED+"Tên sản phẩm không hợp lệ (chỉ gồm chữ, số, khoảng trắng, dấu '-')!");
            } else if (ProductDAO.checkBrandExist(brandInput)) {
                ProductDAO.searchProductByBrand(brandInput); ;
                check=false;
            }else {
                System.out.println(ColerUtil.RED+" ko tìm thấy tên sản phẩm cần tìm kiếm");
            }
        } while (check);
    }


}
