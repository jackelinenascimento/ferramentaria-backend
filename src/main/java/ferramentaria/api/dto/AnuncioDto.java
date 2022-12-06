package ferramentaria.api.dto;

import javax.validation.constraints.NotEmpty;

import ferramentaria.api.entity.Anuncio;
import ferramentaria.api.entity.Ferramenta;
import ferramentaria.api.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnuncioDto {
	
	@NotEmpty	
    private Usuario proprietario;
	
	@NotEmpty
	private Ferramenta ferramenta;
	
	public static Anuncio toModel(AnuncioDto anuncioDto) {
		return new Anuncio(anuncioDto.getProprietario(),
						   anuncioDto.getFerramenta());
	}
}


