package org.dci.theratrack.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "therapists",
    uniqueConstraints = @UniqueConstraint(columnNames = {"email", "phone"}))
public class Therapist {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @Size(max = 255)
  @Column(nullable = false)
  private String name;

  @NotNull
  @Size(max = 255)
  @Column(nullable = false)
  private String surname;

  @Email
  @Column(nullable = true, unique = true)
  private String email;

  @Pattern(regexp = "^\\+?[0-9]{10,15}$")
  @Column(nullable = true, length = 15)
  private String phone;

  @NotNull
  @Column(nullable = false, length = 50)
  private String gender = "Not specified"; // Default value if not set

  @Past
  @Column(nullable = true)
  private LocalDate birthDate;

  @ManyToMany(mappedBy = "therapists")
  private List<Patient> patients;

  @OneToMany(mappedBy = "therapist", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Appointment> appointments;


  @OneToOne
  @JoinColumn(name = "user_id", unique = true, referencedColumnName = "id", nullable = false)
  private User user;

}
