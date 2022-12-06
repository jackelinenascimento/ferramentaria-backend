package ferramentaria.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ferramentaria.api.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	Optional<Usuario> findByEmail(String email);
	
}
