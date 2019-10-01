package com.telusko.demorest;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AlienRepository
{
	static List<Alien> aliensList = new ArrayList<Alien>();
	static Map<Integer, Alien> aliensMap = new HashMap<Integer, Alien>();
	
	static final String URL = "jdb:oracle:thin:amjad/amjad@localhost:1521:xe";
	static Connection connection;

	static
	{
		addAlien(new Alien(1, "Hasnain", 21));
		addAlien(new Alien(2, "Yasir", 42));
		addAlien(new Alien(3, "Alam", 90));
		addAlien(new Alien(4, "Kafeel", 63));
		addAlien(new Alien(5, "Junejo", 64));
		addAlien(new Alien(6, "Kamil", 34));
		addAlien(new Alien(7, "Ali", 63));
		addAlien(new Alien(8, "Wahid", 86));
		addAlien(new Alien(9, "Ilyas", 86));
	}

//	static
//	{
//		try
//		{
//			OracleDataSource oracleDataSource = new OracleDataSource();
//			oracleDataSource.setURL(URL);
//
//			connection = oracleDataSource.getConnection();
//			
//			System.out.println("Connection Successful");
//		} catch (Exception e)
//		{
//			e.printStackTrace();
//		}
//	}

	static List<Alien> getAliens()
	{
		return aliensList;
	}

	static void addAlien(Alien alien)
	{
		aliensList.add(alien);
		aliensMap.put(alien.getId(), alien);
	}

	static Alien getAlien(int id)
	{
		return aliensMap.get(id);
	}

}
