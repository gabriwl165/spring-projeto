package curso.springboot.springboot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

class SpringbootApplicationTests {

	public static void main(String[] args) {
		
		BCryptPasswordEncoder senha = new BCryptPasswordEncoder();
		
		String result = senha.encode("admin");
		String result2 = "admin";
		System.out.println(senha.matches(result, result2));
		
	}

}
