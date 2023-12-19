package com.project.year.user.service.UserService;

import com.project.year.user.domain.dao.User;
import com.project.year.user.exception.UserErrorCode;
import com.project.year.user.exception.UserException;
import com.project.year.user.repository.UserRepository.UserRepository;
import com.project.year.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    @Value("${jwt.token.secret}")
    private String key;
    private Long expireTimeMs = 1000 * 60 * 60l; // 1시간

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

    public String login(String userName, String password) {
        // userName 없을 때
        User selectedUser = userRepository.findByUserName(userName)
                .orElseThrow(() -> new UserException(UserErrorCode.USERNAME_NOTFOUND, userName + "이 없습니다."));

        // password 틀렸을 때
        if (encoder.matches(selectedUser.getPassword(), password)) {
            throw new UserException(UserErrorCode.INVALID_PASSWORD, "패스워드를 잘못 입력 했습니다.");
        }

        // token 발행
        String token = JwtTokenUtil.createToken(selectedUser.getUserName(), key, expireTimeMs);

        return token;
    }
}
