package main.java.builder;

BorderFactory

public class Client {
    public static void main(String[] args) {
        ConcreteBuilder concreteBuilder = new ConcreteBuilder();
        Computer computer = concreteBuilder.setGraphicsCard("card")
                                .setMemory(0).build();
        System.out.println(computer.getGraphicsCard());
        System.out.println(computer.getMemory());
    }
}
