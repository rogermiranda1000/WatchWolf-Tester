package dev.watchwolf.client;

public interface MessageNotifier {
    void onMessage(String username, String message);
}
