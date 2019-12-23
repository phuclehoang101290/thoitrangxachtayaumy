package guru.springframework.controllers;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import guru.springframework.commands.ListAdvisoryForm;
import guru.springframework.domain.Advisory;
import guru.springframework.domain.Message;
import guru.springframework.domain.Users;
import guru.springframework.services.AdminService;
import guru.springframework.services.HomeService;
import guru.springframework.services.MessageService;


@Controller
public class AdminController {

	private MessageService messageService;
	private HomeService homeService;
	private AdminService adminService;
	@Autowired
    private ServletContext servletContext;


	@Autowired
	public void setNewsService(MessageService messageService) {
		this.messageService = messageService;
	}
	
	@Autowired
	public void setHomeService(HomeService homeService) {
		this.homeService = homeService;
	}
	
	@Autowired
	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}
		
//	@RequestMapping(value = "/send-message", method = RequestMethod.POST)
//	public String addMessage(@RequestParam Map<String,String> requestParams, Model model) {
//		Message message = new Message();
//		message.setMessage(requestParams.get("message"));
//		message.setCreateDate(new Date());
//		message.setEmail(requestParams.get("email"));
//		message.setName(requestParams.get("name"));
//		message.setPhone(requestParams.get("phone"));
//		message.setStatus(false);
//		messageService.saveOrUpdate(message);
//		model.addAttribute("message", "Thêm yêu cầu thành công.");
//		return "contact";
//	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginInit(HttpSession session, HttpServletRequest request) {
		String email = "";
		String password = "";
		Cookie[] cookies = request.getCookies();
		for(Cookie ck : cookies) {
			if(ck.getName().equals("email")) {
				email = ck.getValue();
			}
			if(ck.getName().equals("password")) {
				password = ck.getValue();
			}
		}
		
		if(checkLogin(email, password)) {
			session.setAttribute("user", email);
			return "redirect:/list-news";
		}

        return "admin/login";
    }
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session, Model model, HttpServletResponse response) {
        if(session.getAttribute("user") != null) {
        	session.removeAttribute("user");
        	//delete cookie
        	Cookie cookieEmail = new Cookie("email", null); // Not necessary, but saves bandwidth.
        	cookieEmail.setPath(servletContext.getContextPath());
        	cookieEmail.setHttpOnly(true);
        	cookieEmail.setMaxAge(0); // Don't set to -1 or it will become a session cookie!
        	Cookie cookiePassword = new Cookie("password", null); // Not necessary, but saves bandwidth.
        	cookiePassword.setPath(servletContext.getContextPath());
        	cookiePassword.setHttpOnly(true);
        	cookiePassword.setMaxAge(0); // Don't set to -1 or it will become a session cookie!
        	response.addCookie(cookieEmail);
        	response.addCookie(cookiePassword);
        }
        return "redirect:/login";
    }
	
	public boolean checkLogin(String email, String password) {
		Users user = adminService.getByEmail(email);
		if(user != null) {
			return user.getPassword().equals(password);
		}
		return false;
	}
	
	@RequestMapping(value = "/check-login", method = RequestMethod.POST)
    public String doLogin(@RequestParam Map<String,String> requestParams, Model model, HttpSession session) {
        String email = requestParams.get("email").toString();
        String password = requestParams.get("password").toString();
        if(checkLogin(email, password)) {
        	session.setAttribute("user", email);
        }
		return "redirect:/list-news";
    }
	
	@RequestMapping(value = "/change-password", method = RequestMethod.GET)
    public String doInitChangePassword(HttpSession session) {
		if(session.getAttribute("user") == null) {
			return "redirect:/login";
		}
        return "admin/forgot-password";
    }
	
	@RequestMapping(value = "/do-change-password", method = RequestMethod.POST)
    public String doChangePassword(@RequestParam Map<String,String> requestParams, Model model, HttpSession session) {
        String password = requestParams.get("password").toString();
        String email = session.getAttribute("user").toString();
        Users u = adminService.getByEmail(email);
        u.setPassword(password);
        adminService.saveOrUpdate(u);
		return "redirect:/list-news";
    }
	
	@RequestMapping(value = "/list-subscribe", method = RequestMethod.GET)
    public String doInitListSubcribe(Model model, HttpSession session) {
		if(session.getAttribute("user") == null) {
			return "redirect:/login";
		}
		model.addAttribute("lstEmail", homeService.listAll());
        return "admin/list-email";
    }
	
	@RequestMapping(value = "/list-message", method = RequestMethod.GET)
    public String doInitListMessage(Model model, HttpSession session) {
		if(session.getAttribute("user") == null) {
			return "redirect:/login";
		}
		model.addAttribute("lstMessage", messageService.listAll());
        return "admin/list-message";
    }
	
	@RequestMapping(value = "/update-message", method = RequestMethod.POST)
	public String updateStatus(@RequestParam Map<String,String> requestParams, Model model) {

		String jsonString = requestParams.get("jsonString").toString();
		ObjectMapper mapper = new ObjectMapper();
		try {
			List<ListAdvisoryForm> list = Arrays.asList(mapper.readValue(jsonString, ListAdvisoryForm[].class));
			if(list.size() > 0) {
				for(ListAdvisoryForm form : list) {
					Message message = messageService.getById(form.getAdvisoryId());
					message.setStatus(form.getAdvisoryStatus().equals("true"));
					messageService.saveOrUpdate(message);
				}
			}
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// return "redirect:/product/show/" + savedProduct.getId();
		model.addAttribute("lstMessage", messageService.listAll());
		return "redirect:/list-message";
	}
}
