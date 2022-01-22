package xianzhan.spring.jdbc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import xianzhan.spring.jdbc.config.ScanPackage;
import xianzhan.spring.jdbc.service.IStudentScoreService;

/**
 * @author xianzhan
 * @since 2022-01-22
 */
public class Main {

    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(ScanPackage.class);
        IStudentScoreService studentScoreService = ac.getBean(IStudentScoreService.class);

//        studentScoreService.saveScore();
        studentScoreService.saveScoreWithTransaction();
    }
}
