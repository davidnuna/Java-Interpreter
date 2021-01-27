package model.values;

import model.types.IType;
import model.types.RefType;

public class RefValue implements IValue{
    private final int address;
    private final IType locationType;

    public RefValue(int address, IType locationType) {
        this.address = address;
        this.locationType = locationType;
    }

    public int getAddress() { return address; }

    public IType getLocationType() { return locationType; }

    @Override
    public IType getType() { return new RefType(locationType); }

    @Override
    public boolean equals(Object other){
        if (this == other){
            return true;
        }
        if (other == null || getClass() != other.getClass()){
            return false;
        }
        RefValue otherValue = (RefValue) other;
        return address == otherValue.getAddress() && locationType.equals(otherValue.getLocationType());
    }

    @Override
    public String toString() { return "(" + address + ", " + locationType.toString() + ")"; }

    @Override
    public IValue deepCopy() { return new RefValue(address, locationType); }
}
