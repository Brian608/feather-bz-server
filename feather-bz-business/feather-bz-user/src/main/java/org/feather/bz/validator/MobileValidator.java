package org.feather.bz.validator;

import cn.hutool.core.lang.Validator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * @projectName: feather-bz-server
 * @package: org.feather.bz.validator
 * @className: MobileValidator
 * @author: feather
 * @description:
 * @since: 2025-04-27 16:56
 * @version: 1.0
 */
public class MobileValidator implements ConstraintValidator<IsMobile, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return Validator.isMobile(value);
    }
}
