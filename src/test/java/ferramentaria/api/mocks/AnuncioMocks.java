package ferramentaria.api.mocks;

import ferramentaria.api.dto.AnuncioDto;
import ferramentaria.api.dto.response.AnuncioResponse;
import ferramentaria.api.entity.Anuncio;

import static ferramentaria.api.mocks.FerramentaMock.ferramentaMock;
import static ferramentaria.api.mocks.UsuarioMocks.usuarioMock;

public class AnuncioMocks {

    public static Anuncio anuncioMock(){
        return new Anuncio(
                usuarioMock(),
                ferramentaMock()
        );
    }

    public static AnuncioDto anuncioDtoMock(){
        return new AnuncioDto(
                usuarioMock(),
                ferramentaMock()
        );
    }

    public static AnuncioResponse anuncioResponseMock(){
        return new AnuncioResponse(
                anuncioMock()
        );
    }
}
