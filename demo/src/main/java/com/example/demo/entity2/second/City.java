package com.example.demo.entity2.second;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder(toBuilder = true)
@Entity
@Table(name = "cities")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name="NAME")
    private String name;
    @Column(name="POPULATION")
    private int population;

//    public City() {
//    }
//
//    public City(Long id, String name, int population) {
//
//        this.id = id;
//        this.name = name;
//        this.population = population;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public int getPopulation() {
//        return population;
//    }
//
//    public void setPopulation(int population) {
//        this.population = population;
//    }
//
//    @Override
//    public int hashCode() {
//        int hash = 7;
//        hash = 79 * hash + Objects.hashCode(this.id);
//        hash = 79 * hash + Objects.hashCode(this.name);
//        hash = 79 * hash + this.population;
//        return hash;
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) {
//            return true;
//        }
//        if (obj == null) {
//            return false;
//        }
//        if (getClass() != obj.getClass()) {
//            return false;
//        }
//        final City other = (City) obj;
//        if (this.population != other.population) {
//            return false;
//        }
//        if (!Objects.equals(this.name, other.name)) {
//            return false;
//        }
//        return Objects.equals(this.id, other.id);
//    }
//
//    @Override
//    public String toString() {
//        final StringBuilder sb = new StringBuilder("City{");
//        sb.append("id=").append(id);
//        sb.append(", name='").append(name).append('\'');
//        sb.append(", population=").append(population);
//        sb.append('}');
//        return sb.toString();
//    }
}
