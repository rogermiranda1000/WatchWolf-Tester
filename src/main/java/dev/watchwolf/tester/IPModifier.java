package dev.watchwolf.tester;

/**
 * Due to WSL networking we have to change the returned IP
 */
public interface IPModifier {
    String modifyIp(String ip);
}
