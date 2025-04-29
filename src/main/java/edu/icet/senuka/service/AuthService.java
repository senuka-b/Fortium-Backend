package edu.icet.senuka.service;

import edu.icet.senuka.dto.AuthResponse;
import edu.icet.senuka.dto.LoginUser;
import edu.icet.senuka.dto.RegisterUser;

public interface AuthService {
    public AuthResponse login(LoginUser user);
    public AuthResponse signup(RegisterUser user);
}
