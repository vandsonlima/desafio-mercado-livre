package com.vandson.desafiomercadolivre.novaCategoria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Vandson Lima (vandson.vslima@gmail.com)
 * @since 18/08/2020
 **/
@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
