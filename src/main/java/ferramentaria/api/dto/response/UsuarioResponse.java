package ferramentaria.api.dto.response;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.hateoas.RepresentationModel;

import ferramentaria.api.entity.Usuario;
import ferramentaria.api.entity.enums.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UsuarioResponse extends RepresentationModel<UsuarioResponse> {
	
    private Long idUsuario;

	private String nome;

	private String email;

	private Status status;

	private EnderecoResponse endereco;

	private TelefoneResponse telefone;

	private String foto;

    public UsuarioResponse(Usuario usuario) {
    	this.idUsuario = usuario.getIdUsuario();
    	this.nome = usuario.getNome();
    	this.email = usuario.getEmail();
    	this.status = usuario.getStatus();
    	this.endereco = new EnderecoResponse(usuario.getEndereco());
    	this.telefone = new TelefoneResponse(usuario.getTelefone());
		this.foto = usuario.getFoto();
    }

    public static Page<UsuarioResponse> converter(Page<Usuario> usuarios) {
		return usuarios.map(UsuarioResponse::new);
	}
    
	public static List<UsuarioResponse> converter(List<Usuario> usuarios) {
		return usuarios.stream().map(UsuarioResponse::new).collect(Collectors.toList());
	}

}
