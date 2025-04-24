package main.java.builder;

public class ConcreteBuilder implements ComputerBuilder {

    private String processor;
    private int memory;
    private int storage;
    private String graphicsCard;

    public ConcreteBuilder() {
    }

    @Override
    public ComputerBuilder setProcessor(String processor) {
        this.processor = processor;
        return this;
    }

    @Override
    public ComputerBuilder setMemory(int memory) {
        this.memory = memory;
        return this;
    }

    @Override
    public ComputerBuilder setStorage(int storage) {
        this.storage = storage;
        return this;
    }

    @Override
    public ComputerBuilder setGraphicsCard(String graphicsCard) {
        this.graphicsCard = graphicsCard;
        return this;
    }

    @Override
    public Computer build() {
        return new Computer(processor, memory, storage, graphicsCard);
    }

    @Override
    public String getProcessor() {
       return processor;
    }

    @Override
    public int getMemory() {
       return memory;
    }

    @Override
    public int getStorage() {
       return storage;
    }

    @Override
    public String getGraphicsCard() {
        return graphicsCard;
    }

    public static class Builder {
    
        private String processor;
        private int memory;
        private int storage;
        private String graphicsCard;

        
        
    }

}
