package com.br.sfb.crcjud.specification;


import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.br.sfb.crcjud.entities.Entidade;
import com.br.sfb.crcjud.entities.Usuario;
@Component
public class UsuarioSpecification  {


    public static Specification<Usuario> hasFirstName(String firstname){
    	
        return ((root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.or(
    				criteriaBuilder.like(root.get("nome"), "%" + firstname + "%"),
    				criteriaBuilder.like(root.get("email"), "%" + firstname + "%"),
    				criteriaBuilder.like(root.get("cpf"), firstname)
    				);
        });
    }
 public static Specification<Usuario> hasCpfOrEmail(String cpf,String email){
    	
        return ((root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.or(
    				criteriaBuilder.equal(root.get("cpf"), cpf),
    				criteriaBuilder.equal(root.get("email"), email)
    				);
        });
    }
 public static Specification<Usuario> hasEntidade(Entidade entidade){
 	
     return ((root, criteriaQuery, criteriaBuilder) -> {
         return criteriaBuilder.or(
 				criteriaBuilder.equal(root.get("entidade"), entidade) 				
 				);
     });
 }

}
