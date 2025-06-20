package ra.web.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDate dob;

    @Column(length = 100, nullable = false, unique = true)
    private String email;

    @Column(nullable = false, columnDefinition = "BIT")
    private Boolean sex; // Nam = true (1), Nữ = false (0)

    @Column(length = 20, unique = true, nullable = true)
    private String phone;

    @Column(nullable = false)
    private String password;

    @Column(name = "create_at", updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @CreationTimestamp
    private LocalDateTime createAt;

    @Column(nullable = false, columnDefinition = "BIT")
    private Boolean role; // Student = false (0), Admin = true (1)

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Enrollment> enrollments;
}
