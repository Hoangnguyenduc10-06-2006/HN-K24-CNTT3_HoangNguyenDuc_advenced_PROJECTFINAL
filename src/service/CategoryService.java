package service;

import dao.CategoryDAO;
import model.Category;
import utill.ColerUtil;

import java.util.Scanner;

public class CategoryService {
    public static void insertNewCategory(Scanner sc) {
        String nameCategoryNew;

        boolean checkName;
        do {
            checkName = true;
            System.out.println(ColerUtil.BLUE+"Mời bạn nhập tên danh mục muốn thêm: ");
            nameCategoryNew = sc.nextLine();
            if (nameCategoryNew.trim().isEmpty()) {

                // check trùng nhưng: vì xóa mềm nên là sẽ xem điều kiện nếu trùng nhưng status= inactive thì đổi thành active còn nếu mà active thì hiển thị thông báo
            } else if (CategoryDAO.checkNameExist(nameCategoryNew)){
                System.out.println(ColerUtil.RED+"Tên danh mục đã tồn tại!!!");
            }else{
                checkName = false;
            }
        } while (checkName);

        System.out.println(ColerUtil.BLUE+"Mời bạn nhập mô tả của danh mục muốn thêm: ");
        String desNewCategory= sc.nextLine();

        Category categoryNew =new Category(nameCategoryNew,desNewCategory);
        CategoryDAO.insert(categoryNew);
    }

    public static void updateCategory(Scanner sc) {
        boolean checkid;
        boolean checkNewName;

        String idUpdate;
        String newName;
        String newDescription;
        do {
            checkid =true;
            System.out.println(ColerUtil.BLUE+" Mời bạn nhập id danh mục muốn cập nhật: ");
            idUpdate=sc.nextLine();
            if(idUpdate.trim().isEmpty()){
                System.out.println(ColerUtil.RED+"ko được để trống!!!");
            };
            if (CategoryDAO.checkIdExist(Integer.parseInt(idUpdate))){
                checkid =false;
            }else {
                System.out.println(ColerUtil.RED+"không tìm thấy id cần sửa trong danh sach!!!");
            }
        }while (checkid);

        do {
            checkNewName=true;
            System.out.println(ColerUtil.BLUE+" Mời bạn nhập tên danh mục mới muốn cập nhật: ");
            newName=sc.nextLine();
            if (newName.trim().isEmpty()){
                System.out.println(ColerUtil.RED+"ko được để trống!!!");
            }else {

                checkNewName=false;
            }
        }while (checkNewName);

        System.out.println(ColerUtil.BLUE+" Mời bạn mô tả danh mục mới muốn cập nhật: ");
        newDescription=sc.nextLine();

        Category category =new Category(newName,newDescription);
        CategoryDAO.updateCategory(category,Integer.parseInt(idUpdate));
    }

    public static void deleteCategory(Scanner sc) {
        boolean checkid;
        String idDelete;
        do {
            checkid =true;
            System.out.println(ColerUtil.BLUE+" Mời bạn nhập id danh mục muốn xóa: ");
            idDelete=sc.nextLine();
            if(idDelete.trim().isEmpty()){
                System.out.println(ColerUtil.RED+"ko được để trống!!!");
            };
            if (CategoryDAO.checkIdExist(Integer.parseInt(idDelete))){
                checkid =false;
            }else {
                System.out.println(ColerUtil.RED+"không tìm thấy id cần sửa trong danh sach!!!");
            }
        }while (checkid);
        CategoryDAO.deleteCategory(Integer.parseInt(idDelete));

    }

    public static void showCategory() {
        System.out.println(ColerUtil.PURPLE+"======= DANH SACH TAT CA CAC DANH MUC  =========");
        CategoryDAO.showlistCategory();

    }

}
