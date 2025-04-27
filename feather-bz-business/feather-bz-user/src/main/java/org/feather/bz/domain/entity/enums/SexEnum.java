package org.feather.bz.domain.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.feather.bz.domain.enums.BaseEnum;
import org.feather.bz.validator.ArrayValuable;

import java.util.Arrays;

/**
 * @projectName: feather-bz-server
 * @package: org.feather.bz.domain.entity.enums
 * @className: SexEnum
 * @author: feather
 * @description:
 * @since: 2025-04-27 17:07
 * @version: 1.0
 */
@AllArgsConstructor
@Getter
public enum SexEnum implements BaseEnum, ArrayValuable<Integer> {

    MALE(1,"男"),
    FEMALE(0,"女"),
    SECRECY(2,"保密");

    private final Integer code;

    private final String msg;

    public static  final Integer[] ARRAYS= Arrays.stream(values()).map(SexEnum::getCode).toArray(Integer[]::new);

    @Override
    public Integer[] array() {
        return ARRAYS;
    }
}
