package guru.springframework.controllers;

import java.util.Date;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import guru.springframework.domain.Message;

import guru.springframework.services.MessageService;


@Controller
public class ContactController {

	private MessageService messageService;


	@Autowired
	public void setNewsService(MessageService messageService) {
		this.messageService = messageService;
	}
		
	@RequestMapping(value = "/send-message", method = RequestMethod.POST)
	public String addMessage(@RequestParam Map<String,String> requestParams, Model model) {
		Message message = new Message();
		message.setMessage(requestParams.get("message"));
		message.setCreateDate(new Date());
		message.setEmail(requestParams.get("email"));
		message.setName(requestParams.get("name"));
		message.setPhone(requestParams.get("phone"));
		message.setStatus(false);
		messageService.saveOrUpdate(message);
		model.addAttribute("message", "Thêm yêu cầu thành công.");
		return "contact";
	}
	
	@RequestMapping("/contact")
	public String doInitContact(Model model) {
		
		return "contact";
	}
}
