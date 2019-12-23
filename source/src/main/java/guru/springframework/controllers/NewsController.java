package guru.springframework.controllers;

import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import guru.springframework.commands.ListNewsForm;
import guru.springframework.commands.NewsForm;
import guru.springframework.commands.PluginForm;
import guru.springframework.converters.NewsToNewsForm;
import guru.springframework.converters.NewsToPluginForm;
import guru.springframework.domain.News;
import guru.springframework.services.NewsService;

@Controller
public class NewsController {

	private NewsService newsService;
	private NewsToNewsForm newsToNewsForm;
	private NewsToPluginForm newsToPluginForm;

	@Autowired
	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}

	@Autowired
	public void setNewsToNewsForm(NewsToNewsForm newsToNewsForm) {
		this.newsToNewsForm = newsToNewsForm;
	}
	
	@Autowired
	public void setNewsToPluginForm(NewsToPluginForm newsToPluginForm) {
		this.newsToPluginForm = newsToPluginForm;
	}

	@RequestMapping(value = "/news/add/{isAdd}", method = RequestMethod.POST)
	public String addNews(@PathVariable boolean isAdd, @Valid NewsForm newsForm, BindingResult bindingResult, HttpSession session) {
		if(session.getAttribute("user") == null) {
			return "redirect:/login";
		}
		if (bindingResult.hasErrors()) {
			return "admin/add-news";
		}

		News saveNews = newsService.saveOrUpdateNewsForm(newsForm, isAdd);

		// return "redirect:/product/show/" + savedProduct.getId();
		return "redirect:/list-news";
	}

	@RequestMapping("/news")
	public String init(Model model, HttpSession session) {
		if(session.getAttribute("user") == null) {
			return "redirect:/login";
		}
		model.addAttribute("newsForm", new NewsForm());
		return "admin/add-news";
	}

	@RequestMapping("/list-news")
	public String showListNews(Model model, HttpSession session) {
		if(session.getAttribute("user") == null) {
			return "redirect:/login";
		}
		model.addAttribute("newses", newsService.listAll(0, 10));
		//model.addAttribute("listNewsForm", new ListNewsForm());
		return "admin/list-news";
	}

	@RequestMapping(value = "/news/detail/{id}", method = RequestMethod.GET)
	public String getNews(@PathVariable String id, Model model, HttpSession session) {
		if(session.getAttribute("user") == null) {
			return "redirect:/login";
		}
		model.addAttribute("news", newsService.getById(id));
		
		return "admin/detail-news";
	}

	@RequestMapping("/news/delete/{id}")
	public String delete(@PathVariable String id, HttpSession session) {
		if(session.getAttribute("user") == null) {
			return "redirect:/login";
		}
		newsService.delete(id);
		return "redirect:/list-news";
	}

	@RequestMapping("/news/edit/{id}")
	public String edit(@PathVariable String id, Model model, HttpSession session) {
		if(session.getAttribute("user") == null) {
			return "redirect:/login";
		}
		News news = newsService.getById(id);
		NewsForm newsForm = newsToNewsForm.convert(news);
		model.addAttribute("newsForm", newsForm);
		return "admin/edit-news";
	}
	
	@RequestMapping("/plugin/{id}")
	public String initPlugin(@PathVariable String id, Model model, HttpSession session) {
		if(session.getAttribute("user") == null) {
			return "redirect:/login";
		}
		PluginForm pluginForm = newsService.saveOrUpdatePlugin(id);
		model.addAttribute("pluginForm", pluginForm);
		return "admin/news-plugin-fb";
	}
	
	@RequestMapping(value = "/update-display-flg-popular", method = RequestMethod.POST)
	public String updateStatusNews(@RequestParam Map<String,String> requestParams, Model model) {

		String jsonString = requestParams.get("jsonString").toString();
		ObjectMapper mapper = new ObjectMapper();
		try {
			List<ListNewsForm> list = Arrays.asList(mapper.readValue(jsonString, ListNewsForm[].class));
			if(list.size() > 0) {
				for(ListNewsForm form : list) {
					News news = newsService.getById(form.getNewsId());
					news.setDisplayFlg(form.getNewsDisplayFlg().equals("true"));
					news.setPopularNews(form.getNewsPopular().equals("true"));
					newsService.saveOrUpdate(news);
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
		return "redirect:/list-news";
	}
	
//	@RequestMapping(value = "/plugin/edit/{id}", method = RequestMethod.POST)
//	public String editPlugin(@PathVariable boolean isAdd, @Valid PluginForm pluginForm, BindingResult bindingResult) {
//		if (bindingResult.hasErrors()) {
//			return "admin/news-plugin-fb";
//		}
//
//		News saveNews = newsService.saveOrUpdatePlugin(pluginForm);
//
//		// return "redirect:/product/show/" + savedProduct.getId();
//		return "redirect:/list-news/{" + saveNews.getId()+"}";
//	}
}
