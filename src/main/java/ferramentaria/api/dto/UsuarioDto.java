package ferramentaria.api.dto;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import ferramentaria.api.entity.Perfil;
import ferramentaria.api.entity.Usuario;
import ferramentaria.api.entity.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDto {
	
	@NotEmpty
	@Size(min=2, max=100)
    private String nome;
    
    @Email
    private String email;	
	
    @NotEmpty
	@Size(min=6, max=20)
    private String senha;
    
    @Valid
	private EnderecoDto endereco;
	
    @Valid
    private TelefoneDto telefone;
    
	private Status status;
	
	private List<Perfil> perfis;

	@Valid
	private String foto;
    
	public static Usuario toModel(UsuarioDto usuarioDto) {
		return new Usuario(usuarioDto.getNome(),
							usuarioDto.getEmail(),
							usuarioDto.getSenha(),
							EnderecoDto.toModel(usuarioDto.getEndereco()),
							TelefoneDto.toModel(usuarioDto.getTelefone()),
							usuarioDto.getPerfis(),
							usuarioDto.getFoto());
	}
}
