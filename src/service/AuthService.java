package service;

import dao.UserDAO;
import model.User;
import org.mindrot.jbcrypt.BCrypt;
import utill.ColerUtil;

import java.util.Scanner;

public class AuthService {
    public static void register(Scanner sc) {

        String nameValue ;
        String gmailValue;
        String phoneValue;
        String addressValue;
        String passwordValue;
        String roleValue ="CUSTOMER";

        boolean checkGmail;
        boolean checkPhone;
        boolean checkPassword;

        System.out.println(ColerUtil.PURPLE+"══════ NHẬP THÔNG TIN ĐĂNG KÍ TÀI KHOẢN ══════");

        do {
            System.out.println(ColerUtil.BLUE+"Mời bạn nhập họ và tên:");
            nameValue = sc.nextLine();

            if (nameValue.trim().isEmpty()) {
                System.out.println(ColerUtil.RED+"Không được để trống!");
            }
            else if (!nameValue.matches("^[\\p{L} ]+$")) {
                System.out.println(ColerUtil.RED+"Tên chỉ được chứa chữ cái và khoảng trắng, không có số hoặc ký tự đặc biệt!");
            }

        } while (nameValue.trim().isEmpty() || !nameValue.matches("^[\\p{L} ]+$"));


        do {
             checkGmail=true;
            System.out.println(ColerUtil.BLUE+"Mời bạn nhập gmail:");
            gmailValue = sc.nextLine();

            if (gmailValue.trim().isEmpty()) {
                System.out.println(ColerUtil.RED+"Không được để trống!");
            } else if (!gmailValue.matches("^[A-Za-z0-9._%+-]+@gmail\\.com$")) {
                System.out.println(ColerUtil.RED+"Gmail phải đúng định dạng và kết thúc bằng @gmail.com");
            } else if (UserDAO.checkEmailExist(gmailValue)){
                System.out.println(ColerUtil.RED+"Gmail đã được sử dụng. Vui lòng sử dụng gmail khác");
            } else {
                checkGmail=false;
            }


        } while (checkGmail);

        do {
            checkPhone=true;
            System.out.println(ColerUtil.BLUE+"Mời bạn nhập số điện thoại:");
            phoneValue = sc.nextLine();

            if (phoneValue.trim().isEmpty()) {
                System.out.println(ColerUtil.RED+"Không được để trống!");
            } else if (!phoneValue.matches("^0\\d{9}$")) {
                System.out.println(ColerUtil.RED+"SĐT phải 10 số và bắt đầu bằng 0");
            }else if (UserDAO.checkPhoneExist(phoneValue)) {
                System.out.println(ColerUtil.RED+"số điện thoại đã được sử dụng. Vui lòng sử dụng số điện thoại khác");
            }else {
                checkPhone=false;
            }
        } while (checkPhone);


        do {
            System.out.println(ColerUtil.BLUE+"Mời bạn nhập địa chỉ:");
            addressValue = sc.nextLine();
            if (addressValue.trim().isEmpty()) {
                System.out.println(ColerUtil.RED+"Không được để trống!");
            }
        } while (addressValue.trim().isEmpty());


        do {
            checkPassword=true;
            System.out.println(ColerUtil.BLUE+"Mời bạn nhập mật khẩu:");
            passwordValue = sc.nextLine();

            if (passwordValue.trim().isEmpty()) {
                System.out.println(ColerUtil.RED+"Không được để trống!");
            } else if (passwordValue.length() <= 6) {
                System.out.println(ColerUtil.RED+"Mật khẩu phải lớn hơn 6 ký tự");
            } else {
                checkPassword=false;
            }
        } while (checkPassword);

        String bceyptPasswword=BCrypt.hashpw(passwordValue,BCrypt.gensalt(10));
        User newUser =new  User(nameValue,gmailValue,phoneValue,addressValue,bceyptPasswword,roleValue);
        UserDAO.register(newUser);
        System.out.println(ColerUtil.GREEN+"Bạn đã tạo tài khoản thành công. Xin mời đăng nhập");

    }
    public static void login(Scanner sc){
        String inputEmail;
        String inputPassword;

        System.out.println(ColerUtil.PURPLE+"══════ NHẬP THÔNG TIN ĐĂNG NHẬP TÀI KHOẢN ══════");

        do {
            System.out.println(ColerUtil.BLUE+"Mời bạn nhập gmail: ");
            inputEmail= sc.nextLine();

            if(inputEmail.trim().isEmpty()){
                System.out.println(ColerUtil.RED+"không được bỏ trống!!!");
            }
        }while (inputEmail.trim().isEmpty());

        do {
            System.out.println(ColerUtil.BLUE+"Mời bạn nhập mật khẩu: ");
            inputPassword= sc.nextLine();

            if(inputPassword.trim().isEmpty()){
                System.out.println(ColerUtil.RED+"không được bỏ trống!!!");
            }
        }while (inputPassword.trim().isEmpty());

        UserDAO.login(inputEmail, inputPassword);
    }

    public static void main(String[] args) {
        AuthService.login(new Scanner(System.in));
    }
}
