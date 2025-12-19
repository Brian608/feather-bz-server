package org.feather.bz.domain.enums;

import org.feather.bz.domain.base.DxsEnumDT0;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @projectName: feather-bz-server
 * @package: org.feather.bz.domain.enums
 * @className: DxsBaseEnum
 * @author: feather
 * @description:
 * @since: 2025-12-19 13:46
 * @version: 1.0
 */
public interface DxsBaseEnum {
    String getCode();

    String getDesc();

    /**
     * description:  获取枚举
     * @param code
     * @param clazz
     * @return {@link E}
     * @author: duxuesong
     * @since: 2025-02-11 11:15 AM
     **/
    static <E extends Enum<E> & DxsBaseEnum> E getByCode(String code, Class<E> clazz) {
        Objects.requireNonNull(code);
        EnumSet<E> all = EnumSet.allOf(clazz);
        return all.stream().filter((e) -> e.getCode().equals(code)).findFirst().orElse(null);
    }

    /**
     * description:  值获取枚举
     * @param desc
     * @param clazz
     * @return {@link E}
     * @author: duxuesong
     * @since: 2025-02-11 11:15 AM
     **/
    static <E extends Enum<E> & DxsBaseEnum> E getByDesc(String desc, Class<E> clazz) {
        Objects.requireNonNull(desc);
        EnumSet<E> all = EnumSet.allOf(clazz);
        return all.stream().filter((e) -> e.getDesc().equals(desc)).findFirst().orElse(null);
    }

    /**
     * description:  多个逗号拼接的code翻译
     * @param codes
     * @param clazz
     * @return {@link String}
     * @author: duxuesong
     * @since: 2025-12-19 11:31
     **/
    static <E extends Enum<E> & DxsBaseEnum> String convertCodesToDesc(String codes, Class<E> clazz) {
        if (codes == null || codes.trim().isEmpty()) {
            return "";
        }

        String[] codeArray = codes.split(",");
        List<String> descList = new ArrayList<>();

        EnumSet<E> all = EnumSet.allOf(clazz);

        for (String code : codeArray) {
            String trimmedCode = code.trim();
            all.stream()
                    .filter(e -> e.getCode().equals(trimmedCode))
                    .findFirst()
                    .ifPresent(e -> descList.add(e.getDesc()));
        }

        return String.join(",", descList);
    }

    /**
     * description:  枚举转 map
     * @param enumClass
     * @return {@link Map< String, String>}
     * @author: duxuesong
     * @since: 2025-02-11 11:15 AM
     **/
    static <E extends Enum<E> & DxsBaseEnum> Map<String, String> toMap(Class<E> enumClass) {
        return Arrays.stream(enumClass.getEnumConstants())
                .collect(Collectors.toMap(DxsBaseEnum::getCode, DxsBaseEnum::getDesc));
    }
    /**
     * description:  枚举转List
     * @param enumClass
     * @return {@link List<DxsEnumDT0>}
     * @author: duxuesong
     * @since: 2025-02-11 11:15 AM
     **/
    static <E extends Enum<E> & DxsBaseEnum> List<DxsEnumDT0> toList(Class<E> enumClass) {
        return Arrays.stream(enumClass.getEnumConstants())
                .map(enumConstant -> new DxsEnumDT0(enumConstant.getCode(), enumConstant.getDesc()))
                .collect(Collectors.toList());
    }
    }
