package example.foodhub.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import example.foodhub.auth.model.domain.SecurityUser;
import example.foodhub.auth.model.domain.User;
import example.foodhub.auth.repository.UserRepository;

@Service
public class SecurityUserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return SecurityUser.build(user);
    }

    public User getUser(String username) {
        return userRepository.findByEmail(username).get();
    }

}
