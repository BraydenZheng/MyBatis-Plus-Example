package com.fengwenyi.mybatis_plus_example.model.Dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WebInDto<T extends Model<?>> extends Model<T> {
    // 分页处理用
    @ApiParam(defaultValue = "1")
    @TableField(exist = false)
    protected Integer pageNum; //页码

    @ApiParam(defaultValue = "5")
    @TableField(exist = false)
    protected Integer pageSize;//分页条数

    @ApiParam(defaultValue = "asc", value = "排序 asc / desc")
    @TableField(exist = false)
    protected String sort; //排序 (ASC/DESC）

    @ApiParam(value = "排序字段 (column)")
    @TableField(exist = false)
    protected String order;//排序字段
}
