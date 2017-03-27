package de.christianbr.megamost;

import de.christianbreitkreutz.MegaMostExeption;
import de.christianbreitkreutz.Megatech;
import de.christianbreitkreutz.UriScheme;

public class megatechTester {

	public static void main(String[] args) throws MegaMostExeption {
		// TODO Auto-generated method stub
		Megatech megatech = new Megatech.Builder("httpbin.org", "post")
				.port(443)
				.scheme(UriScheme.HTTPS)
				.icon("huhu")
				.useName("ahhah")
				.build();
		megatech.sendMessage("hello world");

		}

}
