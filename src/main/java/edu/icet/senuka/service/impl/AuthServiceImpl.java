package edu.icet.senuka.service.impl;

import edu.icet.senuka.dto.AuthResponse;
import edu.icet.senuka.dto.LoginUser;
import edu.icet.senuka.dto.RegisterUser;
import edu.icet.senuka.entity.UserEntity;
import edu.icet.senuka.repository.UserRepository;
import edu.icet.senuka.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtServiceImpl jwtService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public AuthResponse login(LoginUser user) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword()
                )
        );

        UserEntity userEntity = userRepository.findByUsername(user.getUsername()).orElseThrow();

        String token = jwtService.generateToken(userEntity);
        return AuthResponse.builder()
                .token(token)
                .fullName(userEntity.getFullName())
                .build();

    }

    @Override
    public AuthResponse signup(RegisterUser user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        UserEntity userEntity = userRepository.save(
                UserEntity.builder()
                .fullName(user.getFullName())
                .password(user.getPassword())
                .username(user.getUsername())
                .build());

        String token = jwtService.generateToken(userEntity);

        return AuthResponse.builder()
                .token(token)
                .fullName(user.getFullName())
                .build();
    }


}
