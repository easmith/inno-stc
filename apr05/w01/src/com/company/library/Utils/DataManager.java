package com.company.library.Utils;

import com.company.library.models.Book;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by eku on 05.04.17.
 */
public class DataManager {


    public static <T extends Externalizable> void uSerialize(Set<T> objects) {

        String fileName = objects.iterator().next().getClass().getName().replace('.', '_');
        fileName += ".txt";

        try (FileOutputStream fos = new FileOutputStream(fileName);
             ObjectOutputStream oos = new ObjectOutputStream(fos)){

            oos.writeInt(objects.size());
            for (T obj: objects)
                obj.writeExternal(oos);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <T extends Externalizable> Set<T> uDeserialize (T t) {
        Set<T> objects = new HashSet<>();

        String fileName = t.getClass().getName().replace('.', '_');
        fileName += ".txt";

        try (FileInputStream fis = new FileInputStream(fileName);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            int total = ois.readInt();
            for (int i = 0; i < total; i++) {
                T obj = (T) t.getClass().newInstance();
                obj.readExternal(ois);
                objects.add(obj);
            }
        } catch (EOFException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return objects;
        }
    }
}
