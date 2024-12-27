package isika.p3.amappli.entities.auth;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Permission {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long permissionId;

    @Column(unique=true)
    private String name;

    @ManyToMany
    @JoinTable (
        name = "Role_Permission_Association",
        joinColumns = @JoinColumn(name = "roleId"),
        inverseJoinColumns = @JoinColumn( name = "permissionId")
    )
    @Builder.Default
    private Set<Role> roles = new HashSet<Role>();
}
