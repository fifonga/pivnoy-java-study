package ttv.poltoraha.pivka.serviceImpl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ttv.poltoraha.pivka.entity.MyUser;
import ttv.poltoraha.pivka.repository.MyUserRepository;
import ttv.poltoraha.pivka.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final MyUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(MyUserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void updatePassword(MyUser user, String newPassword) {
        String encoded = passwordEncoder.encode(newPassword);
        user.setPassword(encoded);
        user.setMustUpdatePassword(false);
        userRepository.save(user);
    }
}