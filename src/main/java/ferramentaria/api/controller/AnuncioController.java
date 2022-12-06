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

import ferramentaria.api.dto.AnuncioDto;
import ferramentaria.api.dto.mapper.AnuncioMapper;
import ferramentaria.api.dto.response.AnuncioResponse;
import ferramentaria.api.dto.response.MessageResponseDto;
import ferramentaria.api.entity.Ferramenta;
import ferramentaria.api.entity.Usuario;
import ferramentaria.api.exceptions.AnuncioNaoEncontrado;
import ferramentaria.api.exceptions.FerramentaNaoEncontrada;
import ferramentaria.api.exceptions.UsuarioNaoEncontrado;
import ferramentaria.api.service.impl.AnuncioServiceImpl;
import ferramentaria.api.service.impl.FerramentaServiceImpl;
import ferramentaria.api.service.impl.UsuarioServiceImpl;

@RestController
@RequestMapping("/anuncios")	
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AnuncioController {

	@Autowired
	private AnuncioServiceImpl anuncioServiceImpl;
	
	@Autowired
	private UsuarioServiceImpl usuarioServiceImpl;
	
	@Autowired
	private FerramentaServiceImpl ferramentaServiceImpl;
	
	@GetMapping
	public ResponseEntity<Page<AnuncioResponse>> listarAnuncios(@RequestParam int pagina, @RequestParam int qtd) throws AnuncioNaoEncontrado{
		
		Page<AnuncioResponse> listaDeAnuncios = anuncioServiceImpl.listarAnuncios(pagina, qtd);
		
		for(AnuncioResponse anuncio : listaDeAnuncios) {
			anuncio.add(linkTo(methodOn(AnuncioController.class).pesquisarPorId(anuncio.getIdAnuncio())).withSelfRel());
		}
		
		return new ResponseEntity<>(listaDeAnuncios, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AnuncioResponse> pesquisarPorId(@PathVariable Long id) throws AnuncioNaoEncontrado {
		
		return new ResponseEntity<>(anuncioServiceImpl.pesquisarPorId(id), HttpStatus.OK);

	}
	
	@GetMapping("/proprietario/{id}")
	public ResponseEntity<List<AnuncioResponse>> pesquisaPorProprietarioId(@PathVariable Long id) throws UsuarioNaoEncontrado{
		
		return new ResponseEntity<>(anuncioServiceImpl.persquisaPorProprietarioId(id), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<MessageResponseDto> cadastrarAnuncio(@RequestBody @Valid AnuncioMapper anuncioMapper) throws UsuarioNaoEncontrado, FerramentaNaoEncontrada {
				
		Usuario proprietario = usuarioServiceImpl.verificaSeExistePorId(anuncioMapper.getProprietarioId());
		Ferramenta ferramenta = ferramentaServiceImpl.verificaSeExistePorId(anuncioMapper.getFerramentaId());
		
		AnuncioDto anuncioDto = new AnuncioDto(proprietario, ferramenta);

		return new ResponseEntity<>(anuncioServiceImpl.cadastrarAnuncio(anuncioDto), HttpStatus.CREATED);
	}
}
