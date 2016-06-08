package com.grizzly.functions.Storage.Json;

import android.content.Context;

import java.io.Serializable;

/**
 * Created by FcoPardo on 5/16/16.
 */
public class PersistenceStaticProvider {

    private static PersistenceFactory factory = new PersistenceFactory();

    public static <T extends Serializable> Persistence<T> getJsonPersistence(Class<T> classToPersist, Context context){
        return factory.getJsonPersistence(classToPersist, context);
    }
}
