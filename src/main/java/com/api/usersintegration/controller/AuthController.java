package com.api.usersintegration.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.usersintegration.dto.LoginRequestDTO;
import com.api.usersintegration.dto.SignupRequestDTO;
import com.api.usersintegration.dto.UserInfoResponseDTO;
import com.api.usersintegration.enums.ProfileEnum;
import com.api.usersintegration.model.Profile;
import com.api.usersintegration.model.User;
import com.api.usersintegration.repository.ProfileRepository;
import com.api.usersintegration.repository.UserRepository;
import com.api.usersintegration.security.jwt.JwtUtils;
import com.api.usersintegration.security.services.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  ProfileRepository profileRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequestDTO loginRequest) {

    Authentication authentication = authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getLogin(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);

    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

    ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

    List<String> profiles = userDetails.getAuthorities().stream()
        .map(item -> item.getAuthority())
        .collect(Collectors.toList());

    return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
        .body(new UserInfoResponseDTO(userDetails.getId(),
                                   userDetails.getUsername(),
                                   userDetails.getEmail(),
                                   profiles));
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequestDTO signUpRequest) {
    if (userRepository.existsByLogin(signUpRequest.getLogin())) {
      return ResponseEntity.badRequest().body("Error: Username is already exist!");
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity.badRequest().body("Error: Email is already in exist!");
    }

    // Create new user's account
    User user = new User(signUpRequest.getLogin(),
                         signUpRequest.getEmail(),
                         encoder.encode(signUpRequest.getPassword()));

    Set<String> strProfiles = signUpRequest.getProfile();
    Set<Profile> profiles = new HashSet<>();

    if (strProfiles == null) {
      Profile userProfile = profileRepository.findByName(ProfileEnum.PROFILE_USER)
          .orElseThrow(() -> new RuntimeException("Error: Profile is not found."));
      profiles.add(userProfile);
    } else {
      strProfiles.forEach(profile -> {
        switch (profile) {
        case "admin":
          Profile adminProfile = profileRepository.findByName(ProfileEnum.PROFILE_ADMIN)
              .orElseThrow(() -> new RuntimeException("Error: Profile is not found."));
          profiles.add(adminProfile);

          break;
        default:
          Profile userProfile = profileRepository.findByName(ProfileEnum.PROFILE_USER)
              .orElseThrow(() -> new RuntimeException("Error: Profile is not found."));
          profiles.add(userProfile);
        }
      });
    }

    user.setProfiles(profiles);
    userRepository.save(user);

    return ResponseEntity.ok("User registered successfully!");
  }

  @PostMapping("/signout")
  public ResponseEntity<?> logoutUser() {
    ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
    return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
        .body("You've been signed out!");
  }
}
