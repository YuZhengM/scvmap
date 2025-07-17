package com.spring.boot.util.util.callback;

import java.util.List;

public interface OneCallback<T, K> {

    List<T> run(K k);

}
