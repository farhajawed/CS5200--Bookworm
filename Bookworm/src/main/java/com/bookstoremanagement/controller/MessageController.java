package com.bookstoremanagement.controller;

import java.security.Principal;
import java.time.Instant;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bookstoremanagement.dao.ConversationDao;
import com.bookstoremanagement.dao.ConversationMembersDao;
import com.bookstoremanagement.dao.MessageDao;
import com.bookstoremanagement.dao.UserDao;
import com.bookstoremanagement.model.Conversation;
import com.bookstoremanagement.model.ConversationMembers;
import com.bookstoremanagement.model.Messages;
import com.bookstoremanagement.model.UserLogin;

@Controller
public class MessageController {

	@Autowired
	private ConversationDao conversationDao;

	@Autowired
	private ConversationMembersDao conversationMemberDao;


	@Autowired
	private UserDao userDao;

	@Autowired
	private MessageDao messageDao;



	@RequestMapping(value = "/send-message", method = RequestMethod.POST)
	public ModelAndView sendMessage(HttpServletRequest request,@ModelAttribute("message")Messages message, Principal principal,
			BindingResult result, ModelMap model,final RedirectAttributes redirectAttributes) {
		Conversation conversation = message.getConversation();
		conversationDao.saveConversation(conversation);
		ConversationMembers cm = new ConversationMembers();
		cm.setConversation(conversation);
		cm.setUserLogin(message.getUserLogin());
		conversationMemberDao.saveConversationMember(cm);
		ModelAndView mv = new ModelAndView("redirect:/show/"+message.getUserLogin().getUsername());
		UserLogin userLogin = userDao.findUserInfoByUsername(principal.getName());
		ConversationMembers cm2=new ConversationMembers();
		long unixTimestamp = Instant.now().getEpochSecond();
		cm2.setConversation(conversation);
		cm2.setUserLogin(userLogin);
		cm2.setLastView(unixTimestamp);
		conversationMemberDao.saveConversationMember(cm2);
		message.setUserLogin(userLogin);
		message.setDate(unixTimestamp);
		messageDao.saveMessage(message);
		Boolean sent = true;
		redirectAttributes.addFlashAttribute("sent", sent);
		return mv;
	}

	@RequestMapping(value = "/inbox")
	public ModelAndView inbox(Principal principal) {		
		ModelAndView mv = new ModelAndView("page"); 
		if(principal!=null) {
			String username = principal.getName();
			List<Object>conversation = conversationDao.list(username);
			mv.addObject("conversation",conversation);
		}
		mv.addObject("userClickInbox",true);
		return mv;
	}

	@RequestMapping(value = "/delete-conversation", method = RequestMethod.GET)
	public ModelAndView deleteConversation(@RequestParam("conversationId") int id,Principal principal,
			final RedirectAttributes redirectAttributes) {
		ModelAndView mv = new ModelAndView("redirect:/inbox");
		if(principal!=null) {
			String username = principal.getName();
			ConversationMembers conversationMembers = 
					conversationMemberDao.searchByConversationIdAndUsername(username, id);
			if(conversationMembers.isDeleted()==true) {
				conversationDao.deleteConversation(id);
			}
			else {
				ConversationMembers cm = conversationMemberDao.searchByConversationIdAndCurrentUsername(username, id);
				cm.setDeleted(true);
				conversationMemberDao.saveConversationMember(cm);
			}
			redirectAttributes.addFlashAttribute("deleted",true);
		}
		return mv;

	}

	@RequestMapping(value = "/view-conversation", method = RequestMethod.GET)
	public ModelAndView viewConversation(HttpServletRequest request, @RequestParam("conversationId") int id,Principal principal,
			final RedirectAttributes redirectAttributes) {
		ModelAndView mv = new ModelAndView("page");
		if(principal!=null) {
			mv.addObject("userClickViewConversation",true);
			List<Messages>messages = messageDao.list(id);
			mv.addObject("messages",messages);
			String username = principal.getName();
			ConversationMembers conversationMembers = 
					conversationMemberDao.searchByConversationIdAndUsername(username, id);
			UserLogin userLogin = conversationMembers.getUserLogin();
			ConversationMembers cm = conversationMemberDao.
					searchByConversationIdAndCurrentUsername(username, id);
			long unixTimestamp = Instant.now().getEpochSecond();
			cm.setLastView(unixTimestamp);
			conversationMemberDao.saveConversationMember(cm);
			mv.addObject("userLogin",userLogin);
			Conversation conversation = conversationDao.getConversation(id);
			Messages msg = new Messages();
			UserLogin uLogin = userDao.findUserInfoByUsername(principal.getName());
			msg.setUserLogin(uLogin);
			msg.setConversation(conversation);
			mv.addObject("msg",msg);
		}
		return mv;
	}	

	@RequestMapping(value = "/add-message", method = RequestMethod.POST)
	public ModelAndView addMessage(@ModelAttribute("msg")Messages message, Principal principal,
			BindingResult result, ModelMap model) {
		long unixTimestamp = Instant.now().getEpochSecond();
		message.setDate(unixTimestamp);
		messageDao.saveMessage(message);
		ModelAndView mv = new ModelAndView("redirect:/view-conversation?conversationId="+message.getConversation().getId());
		return mv;
	}

}
