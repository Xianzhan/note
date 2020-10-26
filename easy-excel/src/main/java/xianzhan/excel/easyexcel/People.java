package xianzhan.excel.easyexcel;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 人员信息
 *
 * @author xianzhan
 * @since 2020-10-26
 */
public class People {

    @ExcelProperty("姓名")
    private String name;
    @ExcelProperty("年龄")
    private int    age;
    @ExcelProperty("手机")
    private String phone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "People{" +
               "name='" + name + '\'' +
               ", age=" + age +
               ", phone='" + phone + '\'' +
               '}';
    }
}
