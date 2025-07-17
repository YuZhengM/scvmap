package com.spring.boot.util.util.callback;

import java.util.List;

public interface TwoCallback<T, X, Y> {

    List<T> run(X x, Y y);

}
