package model.types;

import model.values.*;

public class IntType implements IType{
    public boolean equals(Object other){
        return other instanceof IntType;
    }

    @Override
    public IValue defaultValue(){
        return new IntValue(0);
    }

    @Override
    public String toString() {return "int"; }
}
