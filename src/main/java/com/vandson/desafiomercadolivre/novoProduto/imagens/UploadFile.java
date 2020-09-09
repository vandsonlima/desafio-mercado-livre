package com.vandson.desafiomercadolivre.novoProduto.imagens;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

/**
 * @author Vandson Lima (vandson.vslima@gmail.com)
 * @since 08/09/2020
 **/
public interface UploadFile {

    public Set<String> sendFiles(List<MultipartFile> multipartFiles);
}
