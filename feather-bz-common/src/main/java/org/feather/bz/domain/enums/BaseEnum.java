package org.feather.bz.domain.enums;

import org.feather.bz.domain.base.EnumDT0;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @projectName: feather-bz-server
 * @package: org.feather.bz.domain.enums
 * @className: BaseEnum
 * @author: feather
 * @description:
 * @since: 2025-04-27 17:09
 * @version: 1.0
 */
public interface BaseEnum {

    Integer getCode();

    String getMsg();

    /**
     * code 获取枚举
     * @param code
     * @param clazz
     * @return
     * @param <E>
     */

    static <E extends Enum<E> & BaseEnum> E getByCode(Integer code, Class<E> clazz) {
        Objects.requireNonNull(code);
        EnumSet<E> all = EnumSet.allOf(clazz);
        return all.stream().filter((e) -> ((BaseEnum)e).getCode().equals(code)).findFirst().orElse(null);
    }

    /**
     * 值获取枚举
     * @param msg
     * @param clazz
     * @return
     * @param <E>
     */
    static <E extends Enum<E> & BaseEnum> E getByMsg(String msg, Class<E> clazz) {
        Objects.requireNonNull(msg);
        EnumSet<E> all = EnumSet.allOf(clazz);
        return all.stream().filter((e) -> ((BaseEnum)e).getMsg().equals(msg)).findFirst().orElse(null);
    }

    /**
     * 枚举转 map
     * @param enumClass
     * @return
     * @param <E>
     */
    static <E extends Enum<E> & BaseEnum> Map<Integer, String> toMap(Class<E> enumClass) {
        return Arrays.stream(enumClass.getEnumConstants())
                .collect(Collectors.toMap(BaseEnum::getCode, BaseEnum::getMsg));
    }

    static <E extends Enum<E> & BaseEnum> List<EnumDT0> toList(Class<E> enumClass) {
        return Arrays.stream(enumClass.getEnumConstants())
                .map(enumConstant -> new EnumDT0(enumConstant.getCode(), enumConstant.getMsg()))
                .collect(Collectors.toList());
    }
}
