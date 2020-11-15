package com.atm.sa;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Client client1 = new Client(0001,1122,true);

        ArrayList<Client> list = new ArrayList<Client>();
        list.add(client1);
        System.out.println(client1.cardExist);


    }
}
