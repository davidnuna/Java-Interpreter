package model.types;

import model.values.*;

public class BoolType implements IType{
    public boolean equals(Object other){
        return other instanceof BoolType;
    }

    @Override
    public IValue defaultValue(){
        return new BoolValue(false);
    }

    @Override
    public String toString() {return "boolean"; }
}
