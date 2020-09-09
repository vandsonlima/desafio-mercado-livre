package com.vandson.desafiomercadolivre.novoProduto.imagens;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Vandson Lima (vandson.vslima@gmail.com)
 * @since 08/09/2020
 **/
@Profile("!prod")
@Component
public class UploadFileFake implements UploadFile{
    public static final String URL_FAKE= "https://urlfake.com/files/%s";
    @Override
    public Set<String> sendFiles(List<MultipartFile> multipartFiles) {
        return multipartFiles.stream().map( file -> String.format(URL_FAKE, file.getOriginalFilename())).collect(Collectors.toSet());
    }
}
