package ferramentaria.api.service;

import ferramentaria.api.dto.UsuarioDto;
import ferramentaria.api.dto.response.MessageResponseDto;
import ferramentaria.api.dto.response.UsuarioResponse;
import ferramentaria.api.entity.Usuario;
import org.springframework.data.domain.Page;

public interface UsuarioService {

    public Page<UsuarioResponse> listarUsuarios(int pagina, int qtd);

    public UsuarioResponse pesquisarPorId(Long id);

    public UsuarioResponse pesquisarPorEmail(String email);

    public MessageResponseDto cadastrarUsuario(UsuarioDto usuarioDto);

    public MessageResponseDto atualizarDadosUsuario(UsuarioDto usuarioDto);

    public Usuario verificaSeExistePorId(Long id);

}
