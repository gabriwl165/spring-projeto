package curso.springboot.repository;

import java.util.List;

import org.hibernate.criterion.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import curso.springboot.model.Pessoa;

@Repository
@Transactional
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

	@Query("select p from Pessoa p where p.nome like %:paramNome%")
	List<Pessoa> findPessoaByName(@Param("paramNome") String nome);
	
	@Query("select p from Pessoa p where p.nome like :paramNome and p.sexo = :paramSexo")
	List<Pessoa> findPessoaByNameSexo(@Param("paramNome") String nome, @Param("paramSexo") String sexo);
	
	@Query("select p from Pessoa p where p.sexo = ?1 ")
	List<Pessoa> findPessoaBySexo(String sexo);
	
	default Page<Pessoa> findPessoaByNamePage(String nome, Pageable pageable){
		
		Pessoa pessoa = new Pessoa();
		pessoa.setNome(nome);
		ExampleMatcher exampleMatcher = ExampleMatcher.matchingAny().withMatcher("nome", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
		
		// Une o Objeto com o valor e a configuração para consultar
		org.springframework.data.domain.Example<Pessoa> example = org.springframework.data.domain.Example.of(pessoa, exampleMatcher);
		Page<Pessoa> pessoas = findAll(example, pageable);
		
		return pessoas;
	}
	
	default Page<Pessoa> findPessoaBySexoPage(String nome, String sexo, Pageable pageable){
		
		Pessoa pessoa = new Pessoa();
		pessoa.setNome(nome);
		pessoa.setSexo(sexo);
		ExampleMatcher exampleMatcher = ExampleMatcher.matchingAny()
				.withMatcher("nome", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
				.withMatcher("sexo", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
		
		// Une o Objeto com o valor e a configuração para consultar
		org.springframework.data.domain.Example<Pessoa> example = org.springframework.data.domain.Example.of(pessoa, exampleMatcher);
		Page<Pessoa> pessoas = findAll(example, pageable);
		
		return pessoas;
	}
	
}
 