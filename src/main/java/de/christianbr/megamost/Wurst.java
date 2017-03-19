package de.christianbr.megamost;

import de.christianbreitkreutz.MegaMost;
import de.christianbreitkreutz.MegaMostExeption;

public class Wurst {

    public static void main(String[] args) throws MegaMostExeption {
        MegaMost megamost = MegaMost.create("https://httpbin.org", "post");
        megamost.sendMessage("aufgehts");
    }

}
