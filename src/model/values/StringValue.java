package model.values;

import model.types.IType;
import model.types.StringType;

import java.util.Objects;

public class StringValue implements IValue{
    private final String value;

    public StringValue(String value) { this.value = value; }

    public String getValue() { return value; }

    @Override
    public IType getType() { return new StringType(); }

    @Override
    public boolean equals(Object other){
        if (this == other){
            return true;
        }
        if (other == null || getClass() != other.getClass()){
            return false;
        }
        StringValue otherValue = (StringValue) other;
        return value.equals(otherValue.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() { return "\"" + value + "\""; }

    @Override
    public IValue deepCopy() { return new StringValue(value); }
}
