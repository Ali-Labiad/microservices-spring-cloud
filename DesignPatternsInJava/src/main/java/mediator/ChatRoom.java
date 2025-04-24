package main.java.mediator;

import java.util.List;

public class ChatRoom implements ChatMediator {

    private List<User> users;

    public ChatRoom() {
    }

    public ChatRoom(List<User> users) {
        this.users = users;
    }

    @Override
    public void sendMessage(String message, User sender) {
        for (User user : users) {
            if (user != sender) {
                user.receiveMessage(message);
            }
        }
    }

    public void addUser(User user) {
        if (users.contains(user))
            users.add(user);
    }
}
