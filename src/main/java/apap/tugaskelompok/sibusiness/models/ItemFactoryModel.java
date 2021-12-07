package apap.tugaskelompok.sibusiness.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.catalina.User;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "item_factory")
public class ItemFactoryModel {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name="status", nullable = true)
    private Integer status;

    @NotNull
    @Column(name="stock", nullable = false)
    private Integer stock;

    @NotNull
    @Column(name="price", nullable = false)
    private Integer price;

    @NotNull
    @Column(name="category", nullable = false)
    private Integer category;

    @NotNull
    @Size(max = 50)
    @Column(name = "cluster", nullable = true)
    private String cluster;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private UserModel approver;

}
