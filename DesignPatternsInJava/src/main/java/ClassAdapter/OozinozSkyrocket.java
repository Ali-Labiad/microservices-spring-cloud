package main.java.ClassAdapter;

public class OozinozSkyrocket extends Skyrocket {

    private PhysicalRocket physicalRocket;

    public OozinozSkyrocket(PhysicalRocket r) {
        super(r.getMass(0), r.getThrust(0), r.getBurnTime(0));
        this.physicalRocket = r;
    }

    @Override
    double getMass() {
        return physicalRocket.getMass(simTime);
    }

    @Override
    double getThrust() {
        return physicalRocket.getThrust(simTime);
    }
}
