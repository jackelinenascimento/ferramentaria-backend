package ferramentaria.api.mocks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import ferramentaria.api.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import ferramentaria.api.dto.EnderecoDto;
import ferramentaria.api.dto.TelefoneDto;
import ferramentaria.api.dto.UsuarioDto;
import ferramentaria.api.dto.response.UsuarioResponse;
import ferramentaria.api.entity.enums.Status;

public class UsuarioMocks {

	static String nome = "Ana";
	static String email = "ana@email.com";
	static String senha = "123456";
	static String logradouro = "Logradouro";
	static String complemento = "Complemento";
	static String bairro = "Bairro";
	static String cidade = "Cidade";
	static String uf = "UF";
	static String cep = "12345-678";
	static String ddd = "000";
	static String numero = "12345-6789";
	static String url = "https://foto";

	public static Endereco enderecoMock() {
		return new Endereco(logradouro, complemento, bairro, cidade, uf, cep);
	}

	public static EnderecoDto enderecoDtoMock() {
		return new EnderecoDto(logradouro, complemento, bairro, cidade, uf, cep);
	}

	public static Telefone telefoneMock() {
		return new Telefone(ddd, numero);
	}

	public static TelefoneDto telefoneDtoMock() {
		return new TelefoneDto(ddd, numero);
	}

	public static List<Perfil> perfisMock() {
		return new ArrayList<>();
	}

	public static Foto fotosMock(){ return new Foto(url);}

	public static FotoDto fotoDtoMock(){ return new FotoDto(url); }

	public static Usuario usuarioMock() {
		return new Usuario(nome, email, senha, enderecoMock(), telefoneMock(), perfisMock(), fotosMock());
	}

	public static UsuarioDto usuarioDtoMock() {
		return new UsuarioDto(nome, email, senha, enderecoDtoMock(), telefoneDtoMock(), Status.ATIVO, perfisMock(), fotoDtoMock());
	}

	public static UsuarioResponse usuarioResponseMock() {
		return new UsuarioResponse(usuarioMock());
	}

	public static Optional<Usuario> usuarioOptionalMock() {
		return Optional.of(usuarioMock());
	}

	public static Page<Usuario> usuarioPage() {
		List<Usuario> usuarios = Arrays.asList(usuarioMock());
		return new PageImpl<>(usuarios, PageRequest.of(0, 10), 400);
	}
}
