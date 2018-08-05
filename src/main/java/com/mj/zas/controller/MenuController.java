package com.mj.zas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mj.zas.entity.ZAMenu;
import com.mj.zas.service.MenuService;

@RestController
@RequestMapping("/cdgl")
public class MenuController {

	@Autowired
	private MenuService menuService;

	@GetMapping("/list")
	public String list() {
		return menuService.list();
	}

	@GetMapping("/listParent")
	public String listParent() {
		return menuService.listParent();
	}

	@GetMapping("/listSon/{id}")
	public String listSon(@PathVariable String id) {
		return menuService.listSon(id);
	}

	@PostMapping("/save")
	public String save(ZAMenu menu) {
		return menuService.save(menu);
	}

	@DeleteMapping("/delete")
	public String delete(List<String> ids) {
		return menuService.delete(ids);
	}

}
