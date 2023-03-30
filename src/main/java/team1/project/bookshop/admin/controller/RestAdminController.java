package team1.project.bookshop.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;
import team1.project.bookshop.domain.Admin;
import team1.project.bookshop.exception.AdminException;
import team1.project.bookshop.model.admin.AdminService;
import team1.project.bookshop.util.Message;

@Slf4j
@RestController
@RequestMapping(value = "/rest")
public class RestAdminController {
	
	@Autowired
	private AdminService adminService;
	
	
}