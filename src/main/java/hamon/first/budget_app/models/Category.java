package hamon.first.budget_app.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "category")
public class Category {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "reasonCategory")
    private List<Event> reasonEvents;

    @OneToMany(mappedBy = "consequenceCategory")
    private List<Event> consequencesEvents;

    @Column(name = "survival_amount")
    private int survivalAmount;

    @Column(name = "created_at")
    private LocalDateTime created_at;

    @Column(name = "updated_at")
    private LocalDateTime updated_at;


}
