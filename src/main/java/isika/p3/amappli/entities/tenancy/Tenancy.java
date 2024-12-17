package isika.p3.amappli.entities.tenancy;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import isika.p3.amappli.entities.user.Address;
import isika.p3.amappli.entities.user.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tenancy {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tenancyId;

    @Column(nullable = false)
    private String tenancyName;

    @Embedded
    private Address address;
    
    private LocalDateTime dateCreated;

    private LocalDateTime dateLastModified;

    @OneToMany(targetEntity = User.class, mappedBy = "tenancy", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Builder.Default
    private Set<User> users = new HashSet<User>();

    @OneToOne(cascade = CascadeType.ALL)
    private Graphism graphism;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn( name="optionsId")
    private Options options;

    @OneToOne
    @JoinColumn(name = "homePageContentId")
    private HomePageContent homePageContent;

    @OneToOne
    @JoinColumn( name = "serviceSubscriptionId")
    private ServiceSubscription serviceSubscription;

}
