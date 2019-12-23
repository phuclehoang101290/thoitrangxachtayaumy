package guru.springframework.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import guru.springframework.commands.EmailForm;
import guru.springframework.domain.RegisterEmail;
import guru.springframework.services.HomeService;
import guru.springframework.services.NewsService;

@Controller
public class HomeController {

	private NewsService newsService;
	private HomeService homeService;

	@Autowired
	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}

	@Autowired
	public void setNewsService(HomeService homeService) {
		this.homeService = homeService;
	}

	@RequestMapping("/")
	public String doInit(Model model) {

		return "index";
	}

	@RequestMapping("/page/{id}")
	public String doInitPage(@PathVariable String id, Model model) {
		model.addAttribute("newses", newsService.listAll(Integer.parseInt(id) - 1, 4));
		model.addAttribute("selectPage", id);
		long count = newsService.getNumberPage(4);
		List<String> lstPageNumber = new ArrayList<String>();
		for (int i = 1; i <= count; i++) {
			lstPageNumber.add(i + "");
		}
		model.addAttribute("lstPageNumber", lstPageNumber);
		model.addAttribute("emailForm", new EmailForm());
		model.addAttribute("populars", newsService.listPopular());
		return "index";
	}

	@RequestMapping(value = "/news-detail/{id}", method = RequestMethod.GET)
	public String getNews(@PathVariable String id, Model model) {
		model.addAttribute("news", newsService.getById(id));
		model.addAttribute("populars", newsService.listPopular());
		return "news-details";
	}

	@RequestMapping(value = "/register-email/{email}", method = RequestMethod.POST, produces = { "application/json",
			"application/xml" })
	public ResponseEntity<?> addEmail(@PathVariable String email, Model model, UriComponentsBuilder ucBuilder) {
		RegisterEmail obj = new RegisterEmail();
		obj.setEmail(email);
		obj.setCreateDate(new Date());
		homeService.saveOrUpdate(obj);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/register-email/{email}").buildAndExpand(email).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}
	
//	@Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;
//	@Autowired
//	private UserRepository userRepository;
//	@Autowired
//	private RoleRepository roleRepository;
//	public void createAdmin() {
//		Role newAdminRole = new Role();
//        newAdminRole.setRole("ADMIN");
//        roleRepository.save(newAdminRole);
//		Users user = new Users();
//		user.setEmail("phuclehoang101290@gmail.com");
//		user.setEnabled(true);
//		user.setFullname("Lê Hoàng Phúc");
//		user.setPassword(bCryptPasswordEncoder.encode("Abc12345"));
//		List<Role> lstRole = new ArrayList<Role>(); 
//		lstRole = roleRepository.findAll();
//		user.setRoles(lstRole);
//		userRepository.save(user);
//		
//	}
}
