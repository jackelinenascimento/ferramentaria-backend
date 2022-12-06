package ferramentaria.api.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import ferramentaria.api.entity.enums.Disponibilidade;
import ferramentaria.api.entity.enums.Modalidade;
import ferramentaria.api.entity.enums.Status;
import ferramentaria.api.entity.enums.Tensao;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Ferramenta {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFerramenta;
	
	private LocalDateTime dataCadastro = LocalDateTime.now();
	
	@Column(length=30, nullable=true, unique=false)
    private String nome;
	
	@Enumerated(EnumType.STRING)
	private Tensao tensao;

	@Enumerated(EnumType.STRING)
	private Modalidade modalidade;
	
	@Enumerated(EnumType.STRING)
	private Disponibilidade disponibilidade;

	@Column(length=200, nullable=true, unique=false)
    private String descricao;
	
	@ManyToOne
	private Usuario proprietario;
	
	@Enumerated(EnumType.STRING)
	private Status status = Status.ATIVO;

	private String foto;
	
	public Ferramenta(String nome,
					  Tensao tensao,
					  Modalidade modalidade,
					  Disponibilidade disponibilidade,
					  String descricao,
					  Usuario proprietario,
					  String foto) {
		this.nome = nome;
		this.tensao = tensao;
		this.modalidade = modalidade;
		this.disponibilidade = disponibilidade;
		this.descricao = descricao;
		this.proprietario = proprietario;
		this.foto = foto;
	}
}
