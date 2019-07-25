package com.congodb;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Database database = new Database();
        Table table = new Table();
        database.CreateDatabase("MyDataBaseDemo");
//        database.DeleteDatabase("MyDataBaseDemo");

        System.out.println("++++++++++++++++");
        table.CreateTable("MyDataBaseDemo", "User");


    }
}
