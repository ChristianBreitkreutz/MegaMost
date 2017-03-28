package de.christianbr.megamost;

import de.christianbreitkreutz.MegaMost;
import de.christianbreitkreutz.MegaMostExeption;
import de.christianbreitkreutz.UriPort;
import de.christianbreitkreutz.UriScheme;

public class megamostTester {

    public static void main(String[] args) throws MegaMostExeption {
        MegaMost megatech = new MegaMost.Builder("httpbin.org", "post")//
                .port(UriPort.HTTPS)//
                .scheme(UriScheme.HTTPS)//
                .icon("huhu")//
                .useName("ahhah")//
                .build();
        megatech.sendMessage("hello world");

    }

}
