package client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by dauut on 05/01/16.
 */
public class Main {
    private static Scanner input2;

	public static void main(String[] args) {
    	// TODO Auto-generated method stub
    			TableOperations tb = new TableOperations();
    			String tableName = null;
    			ArrayList<String> cfNames = new ArrayList<String>();
    			String colmFamBuffer = "enter";

    			int choise = 0;
    			Scanner input = new Scanner(System.in);
    			Scanner input1 = new Scanner(System.in);
    			input2 = new Scanner(System.in);
    			
    			System.out.println("Whic op? \n1-CreateTable\n2-DeleteTable\n3-List Tables");
    			choise = input.nextInt();
    			if ((choise == 1)) {
    				System.out.println("Table name");
    				tableName = input1.nextLine();
    				while (!colmFamBuffer.equals("close")) {
    					System.out.println("Enter Colm Fam Name ------------ type \"close\" for exit -------------");
    					colmFamBuffer = input2.nextLine();
    					cfNames.add(colmFamBuffer);
    				}
    			}
    			
    			switch (choise) {
    			case 1:
    				tb.createTableOp(tableName,cfNames);
    				break;
    			case 2:
    				System.out.println("Table name");
    				tableName = input1.nextLine();
    				tb.deleteTableOp(tableName);
    				break;
    			case 3:
    				try {
    					tb.listTables();
    				} catch (IOException e) {
    					e.printStackTrace();
    				}
    			default:
    				break;
    			}
    			input.close();
    			input1.close();
    		}
}
