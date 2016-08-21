package com.helper;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by bhushan on 21/8/16.
 */
@XmlRootElement
@Getter
@Setter
@NoArgsConstructor
public class ApiStatusResult implements Serializable {
    private static final long serialVersionUID = -7027507409588330851L;

    private boolean success;
    private String message;

    public ApiStatusResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
