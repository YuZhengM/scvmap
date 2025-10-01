package com.spring.boot.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@TableName("t_trait_chr_count_susie")
@Data
public class TraitChrCountSusie extends TraitChrCount implements Serializable {
}
