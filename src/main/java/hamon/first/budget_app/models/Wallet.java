package hamon.first.budget_app.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="Wallet")
public class Wallet {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;




    @OneToMany(mappedBy = "user")
    private List<User> users;
}
