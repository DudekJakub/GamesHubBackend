package com.gameshub.service;

import com.gameshub.domain.mail.Mail;
import com.gameshub.domain.user.RegistrationRequestDto;
import com.gameshub.domain.user.User;
import com.gameshub.email.EmailBuilder;
import com.gameshub.email.EmailService;
import com.gameshub.exceptions.*;
import com.gameshub.mapper.user.AppUserMapper;
import com.gameshub.validator.AccountConfirmValidator;
import com.gameshub.validator.EmailValidator;
import com.gameshub.validator.PasswordEqualityValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private static final String EMAIL_WITH_LINK_JUST_SEND = "Email with confirmation request just send!";
    private static final String ACCOUNT_VERIFIED = "Account has been verified!";

    private final EmailService emailService;
    private final UserService userService;
    private final AccountConfirmValidator confirmValidator;
    private final EmailValidator emailValidator;
    private final PasswordEqualityValidator passwordEqualityValidator;

    public String register(RegistrationRequestDto request) throws UserEmailAlreadyExistsInDatabaseException,
                                                                  UserLoginNameAlreadyExistsInDatabaseException,
                                                                  PasswordNotMatchException {

        Mail mail = EmailBuilder.createMail(
                request.getEmail(),
                "GamesHub - account confirmation",
                "Welcome to GamesHub! This is confirmation message. Please click button below."
                );

        passwordEqualityValidator.validate(request.getPassword(), request.getRepeatPassword());
        if (emailValidator.validate(request.getEmail())) throw new UserEmailAlreadyExistsInDatabaseException();

        userService.signUpUser(AppUserMapper.mapToAppUser(request));

        emailService.sendNewAccConfirmationMail(mail, request.getFirstname(), request.getLoginName());

        return EMAIL_WITH_LINK_JUST_SEND;
    }

    public String confirmAccount(final User user) throws AccessDeniedException,
                                                         UserNotFoundException,
                                                         UserAlreadyVerifiedException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();
        if (!login.equals(user.getLoginName()) && !login.equalsIgnoreCase("admin")) throw new AccessDeniedException();
        if (confirmValidator.validate(user)) throw new UserAlreadyVerifiedException();

        user.setVerified(true);
        userService.saveUser(user);

        return ACCOUNT_VERIFIED;
    }
}
