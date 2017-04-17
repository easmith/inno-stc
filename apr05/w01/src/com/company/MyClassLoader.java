package com.company;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class MyClassLoader extends ClassLoader {
//    private String jarFile = "/home/eku/proj/stc/apr05/tmp/Ticket.jar"; //Path to the jar file
    private String jarFile = "/home/eku/proj/stc/apr05/tmp/Book.jar"; //Path to the jar file
    private HashMap classes = new HashMap(); //used to cache already defined classes

    public MyClassLoader() {
        super(MyClassLoader.class.getClassLoader()); //calls the parent class loader's constructor
    }

    public Class loadClass(String className) throws ClassNotFoundException {
//        return findClass(className);
        return myloadClass(className);
    }

    public Class findClass(String className) {
        byte classByte[];
        Class result = null;

        result = (Class) classes.get(className); //checks in cached classes
        if (result != null) {
            return result;
        }

        try {
            return findSystemClass(className);
        } catch (Exception e) {
        }

        try {
            JarFile jar = new JarFile(jarFile);
            JarEntry entry = jar.getJarEntry(className + ".class");
            InputStream is = jar.getInputStream(entry);
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            int nextValue = is.read();
            while (-1 != nextValue) {
                byteStream.write(nextValue);
                nextValue = is.read();
            }

            classByte = byteStream.toByteArray();
            result = defineClass(className, classByte, 0, classByte.length, null);
            classes.put(className, result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Class myloadClass(String name) throws ClassNotFoundException {
        if(!"com.company.library.models.Book".equals(name))
            return super.loadClass(name);

        try {
            String url = "file:/home/eku/proj/stc/apr05/tmp/Book.class";
            URL myUrl = new URL(url);
            URLConnection connection = myUrl.openConnection();
            InputStream input = connection.getInputStream();
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            int data = input.read();

            while(data != -1){
                buffer.write(data);
                data = input.read();
            }

            input.close();

            byte[] classData = buffer.toByteArray();

            return defineClass("com.company.library.models.Book",
                    classData, 0, classData.length);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


}