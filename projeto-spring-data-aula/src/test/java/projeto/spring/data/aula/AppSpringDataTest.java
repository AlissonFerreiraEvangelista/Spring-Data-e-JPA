package projeto.spring.data.aula;

import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import projeto.spring.data.aula.dao.InterfaceSpringDataUser;
import projeto.spring.data.aula.dao.InterfaceTelefone;
import projeto.spring.data.aula.model.Telefone;
import projeto.spring.data.aula.model.UsuarioSpringData;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/spring-config.xml"})
public class AppSpringDataTest {
	@Autowired
	InterfaceSpringDataUser interfaceSpringDataUser;
	
	@Autowired
	InterfaceTelefone interfaceTelefone;

	@Test
	public void testInsert() {
		UsuarioSpringData usuarioSpringData = new UsuarioSpringData();
		usuarioSpringData.setEmail("teste@gmail.com");
		usuarioSpringData.setNome("Aline");
		usuarioSpringData.setLogin("admin");
		usuarioSpringData.setSenha("admin");
		usuarioSpringData.setIdade(10);
		interfaceSpringDataUser.save(usuarioSpringData);
		
	}
	
	@Test
	public void TestConsulta() {
		
		Optional<UsuarioSpringData> usuarioSpringData = interfaceSpringDataUser.findById(2L);
		
		System.out.println(usuarioSpringData.get().getNome());
		System.out.println(usuarioSpringData.get().getIdade());
		System.out.println(usuarioSpringData.get().getLogin());
		System.out.println("-----------------------------");
		
		for (Telefone telefone : usuarioSpringData.get().getTelefones()) {
			System.out.println(telefone.getTipo());
			System.out.println(telefone.getNumero());
			System.out.println("-----------------------------");
		}
		assertNotNull(usuarioSpringData);
	}
	
	//@Test
	public void testConsultaTodos() {
		Iterable<UsuarioSpringData> lista = interfaceSpringDataUser.findAll();
		for (UsuarioSpringData usuarioSpringData : lista) {
			System.out.println(usuarioSpringData.getId());
			System.out.println(usuarioSpringData.getNome());
			System.out.println(usuarioSpringData.getEmail());
			System.out.println(usuarioSpringData.getIdade());
			System.out.println(usuarioSpringData.getLogin());
			System.out.println("-----------------------------");
		}		
	}
	
	//@Test
	public void testAtualizar() {
		
		Optional<UsuarioSpringData> usuarioSpringData = interfaceSpringDataUser.findById(3L);		
		UsuarioSpringData user = usuarioSpringData.get();
		user.setNome("Nome Atualizado");
		interfaceSpringDataUser.save(user);
				
	}
	//@Test
	public void testDelete() {
		interfaceSpringDataUser.deleteById(4L);
	}
	
	//@Test
	public void testConsultaNome() {
		List<UsuarioSpringData> list = interfaceSpringDataUser.buscaPorNome("Alis");
		for (UsuarioSpringData usuarioSpringData : list) {
			System.out.println(usuarioSpringData.getId());
			System.out.println(usuarioSpringData.getNome());
			System.out.println(usuarioSpringData.getEmail());
			System.out.println(usuarioSpringData.getIdade());
			System.out.println(usuarioSpringData.getLogin());
			System.out.println("-----------------------------");
		}
	}
	
	//@Test
	public void testConsultaNomeParametro() {
		UsuarioSpringData usuarioSpringData = interfaceSpringDataUser.buscaPorNomeParametro("Alisson");
	
			System.out.println(usuarioSpringData.getId());
			System.out.println(usuarioSpringData.getNome());
			System.out.println(usuarioSpringData.getEmail());
			System.out.println(usuarioSpringData.getIdade());
			System.out.println(usuarioSpringData.getLogin());
			System.out.println("-----------------------------");	
	}
	
	//@Test
	public void testDeletePorNome() {
		interfaceSpringDataUser.deletePorNome("Aline");
	}
	
	//@Test
	public void testAtualizaPorEmail() {
		interfaceSpringDataUser.AtualizarPorEmail("emailAlterado@gmail.com", "Alisson");
	}
	
	//@Test
	public void testInsertTelefone() {
		Optional<UsuarioSpringData> usuarioSpringData = interfaceSpringDataUser.findById(2L);
		Telefone telefone = new Telefone();	
		telefone.setTipo("Casa");
		telefone.setNumero("3232323");
		telefone.setUsuarioSpringData(usuarioSpringData.get());
		interfaceTelefone.save(telefone);
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
