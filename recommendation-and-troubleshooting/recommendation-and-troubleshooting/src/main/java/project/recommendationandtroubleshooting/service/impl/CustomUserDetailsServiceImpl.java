package project.recommendationandtroubleshooting.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import project.recommendationandtroubleshooting.model.Person;
import project.recommendationandtroubleshooting.repository.PersonRepository;


@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PersonRepository personRepository;

    // Funkcija koja na osnovu username-a iz baze vraca objekat User-a
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // ako se ne radi nasledjivanje, paziti gde sve treba da se proveri email

        Person person = personRepository.findByEmail(email);
        if (person == null) {
            throw new UsernameNotFoundException(String.format("No user found with email '%s'.", email));
        } else {
            return person;
        }
    }
}
