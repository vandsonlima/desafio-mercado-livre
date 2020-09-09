package com.vandson.desafiomercadolivre.novoProduto.imagens;

import com.vandson.desafiomercadolivre.novoProduto.Produto;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Vandson Lima (vandson.vslima@gmail.com)
 * @since 08/09/2020
 **/
public class ImagemRequest {

    @NotEmpty
    private List<MultipartFile> imagens;

    @Deprecated
    public ImagemRequest() {
    }

    public List<MultipartFile> getImagens() {
        return imagens;
    }

    public void setImagens(List<MultipartFile> imagens) {
        this.imagens = imagens;
    }
}
