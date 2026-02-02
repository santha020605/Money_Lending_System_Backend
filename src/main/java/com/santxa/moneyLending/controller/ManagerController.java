package com.santxa.moneyLending.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.santxa.moneyLending.models.Manager;
import com.santxa.moneyLending.models.ManagerDashboard;
import com.santxa.moneyLending.service.ManagerService;

@RestController
@RequestMapping("/api/managers")
@CrossOrigin("*")
public class ManagerController {
	
	private final ManagerService managerService;
	
	public ManagerController(ManagerService managerService) {
		this.managerService = managerService;
	}
	
	@PostMapping("/login")
	public Manager loginManager(@RequestBody Map<String, String> loginData) {
		String username = loginData.get("username");
		String password = loginData.get("password");
		
		return managerService.loginManager(username, password);
	}
	
	@GetMapping("/dashboard")
	public ManagerDashboard getDashBoard() {
		return managerService.getDashBoard();
		
	}

}
