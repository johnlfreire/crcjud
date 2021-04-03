package com.br.sfb.crcjud.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.br.sfb.crcjud.entities.Perfil;
import com.br.sfb.crcjud.repository.PerfilRepository;
@Component
public class PerfilConverter implements Converter<String, Perfil>{

	@Autowired
	private PerfilRepository  perfilRepository;
	@Override
	public Perfil convert(String id) {
		return perfilRepository.findById(Long.parseLong(id)).get();
	}
}
