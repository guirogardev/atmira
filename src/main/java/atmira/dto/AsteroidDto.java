package atmira.dto;

import java.time.LocalDate;

public class AsteroidDto {
	
    private	String name;
    private Boolean isPotentiallyHazardous;
    private Double estimatedDiameterMin;
    private Double estimatedDiameterMax;
    private Double relativeVelocityKilometersPerHour;
    private LocalDate approachDate;
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getIsPotentiallyHazardous() {
		return isPotentiallyHazardous;
	}
	public void setIsPotentiallyHazardous(Boolean isPotentiallyHazardous) {
		this.isPotentiallyHazardous = isPotentiallyHazardous;
	}
	public Double getEstimatedDiameterMin() {
		return estimatedDiameterMin;
	}
	public void setEstimatedDiameterMin(Double estimatedDiameterMin) {
		this.estimatedDiameterMin = estimatedDiameterMin;
	}
	public Double getEstimatedDiameterMax() {
		return estimatedDiameterMax;
	}
	public void setEstimatedDiameterMax(Double estimatedDiameterMax) {
		this.estimatedDiameterMax = estimatedDiameterMax;
	}
	public Double getRelativeVelocityKilometersPerHour() {
		return relativeVelocityKilometersPerHour;
	}
	public void setRelativeVelocityKilometersPerHour(Double relativeVelocityKilometersPerHour) {
		this.relativeVelocityKilometersPerHour = relativeVelocityKilometersPerHour;
	}
	public LocalDate getApproachDate() {
		return approachDate;
	}
	public void setApproachDate(LocalDate approachDate) {
		this.approachDate = approachDate;
	}
	
	public Double getAverageDiameter() {
		return (this.estimatedDiameterMax + this.estimatedDiameterMin)/2;
	}
    
}
