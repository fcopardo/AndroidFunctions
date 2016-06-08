package com.grizzly.functions.Storage.Json;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import android.content.Context;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.grizzly.functions.TextFunctions;


/**
 * Created by FcoPardo on 5/12/16.
 */
public class Persistence<T extends Serializable> {

    private Class<T> _classToStore;
    private T _objectToStore;

    private Context _context;
    private String _urlDirData = _context.getFilesDir().getAbsolutePath();

    public Persistence(Class<T> classToStore, Context context){
        _classToStore = classToStore;
        _context = context;
    }

    public void setContext(Context context) {
        this._context = context;
    }

    private void doObjectStore(T objectToStore, String fileName){
        if(objectToStore!=null){
            _objectToStore = objectToStore;

            ObjectMapper mapper = new ObjectMapper();
            try {
                File f;
                if(TextFunctions.isBlankOrNull(fileName)){
                    f = new File(_urlDirData + File.separator  + _classToStore.getSimpleName());
                }else{
                    f = new File(_urlDirData + File.separator + _objectToStore.getClass().getSimpleName()+fileName);
                }

                if (!f.exists()) {
                    f.createNewFile();
                }
                mapper.writeValue(f, _objectToStore);

            }catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
    }

    public void storeObject(T objectToStore){
        doObjectStore(objectToStore, null);
    }

    public void storeObject(T objectToStore, String fileName){
        doObjectStore(objectToStore, fileName);
    }

    private T dogetObject(String fileName){
        ObjectMapper mapper = new ObjectMapper();
        T object = null;
        try {
            File file;
            if(TextFunctions.isBlankOrNull(fileName)){
                file = new File(_urlDirData + File.separator  + _classToStore.getSimpleName());
            }else{
                file = new File(_urlDirData + File.separator + _objectToStore.getClass().getSimpleName()+fileName);
            }
            if(file.exists()) object = mapper.readValue(file, _classToStore);

        } catch (JsonGenerationException e) {

            e.printStackTrace();

        } catch (JsonMappingException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
        catch(NullPointerException e){
            e.printStackTrace();
        }
        return object;
    }

    public T getObject()
    {
       return dogetObject(null);
    }

    public T getObject(String fileName)
    {
        return dogetObject(fileName);
    }

    private boolean doDeleteObject(String fileName){
        try {
            File file;
            if(TextFunctions.isBlankOrNull(fileName)){
                file = new File(_urlDirData + File.separator + _classToStore.getSimpleName());
            }else{
                file = new File(_urlDirData + File.separator + _classToStore.getSimpleName()+fileName);
            }
            return file.delete();
        }
        catch(NullPointerException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteObject()
    {
        return doDeleteObject(null);
    }

    public boolean deleteObject(String fileName)
    {
        return doDeleteObject(fileName);
    }


}
