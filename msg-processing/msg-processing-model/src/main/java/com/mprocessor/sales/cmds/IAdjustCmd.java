package com.mprocessor.sales.cmds;

/**
 * Created by Samantha on 25/03/2017.
 */
public interface IAdjustCmd<I,V,R> {
    public R adjust(I i, V v);
}
