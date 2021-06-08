package project.recommendationandtroubleshooting.model.recommendation;

public class Mobility {

    public Mobility() {
    }


    public Mobility(Double mobility) {
        this.mobility = mobility;
    }

    private Double mobility;

    public Double getMobility() {
        return mobility;
    }

    public void setMobility(Double mobility) {
        this.mobility = mobility;
    }


	@Override
	public String toString() {
		return "Mobility [mobility=" + mobility + "]";
	}
    
    
}
