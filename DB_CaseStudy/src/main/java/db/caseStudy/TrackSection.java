package db.caseStudy;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Objects;

@Entity
@Data
public class TrackSection {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String trackSection;
    public TrackSection(Long idArg, String trackSectionArg) {
        id = idArg;
        trackSection = trackSectionArg;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTrackSection() {
        return trackSection;
    }

    public void setTrackSection(String trackSection) {
        this.trackSection = trackSection;
    }

    public TrackSection() {
        id = 0l;
        trackSection = "L";
    }
    @Override
    public String toString() {
        return "TrackSection{id:"+id+", section:"+trackSection+"}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrackSection that = (TrackSection) o;
        return Objects.equals(id, that.id) && Objects.equals(trackSection, that.trackSection);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, trackSection);
    }
}
