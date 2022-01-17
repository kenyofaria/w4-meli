package br.com.meli.w4.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.meli.w4.entity.Vendedor;
import br.com.meli.w4.repository.VendedorRepository;

@Service
public class VendedorService {

	private VendedorRepository vendedorRepository;
	
	public VendedorService(VendedorRepository vendedorRepository) {
		this.vendedorRepository = vendedorRepository;
	}
	
	public void salva(Vendedor vendedor) {
		this.vendedorRepository.save(vendedor);
	}
	
	public Vendedor get(Integer id) {
		Optional<Vendedor> findById = this.vendedorRepository.findById(id);
		return findById.orElse(new Vendedor());
	}
}
