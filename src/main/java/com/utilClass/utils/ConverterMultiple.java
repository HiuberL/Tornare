package com.utilClass.utils;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
/*
 * Clase que contiene los métodos necesarios para convertir una clase A y una clase B a una clase C
 * Inicialmente se deben instanciar las clases que se van a hacer match ConverterAny<Example1,Example2,Target> converter = new ConverterAny<>()
 * @author: Kevin Lizano
 * @version: 1.0.0
 */
@SuppressWarnings("unchecked")
public class ConverterMultiple<S,V,T> {
    private ObjectMapper mapperData = new ObjectMapper();
    private Map<String, Object> mapSubjectFirst;
    private Map<String, Object> mapSubjectSecond;
    private Map<String, Object> mapTarget;
    
    /*
    * Este método permite convertir una clase A y una clase B a una clase C, haciendo match de los métodos,
    * Dando prioridad a los elementos de la clase A y en segundo plano los de la clas B. Es decir, si la clase A
    * y la clase B tienen elementos en comun, la clase A será prioritaria en los elementos de la clase C.
    * @author: Kevin Lizano
    * @version: 1.0.0
    * @param classSubject
    * @param classTarget
    * @return classTarget
    */
    public <T> T converterAnyClass(S classSubjectFirst,V classSubjectSecond , T classTarget) throws IllegalArgumentException {
        mapSubjectFirst = mapperData.convertValue(classSubjectFirst, Map.class);
        mapSubjectSecond = mapperData.convertValue(classSubjectSecond, Map.class);
        mapTarget = mapperData.convertValue(classTarget, Map.class);
        mapSubjectFirst.keySet().
                    stream().
                    filter(mapTarget::containsKey).
                    forEach(s -> mapTarget.replace(s, mapSubjectFirst.get(s)));       
        mapSubjectSecond.keySet().
                    stream().
                    filter(mapTarget::containsKey).
                    filter(e -> !mapSubjectFirst.containsKey(e)).
                    forEach(s -> mapTarget.replace(s, mapSubjectSecond.get(s)));       
        return (T) mapperData.convertValue(mapTarget, (classTarget.getClass()));
    }
    /*
    * Este método permite convertir una clase A y una clase B a una clase C, haciendo match de los métodos,
    * Dando prioridad a los elementos de la clase A y en segundo plano los de la clas B. Es decir, si la clase A
    * y la clase B tienen elementos en comun, la clase A será prioritaria en los elementos de la clase C. Esto segun a la
    * lista de elementos proporcionados
    * @author: Kevin Lizano
    * @version: 1.0.0
    * @param classSubject
    * @param classTarget
    * @return classTarget
    */    
    public <T> T converterMultipleClass(S classSubjectFirst,V classSubjectSecond , T classTarget,List<String> keysFirst,List<String> keysSecond) throws IllegalArgumentException {
        mapSubjectFirst = mapperData.convertValue(classSubjectFirst, Map.class);
        mapSubjectSecond = mapperData.convertValue(classSubjectSecond, Map.class);
        mapTarget = mapperData.convertValue(classTarget, Map.class);
        mapSubjectFirst.keySet().
                    stream().
                    filter(mapTarget::containsKey).
                    filter(keysFirst::contains).
                    forEach(s -> mapTarget.replace(s, mapSubjectFirst.get(s)));       
        mapSubjectSecond.keySet().
                    stream().
                    filter(mapTarget::containsKey).
                    filter(keysSecond::contains).
                    filter(e -> !mapSubjectFirst.containsKey(e)).
                    forEach(s -> mapTarget.replace(s, mapSubjectSecond.get(s)));       
        return (T) mapperData.convertValue(mapTarget, (classTarget.getClass()));
    }    
    /*
    * Este método permite convertir una clase A y una clase B a una clase C, haciendo match de los métodos,
    * Dando prioridad a los elementos de la clase A y en segundo plano los de la clas B. Es decir, si la clase A
    * y la clase B tienen elementos en comun, la clase A será prioritaria en los elementos de la clase C. Esto segun el Mapa de 
    * elementos
    * @author: Kevin Lizano
    * @version: 1.0.0
    * @param classSubject
    * @param classTarget
    * @return classTarget
    */    
    public <T> T converterMultipleClass(S classSubjectFirst,V classSubjectSecond , T classTarget,Map<String,String> keysFirst,Map<String,String> keysSecond) throws IllegalArgumentException {
        mapSubjectFirst = mapperData.convertValue(classSubjectFirst, Map.class);
        mapSubjectSecond = mapperData.convertValue(classSubjectSecond, Map.class);
        mapTarget = mapperData.convertValue(classTarget, Map.class);
        mapSubjectFirst.keySet().
                    stream().
                    filter(mapTarget::containsKey).
                    filter(keysFirst::containsKey).
                    forEach(s -> mapTarget.replace(keysFirst.get(s), mapSubjectFirst.get(s)));       
        mapSubjectSecond.keySet().
                    stream().
                    filter(mapTarget::containsKey).
                    filter(keysSecond::containsKey).
                    filter(e -> !mapSubjectFirst.containsKey(e)).
                    forEach(s -> mapTarget.replace(keysSecond.get(s), mapSubjectSecond.get(s)));       
        return (T) mapperData.convertValue(mapTarget, (classTarget.getClass()));
    }    


}
