package uk.ac.ebi.spot.goci.curation.model;

/**
 * Created by emma on 07/12/2015.
 *
 * @author emma
 *         <p>
 *         Object passed back to model that stores ID of last viewed association
 */
public class LastViewedAssociation {

    private Long id;

    public LastViewedAssociation() {
    }

    public LastViewedAssociation(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
