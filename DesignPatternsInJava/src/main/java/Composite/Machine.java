package main.java.Composite;

import java.util.Set;

public class Machine extends MachineComponent {

    
    public Machine(int id) {
        super(id);
    }

    @Override
    public int getMachineCount() {
       return 1;
    }

    @Override
    protected boolean isTree(Set visited) {
        visited.add(this);
        return true;
    }

}
