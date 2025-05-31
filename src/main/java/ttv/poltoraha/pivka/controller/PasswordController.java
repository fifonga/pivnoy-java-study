package ttv.poltoraha.pivka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ttv.poltoraha.pivka.entity.MyUser;
import ttv.poltoraha.pivka.repository.MyUserRepository;
import ttv.poltoraha.pivka.service.UserService;

@Controller
@RequestMapping("/password")
public class PasswordController {

    @Autowired
    private MyUserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/update")
    public String showForm() {
        return "changePasswordForm";
    }

    @PostMapping("/update")
    public String update(@RequestParam String newPassword) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        MyUser user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        userService.updatePassword(user, newPassword);
        return "redirect:/";
    }

}
