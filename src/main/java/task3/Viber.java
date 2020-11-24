package task3;

import java.util.Comparator;
import java.util.Objects;
import java.util.UUID;

public class Viber implements Messenger {

    private int id = 1;
    private Server server;

    public Viber(Server server) {
        this.server = server;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void sendMessage(String textMessage, Client clientIdTo, Client clientIdFrom) {
        if (clientIdTo.getClientMessenger().equals(clientIdFrom.getClientMessenger())) {
            Message message = new Message(textMessage, clientIdTo.getId(), clientIdFrom.getId(), this);
            server.addMessage(message);
        }
    }

    @Override
    public Message readMessage(Client owner, Client friend) {
        UUID friendId = friend.getId();
        UUID ownerId = owner.getId();
        return server
            .getListMessage(Viber.this)
            .stream()
            .filter(c -> (c.getClientIdTo().equals(ownerId) && c.getClientIdFrom().equals(friendId))
                || c.getClientIdFrom().equals(ownerId) && c.getClientIdTo().equals(friendId))
            .max(Comparator.comparing(Message::getTimeDispatch))
            .orElse(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Viber viber = (Viber) o;
        return id == viber.id &&
            Objects.equals(server, viber.server);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, server);
    }

}
