package org.feather.bz.validator;

import cn.hutool.core.collection.CollUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @projectName: feather-bz-server
 * @package: org.feather.bz.validator
 * @className: InEnumValidator
 * @author: feather
 * @description:
 * @since: 2025-04-27 17:06
 * @version: 1.0
 */
public class InEnumValidator implements ConstraintValidator<InEnum, Object> {

    private List<Object> values;

    @Override
    public void initialize(InEnum annotation) {
        ArrayValuable<?>[] values = annotation.value().getEnumConstants();
        if (values.length == 0) {
            this.values = Collections.emptyList();
        } else {
            this.values = Arrays.stream(values[0].array()).collect(Collectors.toList());
        }
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        // 为空时，默认不校验，即认为通过
        if (value == null) {
            return true;
        }
        //value 是集合
        if (value instanceof Collection) {
            // 校验通过
            if (CollUtil.containsAll(values, (Collection<?>) value)) {
                return true;
            }
        } else {
            // 校验通过
            if (values.contains(value)) {
                return true;
            }
        }
        // 校验不通过，自定义提示语句（因为，注解上的 value 是枚举类，无法获得枚举类的实际值）
        context.disableDefaultConstraintViolation(); // 禁用默认的 message 的值
        context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate()
                .replaceAll("\\{value}", values.toString())).addConstraintViolation(); // 重新添加错误提示语句
        return false;
    }

}