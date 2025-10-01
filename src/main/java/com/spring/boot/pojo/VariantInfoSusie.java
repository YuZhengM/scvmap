package com.spring.boot.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class VariantInfoSusie extends VariantInfo implements Serializable {
}
