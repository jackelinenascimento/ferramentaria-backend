package ferramentaria.api.service;

import ferramentaria.api.dto.AnuncioDto;
import ferramentaria.api.dto.response.AnuncioResponse;
import ferramentaria.api.dto.response.MessageResponseDto;
import ferramentaria.api.entity.Anuncio;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AnuncioService {

    public Page<AnuncioResponse> listarAnuncios(int pagina, int qtd);

    public AnuncioResponse pesquisarPorId(Long id);

    public MessageResponseDto cadastrarAnuncio(AnuncioDto anuncioDto);

    public Anuncio verificaSeExistePorId(Long id);

    public List<AnuncioResponse> persquisaPorProprietarioId(Long id);
}
