package main.java.builder;

public interface ComputerBuilder {

    Computer build();
    String getProcessor();
    int getMemory();
    int getStorage();
    String getGraphicsCard();

}
