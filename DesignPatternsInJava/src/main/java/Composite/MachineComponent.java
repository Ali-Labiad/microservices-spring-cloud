package main.java.Composite;

import java.util.HashSet;
import java.util.Set;

public abstract class MachineComponent {

    protected int id;
    public MachineComponent(int id){
        this.id = id;
    }

    protected abstract int getMachineCount();
    protected abstract boolean isTree(Set set);
    boolean isTree(){
        return isTree(new HashSet<>());
    }

}
