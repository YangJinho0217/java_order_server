package star.inter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import star.inter.dto.SignUpDto;
import star.inter.dto.UserDto;
import star.inter.service.UserService;

@RestController
@RequestMapping("/user")
@Tag(name = "user", description = "유저관련")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "앱 로그인", description = "로그인 시 호출", tags = { "user" })
    @ApiResponses(value = {
            @ApiResponse(description = "OK", responseCode = "200", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = UserDto.Result.class)) }),
    })

    @PostMapping(value = "login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<?> login(@Parameter UserDto.In in) {
        return new ResponseEntity<>(userService.login(in), HttpStatus.OK);
    }

    @Operation(summary = "앱 회원가입", description = "회원가입 시 호출", tags = { "user" })
    @ApiResponses(value = {
            @ApiResponse(description = "OK", responseCode = "200", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = SignUpDto.Result.class)) }),
    })

    @PostMapping(value = "signUp", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<?> signUp(@Parameter SignUpDto.In in) {
        return new ResponseEntity<>(userService.signUp(in), HttpStatus.OK);
    }
}

