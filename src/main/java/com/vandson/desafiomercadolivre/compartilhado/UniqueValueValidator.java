package com.vandson.desafiomercadolivre.compartilhado;

import org.springframework.util.Assert;

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
public class UniqueValueValidator implements ConstraintValidator<UniqueValue, Object> {
    private Class<?> domainClass;
    private String attributeName;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void initialize(UniqueValue uniqueValue) {
        domainClass = uniqueValue.domainClass();
        attributeName = uniqueValue.fieldName();
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
        Query query = entityManager.createQuery("SELECT 1 from "+ domainClass.getName() + " WHERE "+ attributeName + "=:attributeValue");
        query.setParameter("attributeValue", object);
        List<?> result = query.getResultList();
        Assert.state(result.size() <=1,"Foi encontrado mais de um "+ domainClass +" com mesmo "+ attributeName);

        return result.isEmpty();


    }
}
