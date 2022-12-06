package ferramentaria.api.service;

import ferramentaria.api.dto.FerramentaDto;
import ferramentaria.api.dto.response.FerramentaResponse;
import ferramentaria.api.dto.response.MessageResponseDto;
import ferramentaria.api.entity.Ferramenta;
import org.springframework.data.domain.Page;

import java.util.List;

public interface FerramentaService {

    public Page<FerramentaResponse> listarFerramentas(int pagina, int qtd);

    public MessageResponseDto cadastrarFerramenta(FerramentaDto ferramentaDto);

    public FerramentaResponse pesquisaPorId(Long id);

    public List<FerramentaResponse> persquisaPorProprietarioId(Long id);

    public Ferramenta verificaSeExistePorId(Long id);
}
