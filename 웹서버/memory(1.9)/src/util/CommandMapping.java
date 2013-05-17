package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import controller.Command;

public class CommandMapping {
	private static HashMap<String, Command> commandList= new HashMap<String, Command>();

	public static void mapping(String path){
		Properties props=new Properties();
		try {
			props.load(new FileInputStream(path));
			Set keyList=props.keySet();
			Iterator iter=keyList.iterator();
			String key = null;
			Command command=null;
				
			while(iter.hasNext()){

				key=(String)iter.next();
				System.out.println(key);
				command =(Command)Class.forName(props.getProperty(key)).newInstance();
				System.out.println(command);
				//System.out.println(key + command);
				commandList.put(key, command);
			}
	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static Command getCommand(String action){
		Command command=null;
		System.out.println(commandList.get(action));
		command=commandList.get(action);
		System.out.println("getCommand = " + command);
		return command;
	}
}