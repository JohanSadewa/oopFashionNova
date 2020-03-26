package com.company;

import java.util.*;

class Item {
    public Integer id;
    public String nama;
    public String type;
    public Integer stock;
    public Integer price;
}

public class Main {

    public static void main(String[] args) {
        Integer option=0;
        Integer count= 0;
        Integer c;
        ItemDatabase database = new ItemDatabase();
        do {
            System.out.printf("~~~~~~~~~~~~~~~\nwelcome to fashion nova store\n~~~~~~~~~~~~~~~\n");
            System.out.printf("1.view catalouge\n2. sellproduct\n3. add product to catalouge\n4. Exit\nyour choise?>>");
            Scanner scan = new Scanner(System.in);

            option=scan.nextInt();
            scan.nextLine();
            switch (option){
                case 1:
                    System.out.printf("~~~~~~~~~~~~~~~\nwelcome to fashion nova store\n~~~~~~~~~~~~~~~\n");
                    if(database.isEmpty()){
                        System.out.println("nothing to display");
                    }
                    else {
                        for (var item : database.allItems()) {
                            System.out.println("cloth(id):"+item.id);
                            System.out.println("name:"+ item.nama);
                            System.out.println("price/pcs="+item.price);
                            System.out.println("stock="+item.stock);
                            System.out.println("material="+item.type);
                        }
                    }
                    break;
                case 2:
                    if (database.isEmpty()) {
                        System.out.println("No clothes on our store right now");
                    }
                    else {
                        System.out.println("cloth to buy(cloth id):");
                        int itemId = scan.nextInt();
                        scan.nextLine();
                        System.out.println("quantities:");
                        int stock = scan.nextInt();
                        scan.nextLine();
                        database.reduceItemStock(itemId,stock);
                        int price = database.getItemByID(itemId).get().price;
                        count =(stock * price);
                        System.out.println("price:Rp"+count);
                        int tax=count /10;
                        System.out.println("TAX:Rp" +tax);
                        count=count+(count/10);
                        System.out.println("your total is :Rp"+count);
                    }
                    break;
                case 3:
                    Item item = new Item();
                    do{
                        System.out.println("input cloth id(must be unique and numberic(1-100)):");
                        item.id=scan.nextInt();
                        scan.nextLine();
                    }while (item.id<=0 || item.id>=101);
                    do{
                        System.out.println("input cloth name:");
                        item.nama=scan.nextLine();
                    }while (item.nama.length()<5||item.nama.length()>25);
                    do{
                        System.out.println("input cloth material[cotton|silk , case sensitive]:");
                        item.type=scan.nextLine();
                    }while (!item.type.equals("cotton") && !item.type.equals("silk"));
                    do{
                        System.out.println("input stock[5-50]:");
                        item.stock=scan.nextInt();
                        scan.nextLine();
                    }while (item.stock<5||item.stock>50);
                    do{
                        System.out.println("input price[min 100000]:");
                        item.price=scan.nextInt();
                        scan.nextLine();
                    }while (item.price<100000);
                    System.out.println("cloth succesfully added.");
                    database.addItem(item);
                    break;
            }


        }while(option!=4);
        System.out.println("Thank you for using FashionNova program!");
    }
}
