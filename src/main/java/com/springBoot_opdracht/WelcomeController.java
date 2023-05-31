package com.springBoot_opdracht;

import java.security.Principal;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {

	@GetMapping(value = "/welcome")
    public String printWelcome(Model model, Authentication authentication) {
		
		List<String> listRoles = authentication.getAuthorities()
				                               .stream()
				                               .map(GrantedAuthority::getAuthority).toList();

        model.addAttribute("userName", authentication.getName());
        model.addAttribute("userListRoles", listRoles);
        return "hello";
    }

}
