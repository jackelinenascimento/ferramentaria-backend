package ferramentaria.api.service.impl;

import ferramentaria.api.dto.response.MessageResponseDto;
import ferramentaria.api.dto.response.UsuarioResponse;
import ferramentaria.api.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;

import static ferramentaria.api.mocks.UsuarioMocks.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
class UsuarioServiceImplTest {

    @InjectMocks
    private UsuarioServiceImpl usuarioServiceImpl;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private PasswordEncoder encoder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void quandoListarUsuariosEntaoRetorneUmaListaDeUsuarios() {

        when(usuarioRepository.findAll((Pageable) any())).thenReturn(usuarioPage());
        Page<UsuarioResponse> response = usuarioServiceImpl.listarUsuarios(0,10);

        assertEquals(PageImpl.class, response.getClass());
        assertNotNull(response);
    }

    @Test
    void quandoPesquisarPorIdRetorneUmUsuario() {

        when(usuarioRepository.findById(anyLong())).thenReturn(usuarioOptionalMock());
        UsuarioResponse response = usuarioServiceImpl.pesquisarPorId(1L);

        assertEquals(UsuarioResponse.class, response.getClass());
        assertNotNull(response);
    }

    @Test
    void quandoPesquisarPorEmailRetorneUmUsuario() {

        when(usuarioRepository.findByEmail(any())).thenReturn(usuarioOptionalMock());
        UsuarioResponse response = usuarioServiceImpl.pesquisarPorEmail("ana@email.com");

        assertEquals(UsuarioResponse.class, response.getClass());
        assertNotNull(response);
    }

    @Test
    void quandoCadastrarUsuarioRetorneUmaMessageResponse() {

        when(usuarioRepository.save(any())).thenReturn(usuarioMock());
        MessageResponseDto response = usuarioServiceImpl.cadastrarUsuario(usuarioDtoMock());

        assertEquals(MessageResponseDto.class, response.getClass());
        assertNotNull(response);
    }

    @Test
    void quandoAtualizarDadosUsuarioRetorneUmaMessageResponse() {

        when(usuarioRepository.save(any())).thenReturn(usuarioMock());
        MessageResponseDto response = usuarioServiceImpl.cadastrarUsuario(usuarioDtoMock());

        assertEquals(MessageResponseDto.class, response.getClass());
        assertNotNull(response);
    }
}