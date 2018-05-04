package user.service;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class UserController {

    @Value("${message:Hello default}")
    private String message;
    
	@Autowired
	UserRepository userRepository;

	@GetMapping("/users")
	public Iterable<User> getAllUsers() {
		return userRepository.findAll();
	}

	@PostMapping("/users")
	public User createUser(@Valid @RequestBody User user) {
		return userRepository.save(user);
	}

	@GetMapping(value = "/users/{uid}")
	public ResponseEntity<User> getUserById(@PathVariable("uid") String uid) {
		Optional<User> user = userRepository.findById(uid);
		if (user == null) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<User>(user.get(), HttpStatus.OK);
		}
	}

	@PutMapping(value = "/users/{uid}")
	public ResponseEntity<User> updateUser(@PathVariable("uid") String uid, @Valid @RequestBody User user) {
		Optional<User> userData = userRepository.findById(uid);
		if (userData == null) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		userData.get().setName(user.getName());
		User updatedUser = userRepository.save(userData.get());
		return new ResponseEntity<User>(updatedUser, HttpStatus.OK);
	}

	@DeleteMapping(value = "/users/{uid}")
	public void deleteUser(@PathVariable("uid") String uid) {
		userRepository.deleteById(uid);
	}
	
    @RequestMapping("/message")
    String getMessage() {
        return this.message;
    }

}