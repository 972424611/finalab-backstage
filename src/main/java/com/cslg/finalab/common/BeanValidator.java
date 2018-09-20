package com.cslg.finalab.common;

import com.cslg.finalab.exception.CustomException;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections4.MapUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.*;

public class BeanValidator {

    private static ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();

    public static <T> LinkedHashMap validate(T t, Class... groups) {
        Validator validator = validatorFactory.getValidator();
        Set validateResult = validator.validate(t, groups);
        if(validateResult.isEmpty()) {
            return (LinkedHashMap) Collections.emptyMap();
        }else {
            LinkedHashMap errors = Maps.newLinkedHashMap();
            for (Object aValidateResult : validateResult) {
                ConstraintViolation violation = (ConstraintViolation) aValidateResult;
                errors.put(violation.getPropertyPath().toString(), violation.getMessage());
            }
            return errors;
        }
    }

    public static Map validateList(Collection<?> collection) {
        Preconditions.checkNotNull(collection);
        Iterator iterator = collection.iterator();
        Map errors;
        do {
            if(!iterator.hasNext()) {
                return Collections.emptyMap();
            }
            Object object = iterator.next();
            errors = validate(object);
        }while(errors.isEmpty());
        return errors;
    }

    public static Map validateObject(Object first, Object... objects) {
        if(objects != null && objects.length > 0) {
            return validateList(Lists.asList(first, objects));
        }else {
            return validate(first);
        }
    }

    public static void check(Object param) throws CustomException {
        Map map = BeanValidator.validateObject(param);
        if(MapUtils.isNotEmpty(map)) {
            throw new CustomException(map.toString());
        }
    }
}
