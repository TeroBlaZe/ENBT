package ru.ensemplix.nbt.tag;

import ru.ensemplix.nbt.stream.NBTInputStream;
import ru.ensemplix.nbt.stream.NBTOutputStream;

import java.io.IOException;

import static ru.ensemplix.nbt.tag.TagType.STRING;

public class StringTag extends AbstractTag<String> {

    public StringTag(String value) {
        super("", value);
    }

    public StringTag(String name, String value) {
        super(name, value);
    }

    @Override
    public void readTag(NBTInputStream in) throws IOException {
        value = in.readUTF();
    }

    @Override
    public void writeTag(NBTOutputStream out) throws IOException {
        out.writeUTF(value);
    }

    @Override
    public TagType getType() {
        return STRING;
    }

}
