package com.cts.controller;

import java.io.FileNotFoundException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cts.dao.FetchFromDB;
import com.cts.vo.InputVO;

@Controller
public class Controller1 {
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String showHomePage(Model model) {
		model.addAttribute("home", new InputVO());
		return "home";
	}

	@RequestMapping(value = "/hit", method = RequestMethod.POST)
	public String forwardtoexcelcreation(Model model, @ModelAttribute("home") InputVO e) throws FileNotFoundException {
		FetchFromDB o = new FetchFromDB();
		boolean flag = o.fetch(e);
		if (flag == true) {
			return "success";
		} else {
			return "failed";
		}
	}
}
