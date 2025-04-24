package main.java.builder;

public class Computer {

    private String processor;
    private int memory;
    private int storage;
    private String graphicsCard;

    public Computer(String processor, int memory, int storage, String graphicsCard) {
        this.processor = processor;
        this.memory = memory;
        this.storage = storage;
        this.graphicsCard = graphicsCard;
    }
    public String getProcessor() {
        return processor;
    }
    public void setProcessor(String processor) {
        this.processor = processor;
    }
    public int getMemory() {
        return memory;
    }
    public void setMemory(int memory) {
        this.memory = memory;
    }
    public int getStorage() {
        return storage;
    }
    public void setStorage(int storage) {
        this.storage = storage;
    }
    public String getGraphicsCard() {
        return graphicsCard;
    }
    public void setGraphicsCard(String graphicsCard) {
        this.graphicsCard = graphicsCard;
    }

    

}
