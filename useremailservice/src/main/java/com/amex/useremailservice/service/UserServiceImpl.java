package com.amex.useremailservice.service;

import com.amex.useremailservice.model.Confirmation;
import com.amex.useremailservice.model.User;
import com.amex.useremailservice.repository.ConfirmationRepository;
import com.amex.useremailservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final ConfirmationRepository confirmationRepository;
    private final EmailService emailService;
    @Override
    public User saveUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already Exists");
        }
        user.setEnabled(false);
        userRepository.save(user);

        Confirmation confirmation = new Confirmation(user);
        confirmationRepository.save(confirmation);

        //to do send email to user with token

        //emailService.sendSimpleMailMessage(user.getName(), user.getEmail(), confirmation.getToken());
     // emailService.sendMimeMessageWithAttachments(user.getName(), user.getEmail(), confirmation.getToken());
       // emailService.sendMimeWithEmbeddedFiles(user.getName(), user.getEmail(), confirmation.getToken());
       // emailService.sendHtmlEmail(user.getName(), user.getEmail(), confirmation.getToken());
        emailService.sendHtmlEmailWithEmbeddedFiles(user.getName(), user.getEmail(), confirmation.getToken());

        return user;

    }

    @Override
    public Boolean verifyToken(String token) {
        Confirmation confirmation = confirmationRepository.findByToken(token);
        User user = userRepository.findByEmailIgnoreCase(confirmation.getUser().getEmail());
        user.setEnabled(true);
        userRepository.save(user);
        //confirmationRepository.delete(confirmation);
        return Boolean.TRUE;
    }
}
