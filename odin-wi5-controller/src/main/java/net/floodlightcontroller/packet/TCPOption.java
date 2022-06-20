package net.floodlightcontroller.packet;

public class TCPOption {
    public int kind;
    public int length;
    public byte[] data;
    public TCPOption(int kind, int length, byte[] data)
    {
        this.kind = kind;
        this.length = length;
        this.data = data;
    }

}