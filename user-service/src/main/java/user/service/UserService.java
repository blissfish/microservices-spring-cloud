package user.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class UserService implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(UserService.class);

	public static void main(String args[]) {
		SpringApplication.run(UserService.class, args);
	}

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public void run(String... arg0) throws Exception {
		log.info("Querying for user records ...");
		jdbcTemplate
				.query("SELECT uid, name FROM user ", (rs, rowNum) -> new User(rs.getString("uid"), rs.getString("name")))
				.forEach(user -> log.info(user.toString()));
	}

}