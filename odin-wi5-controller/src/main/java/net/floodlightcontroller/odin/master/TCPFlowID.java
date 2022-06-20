package net.floodlightcontroller.odin.master;

public class TCPFlowID {
    String ip_src;
    String ip_dst;
    short tcp_port_src;
    short tcp_port_dst;
    public TCPFlowID(String ip_src, String ip_dst, short tcp_port_src, short tcp_port_dst)
    {
        this.ip_src = ip_src;
        this.ip_dst = ip_dst;
        this.tcp_port_src = tcp_port_src;
        this.tcp_port_dst = tcp_port_dst;
    }
}
