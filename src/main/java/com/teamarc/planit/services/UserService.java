package com.teamarc.planit.services;



import com.teamarc.planit.dto.OnboardHostDTO;
import com.teamarc.planit.dto.OnboardOrganiserDTO;
import com.teamarc.planit.dto.OnboardServiceProviderDTO;
import com.teamarc.planit.entity.OnboardHost;
import com.teamarc.planit.entity.User;
import com.teamarc.planit.entity.enums.Role;
import com.teamarc.planit.exceptions.ResourceNotFoundException;
import com.teamarc.planit.repository.OnboardHostRepository;
import com.teamarc.planit.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final OnboardHostRepository onboardHostRepository;
    private final ModelMapper modelMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElse(null);
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
    }

    public User loadUserByRole(Role role) {
        return userRepository.findByRoles(role);
    }

    public void createHost(OnboardHostDTO user) {
        onboardHostRepository.save(modelMapper.map(user, OnboardHost.class));
    }

    public void createServiceProvider(OnboardServiceProviderDTO user) {
        onboardHostRepository.save(modelMapper.map(user, OnboardHost.class));
    }

    public void createOrganizer(OnboardOrganiserDTO user) {
        onboardHostRepository.save(modelMapper.map(user, OnboardHost.class));
    }
}
