package com.qk.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult<T> {//这是在此处加了泛型类,接收的数据就步固定了,但也决定了里面只能用定义时定义好的类型
    private long total; // 总记录数
    private List<T> rows; // 当前也的数据列表


}
