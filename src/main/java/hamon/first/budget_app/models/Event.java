package hamon.first.budget_app.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "event")
@NoArgsConstructor
public class Event {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "reason_category_id", referencedColumnName = "id")
    @JsonIgnore
    private Category reasonCategory;

    @ManyToOne
    @JoinColumn(name = "consequence_category_id", referencedColumnName = "id")
    @JsonIgnore
    private Category consequenceCategory;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnore
    private User user;

    @Column(name = "description")
    private String description;

    @Column(name = "chance")
    private double chance;

    @Column(name = "good")
    private boolean good;

    @Column(name = "amount")
    private int amount;

    @Column(name = "death")
    private boolean death;
}
