package org.feather.bz.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @projectName: feather-bz-server
 * @package: org.feather.bz.validator
 * @className: IsMobile
 * @author: feather
 * @description:
 * @since: 2025-04-27 16:56
 * @version: 1.0
 */
@Constraint(validatedBy = MobileValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface IsMobile {
    String message() default "手机号格式不正确"; // 默认错误信息

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}