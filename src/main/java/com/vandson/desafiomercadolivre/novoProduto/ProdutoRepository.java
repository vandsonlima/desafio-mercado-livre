package com.vandson.desafiomercadolivre.novoProduto;

import com.vandson.desafiomercadolivre.novoUsuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Vandson Lima (vandson.vslima@gmail.com)
 * @since 08/09/2020
 **/
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    Produto findProdutoByProprietarioAndId(Usuario proprietario, Long id);

}
