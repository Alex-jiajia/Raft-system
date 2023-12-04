/*
 * KeyValue.java
 * Copyright 2022 Razertory, all rights reserved.
 * GUSU PROPRIETARY/CONFIDENTIAL, any form of usage is subject to approval.
 */

package common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jiaying
 * @date 2023/4/23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KeyValue {

    private String key;
    private String value;
}
