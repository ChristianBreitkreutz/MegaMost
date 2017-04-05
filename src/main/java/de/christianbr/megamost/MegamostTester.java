package de.christianbr.megamost;


import com.google.inject.Guice;
import com.google.inject.Injector;

import de.christianbreitkreutz.MegaMostExeption;
import de.christianbreitkreutz.test.A;
import de.christianbreitkreutz.test.AModule;

public class MegamostTester {


    public static void main(String[] args) throws MegaMostExeption {
//        MegaMost megatech = new MegaMost.Builder("httpbin.org", "post")//
//                .port(UriPort.HTTPS)//
//                .scheme(UriScheme.HTTPS)//
//                .icon("huhu")//
//                .useName("ahhah")//
//                .build();
//        megatech.sendMessage("hello world");

    	 Injector injector = Guice.createInjector(new AModule());
    	 A a = injector.getInstance(A.class);
    	 System.out.println(a.drink(1));
    }

}
