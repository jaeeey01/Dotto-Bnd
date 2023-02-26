package com.dotto.app.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Success<T> implements Result{
    private T data;

    @Override
    public String toString() {
        return "Success{" +
                "data=" + data +
                '}';
    }
}
