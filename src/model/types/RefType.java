package model.types;

import model.values.*;

public class RefType implements IType{
    IType inner;

    public RefType(IType inner){ this.inner = inner; }

    public boolean equals(Object other){
        if (other instanceof RefType){
            return inner.equals(((RefType)other).getInner());
        }
        return false;
    }

    public IType getInner(){ return inner; }

    @Override
    public IValue defaultValue(){ return new RefValue(0, inner); }

    @Override
    public String toString() {return "ref(" + inner.toString() + ")"; }
}
