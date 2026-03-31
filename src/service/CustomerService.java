package service;

import dao.CustomerDAO;
import dao.UserDAO;
import utill.ColerUtil;

import java.util.Scanner;

public class CustomerService {
    //PHƯƠNG THỨC Case 1
    public static void selectINformationCustomer(String emailUserLogging, String passwordUserLogging) {
        System.out.println(ColerUtil.PURPLE+"============ Hiên thị thông tin khách =================");
        CustomerDAO.selectINformationCustomer(emailUserLogging, passwordUserLogging);
    }
    public static void UpdateINformationCustomer(String emailUserLogging, String passwordUserLogging, Scanner sc) {
        String nameValue;
        String gmailValue;
        String phoneValue;
        String addressValue;

        String checkPassword;

        boolean checkPass;
        boolean checkGmail;
        boolean checkPhone;

        System.out.println(ColerUtil.PURPLE+"=============== NHẬP THÔNG TIN MỚI ====================");


        do {
            checkPass=true;
            System.out.println(ColerUtil.BLUE+"nhập lại mật khẩu để xác nhận cập nhật:");
            checkPassword=sc.nextLine();

            if (checkPassword.equals(passwordUserLogging)){
                checkPass=false;
            }else {
                System.out.println(ColerUtil.RED+"Mật khẩu không hợp lệ");
            }
        }while (checkPass);

        do {
            System.out.println(ColerUtil.BLUE+"Mời bạn nhập họ và tên mới:");
            nameValue = sc.nextLine();

            if (nameValue.trim().isEmpty()) {
                System.out.println(ColerUtil.RED+"Không được để trống!");
            } else if (!nameValue.matches("^[\\p{L} ]+$")) {
                System.out.println(ColerUtil.RED+"Tên chỉ được chứa chữ cái và khoảng trắng, không có số hoặc ký tự đặc biệt!");
            }

        } while (nameValue.trim().isEmpty() || !nameValue.matches("^[\\p{L} ]+$"));

        do {
            checkGmail = true;
            System.out.println(ColerUtil.BLUE+"Mời bạn nhập gmail mới:");
            gmailValue = sc.nextLine();

            if (gmailValue.trim().isEmpty()) {
                System.out.println(ColerUtil.RED+"Không được để trống!");
            } else if (!gmailValue.matches("^[A-Za-z0-9._%+-]+@gmail\\.com$")) {
                System.out.println(ColerUtil.RED+"Gmail phải đúng định dạng và kết thúc bằng @gmail.com");
            } else if (UserDAO.checkEmailExist(gmailValue)) {
                System.out.println(ColerUtil.RED+"Gmail đã được sử dụng. Vui lòng sử dụng gmail khác");
            } else {
                checkGmail = false;
            }

        } while (checkGmail);

        do {
            checkPhone = true;
            System.out.println(ColerUtil.BLUE+"Mời bạn nhập số điện thoại mới:");
            phoneValue = sc.nextLine();

            if (phoneValue.trim().isEmpty()) {
                System.out.println(ColerUtil.RED+"Không được để trống!");
            } else if (!phoneValue.matches("^0\\d{9}$")) {
                System.out.println(ColerUtil.RED+"SĐT phải 10 số và bắt đầu bằng 0");
            } else if (UserDAO.checkPhoneExist(phoneValue)) {
                System.out.println(ColerUtil.RED+"số điện thoại đã được sử dụng. Vui lòng sử dụng số điện thoại khác");
            } else {
                checkPhone = false;
            }
        } while (checkPhone);

        do {
            System.out.println(ColerUtil.BLUE+"Mời bạn nhập địa chỉ:");
            addressValue = sc.nextLine();
            if (addressValue.trim().isEmpty()) {
                System.out.println(ColerUtil.RED+"Không được để trống!");
            }
        } while (addressValue.trim().isEmpty());

        CustomerDAO.updateINformationCustomer(emailUserLogging, nameValue,
                gmailValue,
                phoneValue,
                addressValue);

    }
    //PHƯƠNG THỨC Case


    // case ad
    public static void selectAllCustomer(){
        System.out.println(ColerUtil.PURPLE+"══════════════════════════════════════════════════════════ DANH SACH USER ═══════════════════════════════════════════════════════════════════════");
        UserDAO.getAllUsers();
    }

    public static  void updateStatusCustomer(Scanner sc){
        String input;
        int userId = 0;

        boolean breakLoop;
        do {
            breakLoop=true;
            System.out.println(ColerUtil.BLUE+"Nhập ID user cần khóa:");
            input = sc.nextLine();

            if (input.trim().isEmpty()) {
                System.out.println(ColerUtil.RED+"Không được để trống!");
            } else {
                try {
                    userId = Integer.parseInt(input);
                    breakLoop=false;
                } catch (NumberFormatException e) {
                    System.out.println(ColerUtil.RED+"Chỉ được nhập số!");
                }
            }
        } while (breakLoop);
        UserDAO.deleteUser(userId);
    }

}
