package org.feather.bz.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
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
@Target({
        ElementType.METHOD,
        ElementType.FIELD,
        ElementType.ANNOTATION_TYPE,
        ElementType.CONSTRUCTOR,
        ElementType.PARAMETER,
        ElementType.TYPE_USE
})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(
        validatedBy = {InEnumValidator.class}
)
public @interface InEnum {
    /**
     * @return 实现 EnumValuable 接口的
     */
    Class<? extends ArrayValuable<?>> value();

    String message() default "必须在指定范围 {value}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
