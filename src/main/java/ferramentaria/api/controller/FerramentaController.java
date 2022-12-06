package ferramentaria.api.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ferramentaria.api.dto.FerramentaDto;
import ferramentaria.api.dto.response.FerramentaResponse;
import ferramentaria.api.dto.response.MessageResponseDto;
import ferramentaria.api.exceptions.FerramentaNaoEncontrada;
import ferramentaria.api.exceptions.UsuarioNaoEncontrado;
import ferramentaria.api.service.impl.FerramentaServiceImpl;
import ferramentaria.api.service.impl.UsuarioServiceImpl;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/ferramentas")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FerramentaController {
	
	@Autowired
	private FerramentaServiceImpl ferramentaServiceImpl;
	
	@Autowired
	private UsuarioServiceImpl usuarioServiceImpl;
	
	@GetMapping
	public ResponseEntity<Page<FerramentaResponse>> listarFerramentas(@RequestParam int pagina, @RequestParam int qtd) throws FerramentaNaoEncontrada{
		
		Page<FerramentaResponse> listaDeFerramentas = ferramentaServiceImpl.listarFerramentas(pagina, qtd);
		
		for(FerramentaResponse ferramenta : listaDeFerramentas) {
			Long id = ferramenta.getIdFerramenta();
			ferramenta.add(linkTo(methodOn(FerramentaController.class).pesquisarPorId(id)).withSelfRel());
		}
		
		
		return new ResponseEntity<>(listaDeFerramentas, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<FerramentaResponse> pesquisarPorId(@PathVariable Long id) throws FerramentaNaoEncontrada{
		
		return new ResponseEntity<>(ferramentaServiceImpl.pesquisaPorId(id), HttpStatus.OK);
	}
	
	@GetMapping("proprietario/{id}")
	public ResponseEntity<List<FerramentaResponse>> pesquisaPorProprietarioId(@PathVariable Long id) throws UsuarioNaoEncontrado{
		
		return new ResponseEntity<>(ferramentaServiceImpl.persquisaPorProprietarioId(id), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<MessageResponseDto> cadastrarFerramenta(@RequestBody @Valid FerramentaDto ferramentaDto) throws UsuarioNaoEncontrado {
		
		Long id = ferramentaDto.getProprietarioId();
		
		ferramentaDto.setProprietario(usuarioServiceImpl.verificaSeExistePorId(id));
		
		return new ResponseEntity<>(ferramentaServiceImpl.cadastrarFerramenta(ferramentaDto), HttpStatus.CREATED);
	}

}
