package com.utilClass.utils;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

/*
 * Clase que contiene los métodos necesarios para convertir una clase A a una clase B
 * Inicialmente se deben instanciar las clases que se van a hacer match ConverterAny<Example1,Example2> converter = new ConverterAny<>()
 * @author: Kevin Lizano
 * @version: 1.0.0
 */
@SuppressWarnings("unchecked")
public class ConverterAny<S, T> {
    private ObjectMapper mapperData = new ObjectMapper();
    private Map<String, Object> mapSubject;
    private Map<String, Object> mapTarget;
    /*
    * Este método permite convertir una clase A a una clase B, haciendo match de los métodos
    * @author: Kevin Lizano
    * @version: 1.0.0
    * @param classSubject
    * @param classTarget
    * @return classTarget
    */
    public <T> T converterAnyClass(S classSubject, T classTarget) throws IllegalArgumentException {
        mapSubject = mapperData.convertValue(classSubject, Map.class);
        mapTarget = mapperData.convertValue(classTarget, Map.class);
        mapSubject.keySet().
                    stream().
                    filter(mapTarget::containsKey).
                    forEach(s -> mapTarget.replace(s, mapSubject.get(s)));
        return (T) mapperData.convertValue(mapTarget, (classTarget.getClass()));
    }

    /*
    * Este método permite convertir una clase A a una clase B, haciendo match de los elementos
    * encontrados en la lista.
    * @author: Kevin Lizano
    * @version: 1.0.0
    * @param classSubject
    * @param classTarget
    * @param List<String> keys
    * @return classTarget
    */

    public <T> T converterAnyClass(S classSubject, T classTarget, List<String> keys) throws IllegalArgumentException {
        mapSubject = mapperData.convertValue(classSubject, Map.class);
        mapTarget = mapperData.convertValue(classTarget, Map.class);
        mapSubject.keySet().
                    stream().
                    filter(keys::contains).
                    filter(mapTarget::containsKey).
                    forEach(s -> mapTarget.replace(s, mapSubject.get(s)));
        return (T) mapperData.convertValue(mapTarget, (classTarget.getClass()));
    }
    
    /*
    * Este método permite convertir una clase A a una clase B, haciendo match de los elementos
    * encontrados en el mapa keys, este método es más especifico en cuanto a las asignaciones.
    * @author: Kevin Lizano
    * @version: 1.0.0
    * @param classSubject
    * @param classTarget
    * @param List<String> keys
    * @return classTarget
    */
    public <T> T converterAnyClass(S classSubject, T classTarget, Map<String,String> keys) throws IllegalArgumentException {
        mapSubject = mapperData.convertValue(classSubject, Map.class);
        mapTarget = mapperData.convertValue(classTarget, Map.class);
        mapSubject.keySet().
                    stream().
                    filter(keys::containsKey).
                    forEach(s -> mapTarget.replace(keys.get(s), mapSubject.get(s)));    
        return (T) mapperData.convertValue(mapTarget, (classTarget.getClass()));
    } 
}
