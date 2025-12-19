package org.feather.bz.domain.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @projectName: feather-bz-server
 * @package: org.feather.bz.domain.base
 * @className: DxsEnumDT0
 * @author: feather
 * @description:
 * @since: 2025-12-19 13:49
 * @version: 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DxsEnumDT0 implements Serializable {
    private static final long serialVersionUID = 1L;
    private String code;
    private String desc;
}
