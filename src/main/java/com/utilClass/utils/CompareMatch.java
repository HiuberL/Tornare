package com.utilClass.utils;

import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
/*
 * Clase que contiene los métodos necesarios para comparar elementos de una clase A a una clase B
 * Inicialmente se deben instanciar las clases que se van a hacer match ConverterAny<Example1,Example2> converter = new ConverterAny<>()
 * @author: Kevin Lizano
 * @version: 1.0.0
 */
@SuppressWarnings("unchecked")
public class CompareMatch<S, T> {
    private ObjectMapper mapperData = new ObjectMapper();
    private Map<String, Object> mapSubject;
    private Map<String, Object> mapTarget;
    /*
    * Este método permite comparar una clase A a una clase B por los campos, haciendo match de los elementos
    * @author: Kevin Lizano
    * @version: 1.0.0
    * @param classSubject
    * @param classTarget
    * @return double
    */
    public double matchField(S classSubject, T classTarget) throws IllegalArgumentException {
        mapSubject = mapperData.convertValue(classSubject, Map.class);
        mapTarget  = mapperData.convertValue(classTarget, Map.class);
        Long countMatch = mapTarget.keySet().
                    stream().
                    filter(mapSubject::containsKey).count();
        Long countFields = mapTarget.keySet().
                    stream().count();
        return ((double)countMatch/(double)countFields *100.0);
    }
    /*
    * Este método permite comparar una clase A a una clase B por los resultados de los campos, haciendo match de los elementos
    * @author: Kevin Lizano
    * @version: 1.0.0
    * @param classSubject
    * @param classTarget
    * @return double
    */
    public double matchValues(S classSubject, T classTarget) throws IllegalArgumentException {
        mapSubject = mapperData.convertValue(classSubject, Map.class);
        mapTarget = mapperData.convertValue(classTarget, Map.class);
        Long countMatch = mapTarget.keySet().
                    stream().
                    filter(mapSubject::containsKey).
                    filter(e-> mapSubject.get(e).equals(mapTarget.get(e))).count();
        Long countFields = mapTarget.keySet().
                    stream().count();
        return ((double)countMatch/(double)countFields *100);
    }
}
