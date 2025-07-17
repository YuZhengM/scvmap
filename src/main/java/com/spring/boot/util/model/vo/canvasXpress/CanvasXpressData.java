package com.spring.boot.util.model.vo.canvasXpress;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Builder
@ToString
@Data
public class CanvasXpressData<T> implements Serializable {

    private List<List<T>> data;

    private List<String> xLabelList;

    private List<String> yLabelList;

    private List<String> zLabelList;

}
