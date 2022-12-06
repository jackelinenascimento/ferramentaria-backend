package ferramentaria.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ferramentaria.api.entity.Anuncio;

public interface AnuncioRepository extends JpaRepository<Anuncio, Long> {

	Optional<Anuncio> findByFerramentaIdFerramenta(Long idFerramenta);

	List<Anuncio> findByProprietarioIdUsuario(Long id);
}
