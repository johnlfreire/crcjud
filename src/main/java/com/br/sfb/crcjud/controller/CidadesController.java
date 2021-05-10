package com.br.sfb.crcjud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.br.sfb.crcjud.entities.Cidade;
import com.br.sfb.crcjud.services.CidadeService;

@Controller
@RequestMapping("cidades")
public class CidadesController {
@Autowired
private CidadeService cidadeService;

    @GetMapping()
    @ResponseBody()
    @Cacheable("cidade")
    public List<Cidade> cidadesFindUf(@RequestParam(name = "id", defaultValue = "-1") Long id) {    	
        return cidadeService.findAllUf(id);
    }
}
