package ru.education.myproject1.service.Impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.education.myproject1.service.ResourceService;

@Service
@Transactional(readOnly = true)
public class ResourceServiceImpl implements ResourceService {

    @Override
    public Object getUser(Long id) {
        return null;
    }

}
