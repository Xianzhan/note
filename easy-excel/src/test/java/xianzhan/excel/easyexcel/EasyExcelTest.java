package xianzhan.excel.easyexcel;

import com.alibaba.excel.EasyExcel;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Easy Excel 测试类
 *
 * @author xianzhan
 * @since 2020-10-26
 */
public class EasyExcelTest {

    private final String path = "";
    private final String file = path + "test.xlsx";

    @Test
    public void testWrite() {
        List<People> people = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            People p = new People();
            p.setName("名" + i);
            p.setAge(i);
            p.setPhone("12345" + i);
            people.add(p);
        }

        EasyExcel.write(file, People.class)
                .sheet("流动人员")
                .doWrite(people);
    }

    @Test
    public void testRead() {
        List<People> list = EasyExcel.read(file)
                .head(People.class)
                .sheet("流动人员")
                .doReadSync();
        list.forEach(System.out::println);
    }
}
