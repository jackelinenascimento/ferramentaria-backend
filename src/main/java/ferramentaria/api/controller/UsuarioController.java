package ferramentaria.api.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ferramentaria.api.dto.UsuarioDto;
import ferramentaria.api.dto.response.MessageResponseDto;
import ferramentaria.api.dto.response.UsuarioResponse;
import ferramentaria.api.exceptions.UsuarioNaoEncontrado;
import ferramentaria.api.service.impl.UsuarioServiceImpl;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {
	
	@Autowired
	private UsuarioServiceImpl usuarioServiceImpl;
	
	@GetMapping
	public ResponseEntity<Page<UsuarioResponse>> listaUsuarios(@RequestParam int pagina, @RequestParam int qtd) throws UsuarioNaoEncontrado{
		
		Page<UsuarioResponse> listaDeUsuarios = usuarioServiceImpl.listarUsuarios(pagina, qtd);
		
		for(UsuarioResponse usuario: listaDeUsuarios) {		
				usuario.add(linkTo(methodOn(UsuarioController.class).pesquisarPorId(usuario.getIdUsuario())).withSelfRel());
				usuario.add(linkTo(methodOn(UsuarioController.class).pesquisarPorEmail(usuario.getEmail())).withSelfRel());
		}
		
		return new ResponseEntity<>(listaDeUsuarios, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioResponse> pesquisarPorId(@PathVariable Long id) throws UsuarioNaoEncontrado {
		
		return new ResponseEntity<>(usuarioServiceImpl.pesquisarPorId(id), HttpStatus.OK);
	}
	
	@GetMapping("/email/{email}")
	public ResponseEntity<UsuarioResponse> pesquisarPorEmail(@PathVariable String email) throws UsuarioNaoEncontrado {

		return new ResponseEntity<>(usuarioServiceImpl.pesquisarPorEmail(email), HttpStatus.OK);
	}
	
	@PostMapping
    public ResponseEntity<MessageResponseDto> cadastrarUsuario(@RequestBody @Valid UsuarioDto usuarioDto){
        
        return new ResponseEntity<>(usuarioServiceImpl.cadastrarUsuario(usuarioDto), HttpStatus.CREATED);
        
    }
	
	@PutMapping("/{id}")
	public ResponseEntity<MessageResponseDto> atualizarDados(@RequestBody @Valid UsuarioDto usuarioDto) throws UsuarioNaoEncontrado{
		
		return new ResponseEntity<>(usuarioServiceImpl.atualizarDadosUsuario(usuarioDto), HttpStatus.OK);
	}
	
}	
