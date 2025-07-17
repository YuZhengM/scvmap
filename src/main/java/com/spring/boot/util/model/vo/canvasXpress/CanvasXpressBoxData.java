package com.spring.boot.util.model.vo.canvasXpress;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Builder
@ToString
@Data
public class CanvasXpressBoxData<T> {

    private T data;

    private List<String> yLabelList;

}
