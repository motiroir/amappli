package isika.p3.amappli.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import isika.p3.amappli.entities.auth.Permission;
import isika.p3.amappli.entities.tenancy.Tenancy;
import isika.p3.amappli.entities.user.User;
import isika.p3.amappli.repo.amap.UserRepository;

@Service
@Primary
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("trying to authenticate");
        User user = userRepository.findByEmail(username);
        System.out.println("trying to authenticate 2");
        if(user == null){
            throw new UsernameNotFoundException("Utilisateur non trouvé avec l'email: "+ username);
        }
        System.out.println(user.getEmail());

        System.out.println("Matches: " + passwordEncoder.matches("AMAPamap11@", passwordEncoder.encode("AMAPamap11@")));
        //userRepository.flush();
        // If the user is found, get his permissions
        //Set<Permission> permissions = userRepository.findPermissionsByEmail(username);
        CustomUserDetails securityUser = null;
      //  if(user.getTenancy() != null){
            securityUser = new CustomUserDetails.Builder()
                    .username(user.getEmail())
                    .password(user.getPassword())
                    .authorities(getGrantedAuthorities(user.getPermissions(), user.getTenancy()))
                    .build();
        // }
        // else{
        //     securityUser = new CustomUserDetails.Builder()
        //             .username(user.getEmail())
        //             .password(user.getPassword())
        //             .authorities(getGrantedAuthorities(user.getPermissions(), null))
        //             .build();
        // }
        System.out.println("trying to authenticate 3");
        System.out.println("the retrieved password : " );
        System.out.println(user.getPassword());
        securityUser.addAdditionalInfo("userId",user.getUserId());
        if(user.getContactInfo() != null){
            securityUser.addAdditionalInfo("firstName",user.getContactInfo().getFirstName());
        }
        if(user.getTenancy() != null){
            securityUser.addAdditionalInfo("tenancyAlias",user.getTenancy().getTenancyAlias());
        }
        System.out.println(securityUser);
        return securityUser;

    }

    // Transform our permissions and tenancy membership into spring security authorities
    private List<GrantedAuthority> getGrantedAuthorities(Set<Permission> permissions, Tenancy tenancy){
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for(Permission p: permissions){
            authorities.add(new SimpleGrantedAuthority(p.getName()));
        }
        if(tenancy != null){
            authorities.add(new SimpleGrantedAuthority(tenancy.getTenancyAlias()));
        }
        return authorities;
    }

    
}
