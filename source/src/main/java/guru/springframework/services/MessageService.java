package guru.springframework.services;

import guru.springframework.domain.Message;

import java.util.List;

/**
 * Created by jt on 1/10/17.
 */
public interface MessageService {

    List<Message> listAll();
    
    Message getById(String id);

    Message saveOrUpdate(Message message);

    //void delete(String id);

}
