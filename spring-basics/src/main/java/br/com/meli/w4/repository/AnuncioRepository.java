package br.com.meli.w4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.meli.w4.entity.Anuncio;

@Repository
public interface AnuncioRepository extends JpaRepository<Anuncio, Integer>{


}
