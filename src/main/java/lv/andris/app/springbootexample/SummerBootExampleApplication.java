package lv.andris.app.springbootexample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@SpringBootApplication
public class SummerBootExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SummerBootExampleApplication.class, args);
	}
}


@RestController //  The job of @Controller is to create a Map of model object and find a view but @RestController
// simply return the object and object data is directly written into HTTP response as JSON or XML.

class HomeController{

	@Autowired
	private DringService dringService;

	@GetMapping
//	@ResponseBody
	public String home(){
		return "Hello! You are at home!";
	}

	@GetMapping("/sum")
	public String sum (@RequestParam("number1") Integer number1, @RequestParam ("number2") Integer number2){
		return String.valueOf((number1 + number2));
		// http://localhost:8080/sum?number1=5&number2=5
	}

	@GetMapping("/drink")
	public String drink (@RequestParam(value = "name", required = false) String personName){
		// http://localhost:8080/drink?name=Anatolijs
		if (personName == null){
			personName = "Janis";
		}
		return  personName + " will be drinking " + dringService.getMeSomeDring() + " this summer";

	}
}


@Service  // working as @Component

class DringService {

	private List<String> availableDrings = Arrays
			.asList("Water", "PinaCollada", "Aperol Spritz", "Bear", "Juice");

	public  String getMeSomeDring(){

		int randomChoise = new Random().nextInt(availableDrings.size());
		return availableDrings.get(randomChoise);
	}
}
