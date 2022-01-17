package br.com.meli.w4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.meli.w4.entity.Vendedor;

@Repository
public interface VendedorRepository extends JpaRepository<Vendedor, Integer>{

}
