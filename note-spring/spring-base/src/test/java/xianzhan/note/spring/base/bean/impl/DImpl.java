package xianzhan.note.spring.base.bean.impl;

import org.springframework.stereotype.Service;
import xianzhan.note.spring.base.bean.ID;

/**
 * @author xianzhan
 * @since 2022-11-17
 */
@Service
public class DImpl implements ID {

    public DImpl() {
        System.out.println("DImpl");
    }
}
