package net.floodlightcontroller.odin.master;


import java.net.InetAddress;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

import net.floodlightcontroller.util.MACAddress;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(using=OdinClientSerializer.class)
public class OdinClient implements Comparable {
	private final MACAddress hwAddress;
	private InetAddress ipAddress;
	private Lvap lvap;
	private ArrayList<Lvap> mvapList; //store the mvaps of this client
	private ConcurrentHashMap<TCPFlowID, Lvap> mapOfFlow;

	public ArrayList<Lvap> getMvapList() {
		return mvapList;
	}

	public ConcurrentHashMap<TCPFlowID, Lvap> getMapOfFlow() {
		return mapOfFlow;
	}

	public boolean hasFlowId(TCPFlowID flowID)
	{
		return mapOfFlow.containsKey(flowID);
	}

	public void addFlow(TCPFlowID flowID, Lvap vap)
	{
		mapOfFlow.put(flowID, vap);
	}

	// NOTE: Will need to add security token and temporal keys here later.
	// So make sure to pass OdinClient through interfaces of other classes
	// as opposed to the 4-LVAP properties now.



	public OdinClient (MACAddress hwAddress, InetAddress ipAddress, Lvap lvap) {
		this.hwAddress = hwAddress;
		this.ipAddress = ipAddress;
		this.lvap = lvap;
		this.mvapList = new ArrayList<Lvap>();
		this.mapOfFlow = new ConcurrentHashMap<TCPFlowID, Lvap>();
	}

	public void addMvap(Lvap lvap)
	{
		mvapList.add(lvap);
	}

	public void removeMvap(Lvap lvap)
	{
		mvapList.remove(lvap);
	}

	public boolean hasMvap(Lvap lvap)
	{
		return mvapList.contains(lvap);
	}
	/**
	 * find if there is a specific agent which one of the mvap locates in
	 * @param agent
	 * @return
	 */
	public Lvap findMvapWithSpecAgent(IOdinAgent agent)
	{
		for (Lvap mvap : mvapList)
		{
			if(mvap.getAgent() == agent)
			{
				return mvap;
			}
		}
		return null;
	}


	/**
	 * STA's MAC address. We assume one per client here.
	 * (Implies, no support for FMC yet) :)
	 *
	 * @return client's MAC address
	 */
	public MACAddress getMacAddress() {
		return this.hwAddress;
	}


	/**
	 * Get the clien'ts IP address.
	 * @return
	 */
	public InetAddress getIpAddress() {
		return ipAddress;
	}


	/**
	 * Set the client's IP address
	 * @param addr
	 */
	public void setIpAddress(InetAddress addr) {
		this.ipAddress = addr;
	}


	/**
	 * Get the client's lvap object
	 * @return lvap
	 */
	public Lvap getLvap() {
		return lvap;
	}


	/**
	 * Set the client's lvap
	 */
	public void setLvap() {
		this.lvap = lvap;
	}


	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof OdinClient))
			return false;

		if (obj == this)
			return true;

		OdinClient that = (OdinClient) obj;

		return (this.hwAddress.equals(that.hwAddress));
	}


	@Override
	public int compareTo(Object o) {
		assert (o instanceof OdinClient);

		if (this.hwAddress.toLong() == ((OdinClient)o).hwAddress.toLong())
			return 0;

		if (this.hwAddress.toLong() > ((OdinClient)o).hwAddress.toLong())
			return 1;

		return -1;
	}
}
