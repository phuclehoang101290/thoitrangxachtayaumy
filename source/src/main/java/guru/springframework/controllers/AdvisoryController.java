package guru.springframework.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import guru.springframework.commands.EmailForm;
import guru.springframework.commands.ListAdvisoryForm;
import guru.springframework.commands.ListNewsForm;
import guru.springframework.commands.NewsForm;
import guru.springframework.domain.Advisory;
import guru.springframework.domain.News;
import guru.springframework.domain.RegisterEmail;
import guru.springframework.services.AdvisoryService;
import guru.springframework.services.HomeService;
import guru.springframework.services.NewsService;

@Controller
public class AdvisoryController {

	private NewsService newsService;
	private HomeService homeService;
	private AdvisoryService advisoryService;

	@Autowired
	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}
	
	@Autowired
	public void setNewsService(HomeService homeService) {
		this.homeService = homeService;
	}
	
	@Autowired
	public void setNewsService(AdvisoryService advisoryService) {
		this.advisoryService = advisoryService;
	}
	

	@RequestMapping("/advisory")
	public String doInit(Model model) {
		model.addAttribute("populars", newsService.listPopular());
		model.addAttribute("emailForm", new EmailForm());
		return "advisory";
	}
	
	@RequestMapping(value = "/add-advisory", method = RequestMethod.POST)
	public String addadvisory(@RequestParam Map<String,String> requestParams, Model model) {
		Advisory advisory = new Advisory();
		advisory.setContent(requestParams.get("content"));
		advisory.setCreateDate(new Date());
		advisory.setEmail(requestParams.get("email"));
		advisory.setName(requestParams.get("name"));
		advisory.setPhone(requestParams.get("phone"));
		advisory.setStatus(false);
		advisoryService.saveOrUpdate(advisory);
		model.addAttribute("emailForm", new EmailForm());
		model.addAttribute("message", "Thêm yêu cầu thành công.");
		return "advisory";
	}
	
	@RequestMapping("/list-advisory")
	public String getListAdvisory(Model model) {
		model.addAttribute("lstAdvisory", advisoryService.listAll());
		return "admin/list-advisory";
	}
	
	@RequestMapping(value = "/update-advisory", method = RequestMethod.POST)
	public String updateStatus(@RequestParam Map<String,String> requestParams, Model model) {

		String jsonString = requestParams.get("jsonString").toString();
		ObjectMapper mapper = new ObjectMapper();
		try {
			List<ListAdvisoryForm> list = Arrays.asList(mapper.readValue(jsonString, ListAdvisoryForm[].class));
			if(list.size() > 0) {
				for(ListAdvisoryForm form : list) {
					Advisory advisory = advisoryService.getById(form.getAdvisoryId());
					advisory.setStatus(form.getAdvisoryStatus().equals("true"));
					advisoryService.saveOrUpdate(advisory);
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
		model.addAttribute("lstAdvisory", advisoryService.listAll());
		return "redirect:/list-advisory";
	}
}
