package model.values;

import model.types.IType;
import model.types.BoolType;

import java.util.Objects;

public class BoolValue implements IValue{
    private final boolean value;

    public BoolValue(boolean value) { this.value = value; }

    public boolean getValue() { return value; }

    @Override
    public IType getType() { return new BoolType(); }

    @Override
    public boolean equals(Object other){
        if (this == other){
            return true;
        }
        if (other == null || getClass() != other.getClass()){
            return false;
        }
        BoolValue otherValue = (BoolValue) other;
        return value == otherValue.getValue();
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() { return Boolean.toString(value); }

    @Override
    public IValue deepCopy() { return new BoolValue(value); }
}
