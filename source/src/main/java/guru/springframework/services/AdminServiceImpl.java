package guru.springframework.services;

import guru.springframework.commands.ProductForm;
import guru.springframework.commands.UsersForm;
import guru.springframework.converters.ProductFormToProduct;
import guru.springframework.domain.Advisory;
import guru.springframework.domain.Product;
import guru.springframework.domain.Users;
import guru.springframework.repositories.ProductRepository;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jt on 1/10/17.
 */
@Service
public class AdminServiceImpl implements AdminService {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public AdminServiceImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<Users> listAll() {
        List<Users> users = mongoTemplate.findAll(Users.class);
        return users;
    }

    @Override
    public Users getById(String id) {
        return mongoTemplate.findById(id, Users.class);
    }

    @Override
    public Users saveOrUpdate(Users users) {
    	users = mongoTemplate.save(users);
        return users;
    }

    @Override
    public void delete(String id) {
        Users users = mongoTemplate.findById(id, Users.class);
        mongoTemplate.remove(users);
    }

	@Override
	public Users getByEmail(String email) {
		Users user = new Users();
		Query query = new Query();
		query.addCriteria(Criteria.where("email").is(email));
		user = mongoTemplate.findOne(query, Users.class);
		return user;
	}
}
