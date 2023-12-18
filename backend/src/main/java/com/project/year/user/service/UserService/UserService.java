package com.project.year.user.service.UserService;

import com.project.year.user.domain.dao.User;
import com.project.year.user.exception.UserErrorCode;
import com.project.year.user.exception.UserException;
import com.project.year.user.repository.UserRepository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    public String join(String userName, String password) {

        // userName 중복 check
        userRepository.findByUserName(userName)
                .ifPresent(user -> {
                    throw new UserException(UserErrorCode.USERNAME_DUPLICATED, userName + " 아이디는 이미 존재합니다.");
                });

        // 저장
        User user = User.builder()
                .userName(userName)
                .password(encoder.encode(password))
                .build();
        userRepository.save(user);

        return "SUCCESS";
    }
}
