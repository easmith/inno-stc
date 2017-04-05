package com.company.library.models;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * Created by eku on 05.04.17.
 */
public abstract class Model implements Cloneable, Externalizable {
    @Override
    public Model clone() throws CloneNotSupportedException {
        return (Model) super.clone();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF("Evgeny Kuznetsov");
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        in.readUTF();
    }
}
