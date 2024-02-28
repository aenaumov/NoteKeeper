package ru.education.myproject1.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import ru.education.myproject1.client.ResourceWebClient;
import ru.education.myproject1.service.ResourceService;

@Service
@Transactional(readOnly = true)
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    ResourceWebClient resourceWebClient;

    @Override
    public Flux<String> getUserNotes(Long id, String user_id, String role) {
        return resourceWebClient.getAllUserNotes(id, user_id, role);
    }

}
