package com.dino.security.auth;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dino.security.config.JwtService;
import com.dino.security.domain.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
	
	private final AuthenticationService service;
	private final JwtService jwtService;

	@PostMapping(value = "/register", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<?> register(@RequestParam("user") String user, 
			@RequestParam("idCard") String idCard, @RequestParam("criminalRecord") String criminalRecord) {
		ObjectMapper objectMapper = new ObjectMapper();
	    try {
	        RegisterRequest registerRequest = objectMapper.readValue(user, RegisterRequest.class);
	        return ResponseEntity.ok(service.register(registerRequest, idCard, criminalRecord));
	    } catch (Exception e) {
	    	return ResponseEntity.badRequest().body("Invalid register request.");
	    }
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> authenticate(
			@RequestBody AuthenticationRequest request) {
		return ResponseEntity.ok(service.authenticate(request));
	}
	
	@GetMapping("/validate")
	public ResponseEntity<?> validateToken(@RequestParam String token, @AuthenticationPrincipal User user) {
		boolean validToken = jwtService.isTokenExpired(token);
		return ResponseEntity.ok(!validToken);
	}
}
