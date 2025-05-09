package org.example.hiring.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer fileid;

    private String filename;
    private String filetype;
    @Lob
    private byte[] filedata;

    @Override
    public String toString() {
        return "Resume{" +
                "fileid=" + fileid +
                ", filename='" + filename + '\'' +
                ", filetype='" + filetype + '\'' +
                ", filedata=" + Arrays.toString(filedata) +
                '}';
    }
}
