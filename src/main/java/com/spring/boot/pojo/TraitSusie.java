package com.spring.boot.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * t_trait
 *
 * @author Admin
 */
@EqualsAndHashCode(callSuper = true)
@TableName("t_trait_susie")
@Data
@Schema(name = "Trait (SuSiE)", description = "Trait or disease information")
public class TraitSusie extends Trait implements Serializable {
}
