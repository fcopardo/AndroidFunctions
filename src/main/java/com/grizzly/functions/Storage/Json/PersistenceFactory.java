package com.grizzly.functions.Storage.Json;

import android.content.Context;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by FcoPardo on 5/16/16.
 */
public class PersistenceFactory {

    private Map<String, Object> _persistenceMap;

    public <T extends Serializable> Persistence<T> getJsonPersistence(Class<T> classToPersist, Context context) {

        if(_persistenceMap == null) {
            _persistenceMap = new HashMap<>();
        }
        if(!_persistenceMap.containsKey(classToPersist.getSimpleName())){
            _persistenceMap.put(classToPersist.getSimpleName(), new Persistence<>(classToPersist, context));
        }else{
            ((Persistence<T>)_persistenceMap.get(classToPersist.getSimpleName())).setContext(context);
        }
        return (Persistence<T>)_persistenceMap.get(classToPersist.getSimpleName());
    }

}