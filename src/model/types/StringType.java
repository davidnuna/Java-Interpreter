package model.types;

import model.values.*;

public class StringType implements IType{
    public boolean equals(Object other){
        return other instanceof StringType;
    }

    @Override
    public IValue defaultValue(){
        return new StringValue("");
    }

    @Override
    public String toString() {return "string"; }
}
