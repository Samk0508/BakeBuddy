package com.anakie.restApiBakery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@ComponentScan
@SpringBootApplication
@EntityScan(basePackages = "com.anakie.restApiBakery.entity")
public class RestApiBakeryApplication {
	
	public static void main(String[] args) {

		SpringApplication.run(RestApiBakeryApplication.class, args);
		String customAsciiArt =
				 "                                                          \n" +
						    "    )           )           (          (     (           \n" +
						    " ( /(     )  ( /(    (    ( )\\    (    )\\ )  )\\ )  (     \n" +
						    " )\\()) ( /(  )\\())  ))\\   )((_)  ))\\  (()/( (()/(  )\\ )  \n" +
						    "((_\\  )(_))((_\\  /((_) ((_)_  /((_)  ((_)) ((_))(()/(  \n" +
						    "| |(_)((_)_ | |(_)(_))    | _ )(_))(   _| |  _| |  )(_)) \n" +
						    "| '_ \\/ _` || / / / -_)   | _ \\| || |/ _` |/ _` | | || | \n" +
						    "|_.__/\\__,_||_\\_\\ \\___|   |___/ \\_,_|\\__,_|\\__,_|  \\_, | \n" +
						    "                                                   |__/  " ;

		System.out.println(customAsciiArt);


	}

}
