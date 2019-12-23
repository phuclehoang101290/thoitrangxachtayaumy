package guru.springframework.services;

import guru.springframework.domain.Users;

import java.util.List;

/**
 * Created by jt on 1/10/17.
 */
public interface AdminService {

    List<Users> listAll();

    Users getById(String id);

    Users saveOrUpdate(Users product);

    void delete(String id);
    
    Users getByEmail(String email);

}
