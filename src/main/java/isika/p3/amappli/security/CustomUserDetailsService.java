package isika.p3.amappli.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import isika.p3.amappli.entities.auth.Permission;
import isika.p3.amappli.entities.user.User;
import isika.p3.amappli.repo.amap.UserRepository;

@Service
@Primary
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       
        User user = userRepository.findByEmail(username);

        if(user == null){
            throw new UsernameNotFoundException("Utilisateur non trouvé avec l'email: "+ username);
        }

        //userRepository.flush();
        // If the user is found, get his permissions
        //Set<Permission> permissions = userRepository.findPermissionsByEmail(username);

        CustomUserDetails securityUser = new CustomUserDetails.Builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .authorities(getGrantedAuthorities(user.getPermissions()))
                .build();
        
        securityUser.addAdditionalInfo("userId",user.getUserId());
        if(user.getContactInfo().getFirstName() != null){
            securityUser.addAdditionalInfo("firstName",user.getContactInfo().getFirstName());
        }
        return securityUser;

    }

    // Transform our permissions into spring security authorities
    private List<GrantedAuthority> getGrantedAuthorities(Set<Permission> permissions){
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for(Permission p: permissions){
            authorities.add(new SimpleGrantedAuthority(p.getName()));
        }
        authorities.add(new SimpleGrantedAuthority("sampleauthority"));
        return authorities;
    }

    
}
