package ferramentaria.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import ferramentaria.api.configs.security.TokenService;
import ferramentaria.api.dto.LoginDto;
import ferramentaria.api.dto.TokenDto;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AutenticacaoController {
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private TokenService tokenService;

	@PostMapping
	public ResponseEntity<TokenDto> autenticar(@RequestBody @Valid LoginDto loginDto){
		
		UsernamePasswordAuthenticationToken dadosLogin = loginDto.converter();
		
		try {			
			Authentication auth = authManager.authenticate(dadosLogin);
			String token = tokenService.gerarToken(auth);
			return ResponseEntity.ok(new TokenDto(token, "Bearer"));
			
		} catch (Exception e) {			
			return ResponseEntity.badRequest().body(new TokenDto("Usuario não encontrado"));
		}
	}
}
