package xianzhan.note.spring.base;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import xianzhan.note.spring.base.service.IBaseService;

/**
 * 启动类
 *
 * @author xianzhan
 * @since 2022-10-10
 */
public class SpringBaseMain {

    public static void main() {
        // 扫描 Main 类包下的 Bean 对象
        var context = new AnnotationConfigApplicationContext(SpringBaseMain.class.getPackageName());
        var baseService = context.getBean(IBaseService.class);
        baseService.printInfo();
    }
}
