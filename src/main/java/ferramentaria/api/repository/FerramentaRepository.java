package ferramentaria.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ferramentaria.api.entity.Ferramenta;

public interface FerramentaRepository extends JpaRepository<Ferramenta, Long>{

	List<Ferramenta> findByProprietarioIdUsuario(Long id);
}
