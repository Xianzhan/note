package xianzhan.spring.jdbc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import xianzhan.spring.jdbc.service.IStudentScoreService;

/**
 * @author xianzhan
 * @since 2022-01-22
 */
@Service
public class StudentScoreServiceImpl implements IStudentScoreService {

    @Autowired
    private PlatformTransactionManager txManager;
    @Autowired
    private JdbcTemplate               jdbcTemplate;

    @Override
    public void saveScore() {
        TransactionDefinition td = new DefaultTransactionAttribute();
        TransactionStatus transaction = txManager.getTransaction(td);

        try {
            long l = System.currentTimeMillis();
            String sql = "INSERT INTO student_score(name, subject, score) VALUE(?, ?, ?)";
            jdbcTemplate.update(sql, Long.toString(l), Long.toString(l), 1);
            jdbcTemplate.update(sql, Long.toString(l + 1), Long.toString(l + 1), 1);

            txManager.commit(transaction);
        } catch (Exception e) {
            e.printStackTrace();
            txManager.rollback(transaction);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveScoreWithTransaction() {
        long l = System.currentTimeMillis();
        String sql = "INSERT INTO student_score(name, subject, score) VALUE(?, ?, ?)";
        jdbcTemplate.update(sql, Long.toString(l), Long.toString(l), 1);
        jdbcTemplate.update(sql, Long.toString(l + 1), Long.toString(l + 1), 1);
    }
}
