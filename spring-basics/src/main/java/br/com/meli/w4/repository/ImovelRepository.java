package br.com.meli.w4.repository;

import java.io.IOException;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.meli.w4.entity.Imovel;

@Repository
public interface ImovelRepository extends JpaRepository<Imovel, Integer>{

	
	
	
//	@Override
//	public Imovel salva(Imovel t) throws IOException {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<Imovel> listagem() throws IOException {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Imovel get(Integer id) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
