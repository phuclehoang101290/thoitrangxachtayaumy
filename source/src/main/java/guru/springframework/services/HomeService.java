package guru.springframework.services;

import guru.springframework.domain.RegisterEmail;

import java.util.List;

/**
 * Created by jt on 1/10/17.
 */
public interface HomeService {

    List<RegisterEmail> listAll();

    RegisterEmail getById(String id);

    RegisterEmail saveOrUpdate(RegisterEmail email);

    void delete(String id);

}
