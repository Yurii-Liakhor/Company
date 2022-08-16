package com.example.company.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String jobName;

    @OneToMany(mappedBy = "job")
    private List<Worker> workers = new ArrayList<>();

    public Job(String jobName) {
        this.jobName = jobName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Job)) return false;
        Job j = (Job) o;
        return Objects.equals(jobName, j.getJobName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(jobName);
    }

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", jobName='" + jobName + '\'' +
                '}';
    }
}
