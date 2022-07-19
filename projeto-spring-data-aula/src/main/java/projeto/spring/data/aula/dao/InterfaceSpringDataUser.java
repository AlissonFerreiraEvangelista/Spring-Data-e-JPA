package projeto.spring.data.aula.dao;

import java.util.List;

import javax.persistence.LockModeType;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import projeto.spring.data.aula.model.UsuarioSpringData;

@Repository
public interface InterfaceSpringDataUser extends CrudRepository<UsuarioSpringData, Long> {

	@Query(value="SELECT p FROM UsuarioSpringData p WHERE p.nome like %?1%")
	public List<UsuarioSpringData> buscaPorNome(String nome);
	
	@Lock(LockModeType.READ)
	@Query(value="SELECT p FROM UsuarioSpringData p WHERE p.nome = :paranome")
	public UsuarioSpringData buscaPorNomeParametro(@Param("paranome")String paranome);
	
	@Modifying
	@Transactional
	@Query(value="DELETE FROM UsuarioSpringData u WHERE u.nome = ?1")
	public void deletePorNome(String nome);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE UsuarioSpringData u SET u.email = ?1 WHERE u.nome = ?2")
	public void AtualizarPorEmail(String email, String nome);
}
