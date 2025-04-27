package org.feather.bz.validator;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * @projectName: feather-bz-server
 * @package: org.feather.bz.validator
 * @className: InEnum
 * @author: feather
 * @description:
 * @since: 2025-04-27 17:05
 * @version: 1.0
 */
@Documented
@Constraint(validatedBy = InEnumValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface InEnum {

    /**
     * 指定的枚举类，要求实现 ArrayValuable 接口
     */
    Class<? extends ArrayValuable<?>> value();

    String message() default "必须在指定范围 {value}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}