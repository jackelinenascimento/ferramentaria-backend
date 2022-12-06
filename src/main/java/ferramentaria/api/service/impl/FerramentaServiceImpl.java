package ferramentaria.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ferramentaria.api.dto.FerramentaDto;
import ferramentaria.api.dto.response.FerramentaResponse;
import ferramentaria.api.dto.response.MessageResponseDto;
import ferramentaria.api.entity.Ferramenta;
import ferramentaria.api.exceptions.FerramentaNaoEncontrada;
import ferramentaria.api.exceptions.UsuarioNaoEncontrado;
import ferramentaria.api.repository.FerramentaRepository;

@Service
public class FerramentaServiceImpl {

	@Autowired
	private FerramentaRepository ferramentaRepository;
	
	@Autowired 
	private UsuarioServiceImpl usuarioServiceImpl;
	
	public Page<FerramentaResponse> listarFerramentas(int pagina, int qtd){
		
		Pageable paginacao = PageRequest.of(pagina, qtd);
		Page<Ferramenta> ferramentas = ferramentaRepository.findAll(paginacao);
		
		return FerramentaResponse.converter(ferramentas);
	}

	public MessageResponseDto cadastrarFerramenta(FerramentaDto ferramentaDto) throws UsuarioNaoEncontrado {
		
		usuarioServiceImpl.verificaSeExistePorId(ferramentaDto.getProprietario().getIdUsuario());
		
		Ferramenta ferramenta = ferramentaRepository.save(FerramentaDto.toModel(ferramentaDto));

		return MessageResponseDto.message("Ferramenta salva - ID: " +  ferramenta.getIdFerramenta());
	}

	public FerramentaResponse pesquisaPorId(Long id) throws FerramentaNaoEncontrada {
		Ferramenta ferramenta = verificaSeExistePorId(id);
		return new FerramentaResponse(ferramenta);
	}
	
	public List<FerramentaResponse> persquisaPorProprietarioId(Long id) {
		List<Ferramenta> ferramentas = ferramentaRepository.findByProprietarioIdUsuario(id);
		return FerramentaResponse.converter(ferramentas);
	}

	public Ferramenta verificaSeExistePorId(Long id) throws FerramentaNaoEncontrada {
		return ferramentaRepository.findById(id).orElseThrow(() ->
		new FerramentaNaoEncontrada(id));
	}
}
	