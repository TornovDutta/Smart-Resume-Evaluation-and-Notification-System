package org.example.hiring.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobSeeker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String email;
    private Double ats;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "file_id", referencedColumnName = "fileid")
    private Resume resume;
}
