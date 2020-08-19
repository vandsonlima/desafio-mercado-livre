package com.vandson.desafiomercadolivre.compartilhado;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

/**
 * @author Vandson Lima (vandson.vslima@gmail.com)
 * @since 27/07/2020
 **/
//1
public class ExistsIdValidator implements ConstraintValidator<ExistsId,Object> {
    private Class<?> domainClass;
    private String fildName;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void initialize(ExistsId existsId) {
        domainClass = existsId.domainClass();
        fildName = existsId.fieldName();
    }

    /**
     * Uma entidade é válida quando existe registro no banco com o campo informado
     *
     * @param value
     * @param constraintValidatorContext
     * @return
     */
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        if(value == null)
            return true;
        Query query = entityManager.createQuery("SELECT 1 from "+ domainClass.getName() + " WHERE "+ fildName + "=:attributeValue");
        query.setParameter("attributeValue", value);
        List<?> result = query.getResultList();
        return !result.isEmpty();
    }
}
