package ttv.poltoraha.pivka.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import ttv.poltoraha.pivka.entity.MyUser;
import ttv.poltoraha.pivka.repository.MyUserRepository;

import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler
        implements AuthenticationSuccessHandler {

    private final MyUserRepository repo;

    public CustomAuthenticationSuccessHandler(MyUserRepository repo) {
        this.repo = repo;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest req,
                                        HttpServletResponse res,
                                        Authentication auth)
            throws IOException {
        MyUser user = repo.findByUsername(auth.getName())
                .orElseThrow(() -> new IllegalStateException("User not found"));
        if (user.isMustUpdatePassword()) {
            res.sendRedirect("/password/update");
        } else {
            res.sendRedirect("/");
        }
    }
}
