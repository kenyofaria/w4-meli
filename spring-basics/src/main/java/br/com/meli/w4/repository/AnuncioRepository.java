package br.com.meli.w4.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.meli.w4.entity.Anuncio;
import br.com.meli.w4.entity.Caracteristica;

@Repository
public interface AnuncioRepository extends JpaRepository<Anuncio, Integer>{


	Page<Anuncio> findByVendedor_Id(Integer id, Pageable pageable);
	
//	@Query(value = "select c.nome, ac.valor from AnuncioCaracteristica ac, Anuncio a, Caracteristica c "
//			+ " where ac.anuncio.id = a.id "
//			+ " and ac.caracteristica.id = c.id "
//			+ " and a.id = :idAnuncio ")
	@Query(value = "select c.nome, ac.valor "
			+ " from anuncio_caracteristicas ac, caracteristica c, anuncio a "
			+ " where ac.anuncio_id = a.id "
			+ " and ac.caracteristica_id = c.id "
			+ " and a.id = :idAnuncio ", nativeQuery = true)
	List<CaracteristicaTmp> listaDeCaracteristicas(@Param("idAnuncio") Integer id);
	public interface CaracteristicaTmp{
		String getNome();
		String getValor();
	}

}
