package uk.ac.cf.nsa.team2.deskbookingapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * A data transfer object for transferring desk type data.
 */
public class DeskTypeDTO {

    @JsonProperty("deskTypeId")
    private int deskTypeId;
    @JsonProperty("deskTypeName")
    private String deskTypeName;
    @JsonProperty("deskTypeIntroduce")
    private String deskTypeIntroduce;


    public String getDeskTypeIntroduce() {
        return deskTypeIntroduce;
    }

    public void setDeskTypeIntroduce(String deskTypeIntroduce) {
        this.deskTypeIntroduce = deskTypeIntroduce;
    }



    public DeskTypeDTO(int deskTypeId, String deskTypeName, String deskTypeIntroduce) {
        this.deskTypeId = deskTypeId;
        this.deskTypeName = deskTypeName;
        this.deskTypeIntroduce = deskTypeIntroduce;

    }

    public DeskTypeDTO() {

    }

    public int getDeskTypeId() {
        return deskTypeId;
    }

    public void setDeskTypeId(int deskTypeId) {
        this.deskTypeId = deskTypeId;
    }

    public String getDeskTypeName() {
        return deskTypeName;
    }

    public void setDeskTypeName(String deskTypeName) {
        this.deskTypeName = deskTypeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeskTypeDTO that = (DeskTypeDTO) o;
        return deskTypeId == that.deskTypeId &&
                Objects.equals(deskTypeName, that.deskTypeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deskTypeId, deskTypeName);
    }

    public DeskTypeDTO(int deskTypeId, String deskTypeName) {
        this.deskTypeId = deskTypeId;
        this.deskTypeName = deskTypeName;
    }
}
