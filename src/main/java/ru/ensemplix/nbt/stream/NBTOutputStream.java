package ru.ensemplix.nbt.stream;

import ru.ensemplix.nbt.converter.ObjectConverter;
import ru.ensemplix.nbt.tag.Tag;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

public class NBTOutputStream extends DataOutputStream {

    public NBTOutputStream(OutputStream out) throws IOException {
        this(out, false);
    }

    public NBTOutputStream(OutputStream out, boolean compressed) throws IOException {
        super(compressed ? new GZIPOutputStream(out) : out);
    }

    public void writeTag(Tag tag) throws IOException {
        writeByte(tag.getType().ordinal());
        writeUTF(tag.getName());
        tag.writeTag(this);
    }

    public void writeObject(Object obj) throws IOException {
        try {
            writeTag(ObjectConverter.toTag("", obj));
        } catch (IllegalAccessException e) {
            throw new IllegalStateException("Failed write NBT object " + obj.getClass().getName(), e);
        }
    }

}
