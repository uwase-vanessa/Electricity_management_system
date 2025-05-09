package com.example.electricity_management_system.model;
import com.example.electricity_management_system.dto.UserRegisterDto;
import com.example.electricity_management_system.utils.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Entity
@Table(name="users")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String names;
    @JsonIgnore
    public String password;

    public String phoneNumber;
    public String email;
    @Column(unique = true,length = 16)
    public String nationalID;
    @JsonIgnore
    public Role role=Role.ROLE_CUSTOMER;
    @OneToMany(mappedBy =
            "user", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
    private List<MeterModel> meters;

    public UserModel(UserRegisterDto user) {
        this.names = user.names;
        this.password = user.password;
        this.phoneNumber = user.phoneNumber;
        this.email = user.email;
        this.nationalID = user.nationalID;
        this.role =user.role;
    }
}
