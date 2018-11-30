package com.blauband.ods.converter;

import java.io.Serializable;

/**
 *
 * @author sergeizenevich
 */
public class StatusDto implements Serializable {

    public String file;
    public String trace = "";
    public boolean finished;
}
