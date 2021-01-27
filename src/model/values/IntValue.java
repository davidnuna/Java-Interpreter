package model.values;

import model.types.IType;
import model.types.IntType;

import java.util.Objects;

public class IntValue implements IValue{
    private final int value;

    public IntValue(int value) { this.value = value; }

    public int getValue() { return value; }

    @Override
    public IType getType() { return new IntType(); }

    @Override
    public boolean equals(Object other){
        if (this == other){
            return true;
        }
        if (other == null || getClass() != other.getClass()){
            return false;
        }
        IntValue otherValue = (IntValue) other;
        return value == otherValue.getValue();
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() { return Integer.toString(value); }

    @Override
    public IValue deepCopy() { return new IntValue(value); }
}
