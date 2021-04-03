package com.br.sfb.crcjud.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.sfb.crcjud.entities.Uf;
import com.br.sfb.crcjud.repository.UfRepository;


@Service
public class UfService {
@Autowired
private UfRepository ufRepository;

public List<Uf> findAll(){
	return ufRepository.findAll();
}
public Optional<Uf> findById(Long id) {
	return ufRepository.findById(id);
}
}
