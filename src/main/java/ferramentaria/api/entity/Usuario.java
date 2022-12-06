package ferramentaria.api.entity;

import java.io.Serial;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import ferramentaria.api.entity.enums.Status;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Usuario implements UserDetails{

	@Serial
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;
	
	private LocalDateTime dataCadastro = LocalDateTime.now();
	
	@Column(length=30, nullable=false)
    private String nome;
	
	@Column(length=100, unique=true)
    private String email;

	private String senha;
    	
	@OneToOne(cascade=CascadeType.PERSIST)
	private Endereco endereco;
    
	@OneToOne(cascade=CascadeType.PERSIST)
    private Telefone telefone;
	
	@Enumerated(EnumType.STRING)
	private Status status = Status.ATIVO;

	@ManyToMany(fetch = FetchType.EAGER)
	private List<Perfil> perfis = new ArrayList<>();

	private String foto;
    
    public Usuario(String nome,
    			   String email,
    			   String senha,
    			   Endereco endereco,
    			   Telefone telefone,
    			   List<Perfil> perfis,
				   String foto) {
    	
    	this.nome = nome;
    	this.email = email;
    	this.senha = senha;
    	this.endereco = endereco;
    	this.telefone = telefone;
    	this.perfis = perfis;
		this.foto = foto;
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.perfis;
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.status == Status.ATIVO;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.status == Status.ATIVO;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.status == Status.ATIVO;
	}

	@Override
	public boolean isEnabled() {
		return this.status == Status.ATIVO;
	}
}
