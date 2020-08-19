package com.vandson.desafiomercadolivre.compartilhado;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author Vandson Lima (vandson.vslima@gmail.com)
 * @since 27/07/2020
 **/
@Documented
@Constraint( validatedBy = {ExistsIdValidator.class})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistsId {

    String message() default "{com.vandson.desafiocasadocodigo.validations.idnotfoud}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String fieldName() default "id";

    Class<?> domainClass();

}
