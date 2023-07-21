package com.charly.perpetuostudio.service.user;

import com.charly.perpetuostudio.dto.request.user.RegisterRequest;
import com.charly.perpetuostudio.exception.InvalidRequestException;
import com.charly.perpetuostudio.models.Role;
import com.charly.perpetuostudio.models.RoleType;
import com.charly.perpetuostudio.models.User;
import com.charly.perpetuostudio.repository.RoleRepository;
import com.charly.perpetuostudio.repository.UserRepository;
import org.assertj.core.util.Sets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RegisterServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder encoder;

    @InjectMocks
    private RegisterServiceImpl registerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void processErrorWhenUsernameAlreadyExists_ShouldThrowException() {

        RegisterRequest request = new RegisterRequest();
        request.setUsername("existingUsername");
        request.setEmail("test@example.com");
        request.setName("Test User");
        request.setPassword("testPassword");

        when(userRepository.existsByUsername("existingUsername")).thenReturn(true);

        assertThrows(InvalidRequestException.class, () -> registerService.process(request));
    }

    @Test
    void processErrorWhenEmailAlreadyExists_ShouldThrowException() {

        RegisterRequest request = new RegisterRequest();
        request.setUsername("username");
        request.setEmail("test@example.com");
        request.setName("Test User");
        request.setPassword("testPassword");

        when(userRepository.existsByUsername("username")).thenReturn(false);
        when(userRepository.existsByEmail("test@example.com")).thenReturn(true);

        assertThrows(InvalidRequestException.class, () -> registerService.process(request));
    }

    @Test
    void processSuccessWhenRoleIsEmpty() throws InvalidRequestException {

        RegisterRequest request = new RegisterRequest();
        request.setUsername("testUser");
        request.setEmail("test@example.com");
        request.setName("Test User");
        request.setPassword("testPassword");

        when(userRepository.existsByUsername(request.getUsername())).thenReturn(false);
        when(userRepository.existsByEmail(request.getEmail())).thenReturn(false);
        when(roleRepository.findByName(RoleType.ROLE_USER.toString())).thenReturn(Optional.of(new Role(1, RoleType.ROLE_USER)));
        when(encoder.encode(request.getPassword())).thenReturn("encodedPassword");

        // Act
        registerService.process(request);

        // Assert
        verify(userRepository, times(1)).existsByUsername(request.getUsername());
        verify(userRepository, times(1)).existsByEmail(request.getEmail());
        verify(roleRepository, times(1)).findByName(RoleType.ROLE_USER.toString());
        verify(encoder, times(1)).encode(request.getPassword());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void processSuccessWhenRoleIsSetAll() throws InvalidRequestException {

        RegisterRequest request = new RegisterRequest();
        request.setUsername("testUser");
        request.setEmail("test@example.com");
        request.setName("Test User");
        request.setPassword("testPassword");
        Set<String> roles = Sets.newHashSet();
        roles.add(RoleType.ROLE_ADMIN.toString());
        roles.add(RoleType.ROLE_MODERATOR.toString());
        roles.add(RoleType.ROLE_USER.toString());
        request.setRole(roles);

        when(userRepository.existsByUsername(request.getUsername())).thenReturn(false);
        when(userRepository.existsByEmail(request.getEmail())).thenReturn(false);
        when(roleRepository.findByName(RoleType.ROLE_USER.toString())).thenReturn(Optional.of(new Role(1, RoleType.ROLE_USER)));
        when(roleRepository.findByName(RoleType.ROLE_MODERATOR.toString())).thenReturn(Optional.of(new Role(1, RoleType.ROLE_MODERATOR)));
        when(roleRepository.findByName(RoleType.ROLE_ADMIN.toString())).thenReturn(Optional.of(new Role(1, RoleType.ROLE_ADMIN)));
        when(encoder.encode(request.getPassword())).thenReturn("encodedPassword");

        registerService.process(request);

        // Assert
        verify(userRepository, times(1)).existsByUsername(request.getUsername());
        verify(userRepository, times(1)).existsByEmail(request.getEmail());
        verify(roleRepository, times(1)).findByName(RoleType.ROLE_USER.toString());
        verify(roleRepository, times(1)).findByName(RoleType.ROLE_MODERATOR.toString());
        verify(roleRepository, times(1)).findByName(RoleType.ROLE_ADMIN.toString());
        verify(encoder, times(1)).encode(request.getPassword());
        verify(userRepository, times(1)).save(any(User.class));
    }
}