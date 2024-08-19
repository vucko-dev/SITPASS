package com.example.sitpass.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.time.LocalDate;

@Entity
@Table(name="USERS")
public class User implements UserDetails {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "email")
  private String email;
  @JsonIgnore
  @Column(name = "password")
  private String password;

  @Column(name = "name")
  private String firstName;

  @Column(name = "surname")
  private String lastName;

  @Column(name = "phone_number")
  private String phoneNumber;

  @Column(name = "address")
  private String address;

  @Column(name = "created_at")
  private LocalDate createdAt;

  @Column(name = "birthday")
  private LocalDate birthday;

  @Column(name = "city")
  private String city;

  @Column(name = "zip_code")
  private String zipCode;

  @Column(name = "enabled")
  private boolean enabled;

  @Column(name = "last_password_reset_date")
  private Timestamp lastPasswordResetDate;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "user_role",
    joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
  private List<Role> roles;

//  @Lob  // Ova anotacija označava BLOB ili CLOB polje.
//  @Column(name="image")
//  private byte[] image;

  @OneToOne(fetch = FetchType.EAGER)
  @JoinTable(name = "user_image",
    joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "image_id", referencedColumnName = "id"))
  private Image image;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return email;
  }

  public void setPassword(String password) {
    Timestamp now = new Timestamp(new Date().getTime());
    this.setLastPasswordResetDate(now);
    this.password = password;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setRoles(List<Role> roles) {
    this.roles = roles;
  }

  public List<Role> getRoles() {
    return roles;
  }

  @JsonIgnore
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.roles;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public LocalDate getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDate createdAt) {
    this.createdAt = createdAt;
  }


  public LocalDate getBirthday() {
    return birthday;
  }

  public void setBirthday(LocalDate birthday) {
    this.birthday = birthday;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getZipCode() {
    return zipCode;
  }

  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }

  @Override
  public boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  public Timestamp getLastPasswordResetDate() {
    return lastPasswordResetDate;
  }

  public void setLastPasswordResetDate(Timestamp lastPasswordResetDate) {
    this.lastPasswordResetDate = lastPasswordResetDate;
  }

  @JsonIgnore
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @JsonIgnore
  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @JsonIgnore
  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }


  public Image getImage() {
    return image;
  }

  public void setImage(Image image) {
    this.image = image;
  }
}
