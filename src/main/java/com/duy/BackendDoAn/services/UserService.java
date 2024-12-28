package com.duy.BackendDoAn.services;

import com.duy.BackendDoAn.components.JwtTokenUtil;
import com.duy.BackendDoAn.configurations.SecureConfig;
import com.duy.BackendDoAn.dtos.UserChangePasswordDTO;
import com.duy.BackendDoAn.dtos.UserDTO;
import com.duy.BackendDoAn.dtos.UserRegisterDTO;
import com.duy.BackendDoAn.models.*;
import com.duy.BackendDoAn.repositories.*;
import com.duy.BackendDoAn.responses.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.expression.ExpressionException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class UserService {

    @Value("${user.default.avatar-url}")
    private String unknownUserAvatar;

    private final UserRepository userRepository;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    private static final Pattern EMAIL = Pattern.compile("([^@]+)@");


    public User add(UserRegisterDTO userRegisterDTO) throws Exception {
        if (userRepository.existsByEmail(userRegisterDTO.getEmail())) {
            throw new DataIntegrityViolationException("Email already been used");
        }
        if (!userRegisterDTO.getPassword().equals(userRegisterDTO.getConfirmPassword())) {
            throw new Exception("Password and Confirm password doesn't match");
        }
        User newUser = User.builder()
                .name(getUsernameFromEmail(userRegisterDTO.getEmail()))
                .avatar(unknownUserAvatar)
                .email(userRegisterDTO.getEmail())
                .active(true)
                .date_of_birth(LocalDate.of(1990, 1, 1))
                .password(userRegisterDTO.getPassword())
                .role(Optional.ofNullable(userRegisterDTO.getRole()).orElse("USER"))
                .build();
        String password = userRegisterDTO.getPassword();
        String encodedPassword = passwordEncoder.encode(password);
        newUser.setPassword(encodedPassword);
        return userRepository.save(newUser);
    }

    public User addAdmin(UserRegisterDTO userRegisterDTO) throws Exception {
        if (userRepository.existsByEmail(userRegisterDTO.getEmail())) {
            throw new DataIntegrityViolationException("Email already been used");
        }
        if (!userRegisterDTO.getPassword().equals(userRegisterDTO.getConfirmPassword())) {
            throw new Exception("Password and Confirm password doesn't match");
        }
        User newUser = User.builder()
                .name(getUsernameFromEmail(userRegisterDTO.getEmail()))
                .avatar(unknownUserAvatar)
                .email(userRegisterDTO.getEmail())
                .active(true)
                .password(userRegisterDTO.getPassword())
                .role(Optional.ofNullable(userRegisterDTO.getRole()).orElse("ADMIN"))
                .build();
        String password = userRegisterDTO.getPassword();
        String encodedPassword = passwordEncoder.encode(password);
        newUser.setPassword(encodedPassword);
        return userRepository.save(newUser);
    }



    public static String getUsernameFromEmail(String email) {
        Matcher matcher = EMAIL.matcher(email);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null; // Trả về null nếu không tìm thấy
    }

    public String login(String email, String password) throws Exception {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()) throw new Exception("Invalid email or password");
        User existingUser = optionalUser.get();

        if (!passwordEncoder.matches(password, existingUser.getPassword())) {
            throw new BadCredentialsException("Wrong email or password");
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                email, password,
                existingUser.getAuthorities()
        );
        authenticationManager.authenticate(authenticationToken);
        return jwtTokenUtil.generateToken(optionalUser.get());
    }

    public User getUserDetailFromToken(String token) throws Exception {
        if (jwtTokenUtil.isTokenExpired(token)) {
            throw new Exception("Token is expired!");
        }
        String email = jwtTokenUtil.extractEmail(token);
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new Exception("User not found");
        }
    }

    public User getUserFromId(long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User updateUserDetails(UserDTO userDTO, String token) throws Exception {
        String email = jwtTokenUtil.extractEmail(token);
        Optional<User> optionalUser = userRepository.findByEmail(email);
        User user = optionalUser.orElseThrow(() -> new Exception("User not found"));
        user.setName(userDTO.getName());
        user.setAddress(userDTO.getAddress());
        user.setFirst_name(userDTO.getFirstName());
        user.setLast_name(userDTO.getLastName());
        user.setCountry(userDTO.getCountry());
        user.setAvatar(userDTO.getAvatar());
        user.setPhone_number(userDTO.getPhoneNumber());
        user.setDate_of_birth(userDTO.getDateOfBirth());
        return userRepository.save(user);
    }

    public Page<UserResponse> getAllUsers(String keyword, PageRequest pageRequest){
        Page<User> userPage = userRepository.findAll(keyword, pageRequest);
        return userPage.map(UserResponse::fromUser);
    }

    public void switchActive(Long userId) throws Exception {
        User user = userRepository.findById(userId).orElseThrow(() -> new Exception("User not found"));
        user.setActive(!user.isActive());
        userRepository.save(user);
    }

    public boolean changePassword(UserChangePasswordDTO userChangePasswordDTO) throws Exception {
        User user = userRepository.findById(userChangePasswordDTO.getUser()).orElseThrow(()-> new Exception("Cant find user"));
        String oldPassEncode = passwordEncoder.encode(userChangePasswordDTO.getOldPassword());

        if(!passwordEncoder.matches(userChangePasswordDTO.getOldPassword(), user.getPassword()) ||
                !userChangePasswordDTO.getNewPassword().equals(userChangePasswordDTO.getConfirmNewPassword())) return false;

        String newPass = passwordEncoder.encode(userChangePasswordDTO.getNewPassword());
        user.setPassword(newPass);
        userRepository.save(user);
        return true;
    }

    public boolean forgotPassword(String email) throws Exception {
        User user = userRepository.findByEmail(email).orElseThrow(()-> new Exception("No user found"));
        String newPassword = generateRandomPassword(10);
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        emailService.sendPasswordResetEmail(user.getEmail(), newPassword);
        return true;
    }

    private String generateRandomPassword(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder password = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            password.append(characters.charAt(random.nextInt(characters.length())));
        }
        return password.toString();
    }
}
