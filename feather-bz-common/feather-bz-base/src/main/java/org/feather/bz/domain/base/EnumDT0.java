package org.feather.bz.domain.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @projectName: feather-bz-server
 * @package: org.feather.bz.domain.base
 * @className: EnumDT0
 * @author: feather
 * @description:
 * @since: 2025-04-27 17:10
 * @version: 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EnumDT0 implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer code;
    private String msg;
}