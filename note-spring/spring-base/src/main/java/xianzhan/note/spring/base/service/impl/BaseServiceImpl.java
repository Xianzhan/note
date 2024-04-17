package xianzhan.note.spring.base.service.impl;

import org.springframework.stereotype.Service;
import xianzhan.note.spring.base.service.IBaseService;

/**
 * IBaseService 实现类
 *
 * @author xianzhan
 * @since 2023-05-23
 */
@Service
public class BaseServiceImpl implements IBaseService {

    @Override
    public String printInfo() {
        var info = "BaseServiceImpl#printInfo";
        System.out.println(info);
        return info;
    }
}
