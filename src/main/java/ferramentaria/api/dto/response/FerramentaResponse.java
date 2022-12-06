package ferramentaria.api.dto.response;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.hateoas.RepresentationModel;

import ferramentaria.api.entity.Ferramenta;
import ferramentaria.api.entity.enums.Disponibilidade;
import ferramentaria.api.entity.enums.Modalidade;
import ferramentaria.api.entity.enums.Status;
import ferramentaria.api.entity.enums.Tensao;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FerramentaResponse extends RepresentationModel<UsuarioResponse> {

    private Long idFerramenta;	
	private String nome;
	private String descricao;
	private Tensao tensao;
	private Modalidade modalidade;
	private Disponibilidade disponibilidade;
	private Long proprietarioId;
	private Status status;

	private String foto;
	
	public FerramentaResponse(Ferramenta ferramenta) {
		this.idFerramenta = ferramenta.getIdFerramenta();
		this.nome = ferramenta.getNome();
		this.descricao = ferramenta.getDescricao();
		this.tensao = ferramenta.getTensao();
		this.modalidade = ferramenta.getModalidade();
		this.disponibilidade = ferramenta.getDisponibilidade();
		this.status = ferramenta.getStatus();
		this.proprietarioId = ferramenta.getProprietario().getIdUsuario();
		this.foto = ferramenta.getFoto();
	}

	public static Page<FerramentaResponse> converter(Page<Ferramenta> ferramentas) {
		return ferramentas.map(FerramentaResponse::new);
	}

	public static List<FerramentaResponse> converter(List<Ferramenta> ferramentas) {
		return ferramentas.stream().map(FerramentaResponse::new).collect(Collectors.toList());
	}
}
