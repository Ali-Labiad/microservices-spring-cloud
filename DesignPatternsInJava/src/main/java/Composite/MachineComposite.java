package main.java.Composite;

import java.util.List;
import java.util.Set;

public class MachineComposite extends MachineComponent {

    public MachineComposite(int id) {
        super(id);
    }

    private List<MachineComponent> components;

    @Override
    public int getMachineCount() {
        int count = 0;
        for (MachineComponent mc : components) {
            count += mc.getMachineCount();
        }
        return count;
    }

    @Override
    protected boolean isTree(Set visited) {
        visited.add(this);

        for (MachineComponent mc : (Set<MachineComponent>) visited) {

            if (visited.contains(mc) || !mc.isTree(visited))
                return false;
        }

        return true;
    }

}
