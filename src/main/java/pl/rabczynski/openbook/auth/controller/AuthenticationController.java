package pl.rabczynski.openbook.auth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.rabczynski.openbook.auth.dto.request.AuthenticationRequest;
import pl.rabczynski.openbook.auth.dto.response.AuthenticationResponse;
import pl.rabczynski.openbook.auth.service.JwtUserDetailsService;
import pl.rabczynski.openbook.auth.util.JwtUtil;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtUserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    ResponseEntity<?> authenticateUser(@RequestBody AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        var userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        return ResponseEntity.ok(new AuthenticationResponse(jwtUtil.generateToken(userDetails)));
    }
}
