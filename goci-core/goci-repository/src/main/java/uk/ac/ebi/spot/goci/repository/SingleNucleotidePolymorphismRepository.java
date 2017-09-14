package uk.ac.ebi.spot.goci.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import uk.ac.ebi.spot.goci.model.SingleNucleotidePolymorphism;

import java.util.Collection;
import java.util.List;


/**
 * Created by emma on 21/11/14.
 *
 * @author emma
 *         <p>
 *         Repository accessing Single Nucleotide Polymorphism entity objectls
 */

@RepositoryRestResource
public interface SingleNucleotidePolymorphismRepository extends JpaRepository<SingleNucleotidePolymorphism, Long> {
    @RestResource(exported = false)
    SingleNucleotidePolymorphism findByRsId(@Param("rsId") String rsId);

    @RestResource(path = "findByRsId", rel = "findByRsId")
    SingleNucleotidePolymorphism findByRsIdIgnoreCase(@Param("rsId") String rsId);

    Collection<SingleNucleotidePolymorphism> findByRiskAllelesLociAssociationStudyId(Long studyId);

    @RestResource(path = "findByPubmedId", rel = "findByPubmedId")
    Page<SingleNucleotidePolymorphism> findByRiskAllelesLociAssociationStudyPubmedId(String pubmedId, Pageable pageable);

    Collection<SingleNucleotidePolymorphism> findByRiskAllelesLociAssociationId(Long associationId);

    Collection<SingleNucleotidePolymorphism> findByRiskAllelesLociAssociationStudyDiseaseTraitId(Long traitId);

    List<SingleNucleotidePolymorphism> findByLocationsId(Long locationId);

    @RestResource(path = "findByBpLocation", rel = "findByBpLocation")
    List<SingleNucleotidePolymorphism> findByLocationsChromosomePosition(@Param("bpLocation") int chromosomePosition);

//    List<SingleNucleotidePolymorphism> findByLocationsChromosomeNameAndLocationsChromosomePositionBetween(@Param("chrom") String chromosomeName, @Param("bpStart") int start, @Param("bpEnd") int end);

    @RestResource(path = "findByChromBpLocation", rel = "findByChromBpLocation")
    Page<SingleNucleotidePolymorphism> findByLocationsChromosomeNameAndLocationsChromosomePositionBetween(@Param("chrom") String chromosomeName, @Param("bpStart") int start, @Param("bpEnd") int end, Pageable pageable);

    Collection<SingleNucleotidePolymorphism> findByRiskAllelesLociId(Long locusId);

    @RestResource(path = "findByGene", rel = "findByGene")
    Page<SingleNucleotidePolymorphism> findByGenesGeneName(String geneName, Pageable pageable);
}

