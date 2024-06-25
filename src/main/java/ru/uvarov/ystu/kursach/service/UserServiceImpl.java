package ru.uvarov.ystu.kursach.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.uvarov.ystu.kursach.exceptions.UsernameAlreadyExistsExeption;
import ru.uvarov.ystu.kursach.model.User;
import ru.uvarov.ystu.kursach.model.UserAuthority;
import ru.uvarov.ystu.kursach.model.UserRole;
import ru.uvarov.ystu.kursach.repository.UserRepository;
import ru.uvarov.ystu.kursach.repository.UserRolesRepository;


@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final UserRolesRepository userRolesRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void registration(String username, String password) {
        if (userRepository.findByUsername(username).isEmpty()){
            User user = userRepository.save(
                    new User()
                            .setId(null)
                            .setUsername(username)
                            .setPassword(passwordEncoder.encode(password))
                            .setEnabled(true)
                            .setExpired(false)
                            .setLocked(false)
            );
            userRolesRepository.save(new UserRole(null, UserAuthority.BUYER, user));
        }
        else{
            throw new UsernameAlreadyExistsExeption();
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
