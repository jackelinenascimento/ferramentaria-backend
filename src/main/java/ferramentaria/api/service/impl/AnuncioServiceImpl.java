package ferramentaria.api.service.impl;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import ferramentaria.api.service.AnuncioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ferramentaria.api.dto.AnuncioDto;
import ferramentaria.api.dto.response.AnuncioResponse;
import ferramentaria.api.dto.response.MessageResponseDto;
import ferramentaria.api.entity.Anuncio;
import ferramentaria.api.entity.enums.Status;
import ferramentaria.api.exceptions.AnuncioNaoEncontrado;
import ferramentaria.api.repository.AnuncioRepository;

@Service
public class AnuncioServiceImpl implements AnuncioService {

	@Autowired
	private AnuncioRepository anuncioRepository;
	
	public Page<AnuncioResponse> listarAnuncios(int pagina, int qtd) {
		
		Pageable paginacao = PageRequest.of(pagina, qtd);
		
		Page<Anuncio> anuncios = anuncioRepository.findAll(paginacao);
		return AnuncioResponse.converter(anuncios);
	}

	public AnuncioResponse pesquisarPorId(Long id) throws AnuncioNaoEncontrado {
		Anuncio anuncio = verificaSeExistePorId(id);
		return new AnuncioResponse(anuncio);
	}

	public MessageResponseDto cadastrarAnuncio(@Valid AnuncioDto anuncioDto) {
		
		if(anuncioDto.getFerramenta().getStatus() == Status.INATIVO) {
			throw new IllegalArgumentException("Ferramenta com status INATIVO");
		}
		
		Optional<Anuncio> ferramenta = anuncioRepository.findByFerramentaIdFerramenta(anuncioDto.getFerramenta().getIdFerramenta());
		
		if(ferramenta.isPresent()) {
			throw new IllegalArgumentException("Ferramenta já publicada");
		}
		
		Anuncio anuncioSalvo = anuncioRepository.save(AnuncioDto.toModel(anuncioDto));
		return MessageResponseDto.message("Anuncio salvo - ID: " +  anuncioSalvo.getIdAnuncio());
	}

	public Anuncio verificaSeExistePorId(Long id) throws AnuncioNaoEncontrado {
		return anuncioRepository.findById(id).orElseThrow(() ->
				new AnuncioNaoEncontrado(id));
	}

	public List<AnuncioResponse> persquisaPorProprietarioId(Long id) {
		List<Anuncio> anuncios = anuncioRepository.findByProprietarioIdUsuario(id);
		return AnuncioResponse.converter(anuncios);
	}
}
	