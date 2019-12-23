package guru.springframework.services;

import guru.springframework.domain.Advisory;

import java.util.List;

/**
 * Created by jt on 1/10/17.
 */
public interface AdvisoryService {

    List<Advisory> listAll();
    
    Advisory getById(String id);

    Advisory saveOrUpdate(Advisory advisory);

    //void delete(String id);

}
