package com.spring.boot.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class SampleTraitEnrichSusie extends SampleEnrichSampleId implements Serializable {

}
