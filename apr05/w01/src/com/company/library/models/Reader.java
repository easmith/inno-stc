package com.company.library.models;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * Created by eku on 05.04.17.
 */
public class Reader implements Externalizable {
    private String firstName;
    private String secondName;
    private String lastName;
    private long passportNumber;
    public Reader(String firstName, String secondName, String lastName, long passportNumber) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
        this.passportNumber = passportNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public long getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(long passportNumber) {
        this.passportNumber = passportNumber;
    }

    @Override
    public int hashCode() {
        return (int) (passportNumber * 32);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;

        if (!(obj instanceof Reader))
            return false;

        if (passportNumber != ((Reader) obj).passportNumber)
            return false;

        return true;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeLong(this.passportNumber);
        out.writeUTF(this.firstName);
        out.writeUTF(this.secondName);
        out.writeUTF(this.lastName);
        out.writeUTF("Evgeny Kuznetsov");
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.passportNumber = in.readLong();
        this.firstName = in.readUTF();
        this.secondName = in.readUTF();
        this.lastName = in.readUTF();
        in.readUTF();
    }
}
