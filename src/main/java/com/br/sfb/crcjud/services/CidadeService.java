package com.br.sfb.crcjud.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.sfb.crcjud.entities.Cidade;
import com.br.sfb.crcjud.repository.CidadeRepository;

@Service
public class CidadeService {
    @Autowired
    private CidadeRepository cidadeRepository;
    public List<Cidade> findAll(){
        return cidadeRepository.findAll();
    }

	public List<Cidade> findAllUf(Long uf) {		
		return cidadeRepository.findByEstadosId(uf);
	}
	public Cidade findById(long id) {		
		return cidadeRepository.findById(id).get();
	}
}
