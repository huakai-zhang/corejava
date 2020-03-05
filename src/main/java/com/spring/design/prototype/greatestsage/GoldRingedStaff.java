package com.spring.design.prototype.greatestsage;

import java.io.Serializable;

/**
 * @author Spring Zhang
 * @date 2020/3/5 15:56
 */
public class GoldRingedStaff implements Serializable {
    private float height = 100;
    private float diameter = 10;

    public void grow() {
        this.diameter *= 2;
        this.height *= 2;
    }

    public void shrink() {
        this.diameter /= 2;
        this.height /= 2;
    }
}
