package com.tornare.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
 * Clase que contiene los métodos necesarios para comparar elementos de una Lista de mapa A a una Lista de clases B
 * @author: Kevin Lizano
 * @version: 1.0.0
 */
public class TornareList<T> {
    private ObjectMapper mapperData = new ObjectMapper();

    /*
     * Este método permite comparar una clase A a una clase B por los campos,
     * haciendo match de los elementos
     * 
     * @author: Kevin Lizano
     * 
     * @version: 1.0.0
     * 
     * @param subject
     * 
     * @return List<T>
     */
    public List<T> toListClass(List<Map<String, ?>> subject) throws IllegalArgumentException {
        List<T> target = new ArrayList<>();
        subject.forEach(value -> {
            target.add((T) mapperData.convertValue(value, new TypeReference<T>() {
            }));
        });
        return target;
    }

    public List<T> toListClass(List<Map<String, ?>> subject, List<String> ignore) throws IllegalArgumentException {
        List<T> target = new ArrayList<>();
        subject.forEach(value -> {
            ignore.forEach(e -> value.remove(e));
            target.add((T) mapperData.convertValue(value, new TypeReference<T>() {
            }));
        });
        return target;
    }

    public List<T> toListClass(List<Map<String, ?>> subject, Map<String, Object> ignoreIf)
            throws IllegalArgumentException {
        List<T> target = new ArrayList<>();
        subject.forEach(value -> {
            ignoreIf.entrySet().forEach(e -> {
                if (Optional.ofNullable(value.get(e.getKey())).isPresent())
                    if (value.get(e.getKey()).equals(e.getValue()))
                        value.remove(e.getKey());
            });
            target.add((T) mapperData.convertValue(value, new TypeReference<T>() {
            }));
        });
        return target;
    }
}
