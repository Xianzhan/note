package xianzhan.spring.jdbc.service;

/**
 * @author xianzhan
 * @since 2022-01-22
 */
public interface IStudentScoreService {

    void saveScore();

    void saveScoreWithTransaction();
}
