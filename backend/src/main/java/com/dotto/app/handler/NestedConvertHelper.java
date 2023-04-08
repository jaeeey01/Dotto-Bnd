package com.dotto.app.handler;

import com.dotto.app.exception.CannotConvertNestedStructureException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class NestedConvertHelper<K, E, D> {

    private List<E> entites;
    private Function<E, D> toDto;
    private Function<E, E> getParent;

    private Function<E, K> getKey;
    private Function<D,List<D>> getChildren;

    public static <K,E,D> NestedConvertHelper newInstance(List<E> entites, Function<E,D> toDto, Function<E,E>getParent, Function<E,K> getKeys, Function<D,List<D>> getChildren){
        return new NestedConvertHelper<K, E, D>(entites, toDto, getParent, getKeys ,getChildren);

    }

    private NestedConvertHelper(List<E> entites, Function<E, D> toDto, Function<E,E> getParent, Function<E, K> getKey, Function<D,List<D>> getChildren){
        this.entites = entites;
        this.toDto = toDto;
        this.getParent = getParent;
        this.getKey = getKey;
        this.getChildren = getChildren;
    }

    public List<D> convert(){
        try{
            return convertInternal();
        }catch (NullPointerException e){
                throw new CannotConvertNestedStructureException(e.getMessage());
        }
    }

    public List<D> convertInternal(){
        Map<K, D> map = new HashMap<>();
        List<D> roots = new ArrayList<>();

        for (E e : entites) {
            D dto = toDto(e);
            map.put(getKey(e), dto);
            if(hasParent(e)){
                E parent = getParent(e);
                K parentKey = getKey(parent);
                D parentDto = map.get(parentKey);
                getChildren(parentDto).add(dto);
            }else{
                roots.add(dto);
            }
        }
        return  roots;
    }
    private boolean hasParent(E e){
        return getParent(e) != null;
    }

    private E getParent(E e){
        return getParent.apply(e);
    }

    private D toDto(E e){
        return toDto.apply(e);
    }

    private K getKey(E e){
        return getKey.apply(e);
    }

    private List<D> getChildren(D d){
        return getChildren.apply(d);
    }

}
