package com.project.year.user.controller;

import com.project.year.user.domain.dto.UserJoinRequest;
import com.project.year.user.domain.dto.UserLoginRequest;
import com.project.year.user.service.UserService.UserService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "User Api")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @Operation(
            summary = "Post Sign In Api",
            description = "사용자 회원가입 api 입니다."
    )
    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody UserJoinRequest dto) {
        userService.join(dto.getUserName(), dto.getPassword());

        return ResponseEntity.ok().body("회원가입이 성공 했습니다.");
    }

    @Operation(
            summary = "Post Login Api",
            description = "사용자 로그인 api 입니다."
    )
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginRequest dto) {
        String token = userService.login(dto.getUserName(), dto.getPassword());

        return ResponseEntity.ok().body(token);
    }
}
