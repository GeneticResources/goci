package uk.ac.ebi.spot.goci.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.ac.ebi.spot.goci.model.Association;
import uk.ac.ebi.spot.goci.model.Gene;
import uk.ac.ebi.spot.goci.model.Locus;
import uk.ac.ebi.spot.goci.model.RiskAllele;
import uk.ac.ebi.spot.goci.model.ValidationError;
import uk.ac.ebi.spot.goci.utils.ErrorProcessingService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by emma on 18/04/2016.
 *
 * @author emma
 *         <p>
 *         Class that runs various combinations of error checks
 */
@Service
public class ValidationChecksBuilder {

    private ErrorCreationService errorCreationService;

    private final Logger log = LoggerFactory.getLogger(getClass());

    protected Logger getLog() {
        return log;
    }

    @Autowired
    public ValidationChecksBuilder(ErrorCreationService errorCreationService) {
        this.errorCreationService = errorCreationService;
    }

    /**
     * Run p-value checks on a row annotation
     *
     * @param association association to be checked
     */
    public Collection<ValidationError> runPvalueChecks(Association association) {

        Collection<ValidationError> validationErrors = new ArrayList<>();
        validationErrors.add(errorCreationService.checkMantissaIsLessThan10(association));
        validationErrors.add(errorCreationService.checkExponentIsPresent(association));
        return ErrorProcessingService.checkForValidErrors(validationErrors);
    }

    /**
     * Run general checks on a row annotation
     *
     * @param association association to be checked
     */
    public Collection<ValidationError> runAnnotationChecks(Association association) {

        Collection<ValidationError> validationErrors = new ArrayList<>();
        validationErrors.add(errorCreationService.checkSnpType(association));

        if (association.getSnpInteraction()) {
            for (Locus locus : association.getLoci()) {
                locus.getStrongestRiskAlleles().forEach(riskAllele -> {
                    Boolean genomeWide = riskAllele.getGenomeWide();
                    Boolean limitedList = riskAllele.getLimitedList();
                    validationErrors.add(errorCreationService.checkSnpStatusIsPresent(genomeWide, limitedList));
                });
            }
        }

        return ErrorProcessingService.checkForValidErrors(validationErrors);
    }

    /**
     * Run OR checks on a row
     *
     * @param association association to be checked
     */
    public Collection<ValidationError> runOrChecks(Association association) {

        Collection<ValidationError> validationErrors = new ArrayList<>();

        ValidationError orIsPresent = errorCreationService.checkOrIsPresentAndMoreThanOne(association);
        validationErrors.add(orIsPresent);

        ValidationError betaFoundForOr = errorCreationService.checkBetaValuesIsEmpty(association);
        validationErrors.add(betaFoundForOr);

        ValidationError betaUnitFoundForOr = errorCreationService.checkBetaUnitIsEmpty(association);
        validationErrors.add(betaUnitFoundForOr);

        ValidationError betaDirectionFoundForOr =
                errorCreationService.checkBetaDirectionIsEmpty(association);
        validationErrors.add(betaDirectionFoundForOr);

        return ErrorProcessingService.checkForValidErrors(validationErrors);
    }


    /**
     * Run OR checks on a row
     *
     * @param association association to be checked
     */
    public Collection<ValidationError> runAuthorLevelOrChecks(Association association) {

        Collection<ValidationError> validationErrors = new ArrayList<>();

        ValidationError betaFoundForOr = errorCreationService.checkBetaValuesIsEmpty(association);
        validationErrors.add(betaFoundForOr);

        ValidationError betaUnitFoundForOr = errorCreationService.checkBetaUnitIsEmpty(association);
        validationErrors.add(betaUnitFoundForOr);

        ValidationError betaDirectionFoundForOr =
                errorCreationService.checkBetaDirectionIsEmpty(association);
        validationErrors.add(betaDirectionFoundForOr);

        ValidationError rangeNotFound =
                errorCreationService.checkRangeIsPresent(association);
        validationErrors.add(rangeNotFound);

        return ErrorProcessingService.checkForValidErrors(validationErrors);
    }


    /**
     * Run Beta checks on a row
     *
     * @param association row to be checked
     */
    public Collection<ValidationError> runBetaChecks(Association association) {

        Collection<ValidationError> validationErrors = new ArrayList<>();

        ValidationError betaIsPresent = errorCreationService.checkBetaIsPresentAndIsNotNegative(association);
        validationErrors.add(betaIsPresent);

        ValidationError betaUnitNotFound = errorCreationService.checkBetaUnitIsPresent(association);
        validationErrors.add(betaUnitNotFound);

        ValidationError betaDirectionNotFound =
                errorCreationService.checkBetaDirectionIsPresent(association);
        validationErrors.add(betaDirectionNotFound);

        ValidationError orFound = errorCreationService.checkOrEmpty(association);
        validationErrors.add(orFound);

        ValidationError orRecipFound = errorCreationService.checkOrRecipEmpty(association);
        validationErrors.add(orRecipFound);

        ValidationError orRecipRangeFound =
                errorCreationService.checkOrPerCopyRecipRangeIsEmpty(association);
        validationErrors.add(orRecipRangeFound);

        return ErrorProcessingService.checkForValidErrors(validationErrors);
    }

    /**
     * Run author level beta checks on a row
     *
     * @param association row to be checked
     */
    public Collection<ValidationError> runAuthorLevelBetaChecks(Association association) {
        Collection<ValidationError> validationErrors = new ArrayList<>();

        ValidationError betaIsPresent = errorCreationService.checkBetaIsPresentAndIsNotNegative(association);
        validationErrors.add(betaIsPresent);

        ValidationError betaUnitNotFound = errorCreationService.checkBetaUnitIsPresent(association);
        validationErrors.add(betaUnitNotFound);

        ValidationError betaDirectionNotFound =
                errorCreationService.checkBetaDirectionIsPresent(association);
        validationErrors.add(betaDirectionNotFound);

        ValidationError orFound = errorCreationService.checkOrEmpty(association);
        validationErrors.add(orFound);

        ValidationError rangeNotFound =
                errorCreationService.checkRangeIsPresent(association);
        validationErrors.add(rangeNotFound);

        return ErrorProcessingService.checkForValidErrors(validationErrors);
    }

    /**
     * Run no effect checks on a row
     *
     * @param association row to be checked
     */
    public Collection<ValidationError> runNoEffectErrors(Association association) {

        Collection<ValidationError> validationErrors = new ArrayList<>();

        ValidationError orFound = errorCreationService.checkOrEmpty(association);
        validationErrors.add(orFound);

        ValidationError orRecipFound = errorCreationService.checkOrRecipEmpty(association);
        validationErrors.add(orRecipFound);

        ValidationError orRecipRangeFound =
                errorCreationService.checkOrPerCopyRecipRangeIsEmpty(association);
        validationErrors.add(orRecipRangeFound);

        ValidationError betaFound = errorCreationService.checkBetaValuesIsEmpty(association);
        validationErrors.add(betaFound);

        ValidationError betaUnitFound = errorCreationService.checkBetaUnitIsEmpty(association);
        validationErrors.add(betaUnitFound);

        ValidationError betaDirectionFound =
                errorCreationService.checkBetaDirectionIsEmpty(association);
        validationErrors.add(betaDirectionFound);

        ValidationError rangeFound = errorCreationService.checkRangeIsEmpty(association);
        validationErrors.add(rangeFound);

        ValidationError standardErrorFound =
                errorCreationService.checkStandardErrorIsEmpty(association);
        validationErrors.add(standardErrorFound);

        ValidationError descriptionFound = errorCreationService.checkDescriptionIsEmpty(association);
        validationErrors.add(descriptionFound);

        return ErrorProcessingService.checkForValidErrors(validationErrors);
    }

    /**
     * Run no effect checks on a row
     *
     * @param association row to be checked
     */
    public Collection<ValidationError> runAuthorLevelNoEffectChecks(Association association) {

        Collection<ValidationError> validationErrors = new ArrayList<>();

        ValidationError orFound = errorCreationService.checkOrEmpty(association);
        validationErrors.add(orFound);

        ValidationError betaFound = errorCreationService.checkBetaValuesIsEmpty(association);
        validationErrors.add(betaFound);

        ValidationError betaUnitFound = errorCreationService.checkBetaUnitIsEmpty(association);
        validationErrors.add(betaUnitFound);

        ValidationError betaDirectionFound =
                errorCreationService.checkBetaDirectionIsEmpty(association);
        validationErrors.add(betaDirectionFound);

        ValidationError rangeFound = errorCreationService.checkRangeIsEmpty(association);
        validationErrors.add(rangeFound);

        return ErrorProcessingService.checkForValidErrors(validationErrors);
    }

    /**
     * Run loci attributes checks on association
     *
     * @param association association to be checked
     */
    public Collection<ValidationError> runLociAttributeChecks(Association association) {

        Collection<ValidationError> validationErrors = new ArrayList<>();
        Collection<Locus> loci = association.getLoci();

        if (loci != null) {
            for (Locus locus : association.getLoci()) {
                Collection<RiskAllele> riskAlleles = locus.getStrongestRiskAlleles();
                Collection<ValidationError> geneErrors = new ArrayList<>();

                // Firstly check all genes are valid
                if (!locus.getAuthorReportedGenes().isEmpty()) {
                    Set<String> locusGenes =
                            locus.getAuthorReportedGenes().stream().map(Gene::getGeneName).collect(Collectors.toSet());

                    locusGenes.forEach(geneName -> {
                        getLog().info("Checking gene: ".concat(geneName));
                        ValidationError geneError = errorCreationService.checkGene(geneName);
                        if (geneError.getError() != null) {
                            geneErrors.add(geneError);
                        }
                    });

                    if (!geneErrors.isEmpty()) {
                        validationErrors.addAll(geneErrors);
                    }
                }

                // Check risk allele attributes
                riskAlleles.forEach(riskAllele -> {

                    ValidationError riskAlleleError = errorCreationService.checkRiskAllele(riskAllele);
                    validationErrors.add(riskAlleleError);

                    // If gene is valid proceed to check gene and snp location
                    if (geneErrors.isEmpty()) {
                        Set<String> locusGenes =
                                locus.getAuthorReportedGenes()
                                        .stream()
                                        .map(Gene::getGeneName)
                                        .collect(Collectors.toSet());

                        locusGenes.forEach(geneName -> {
                            getLog().info("Checking snp/gene location: ".concat(geneName)
                                                  .concat(" ")
                                                  .concat(riskAllele.getSnp().getRsId()));
                            ValidationError snpGeneLocationError =
                                    errorCreationService.checkSnpGeneLocation(riskAllele.getSnp(), geneName);
                            validationErrors.add(snpGeneLocationError);
                        });
                    }
                    else {
                        // Check snp is valid
                        ValidationError snpError = errorCreationService.checkSnp(riskAllele.getSnp().getRsId());
                        validationErrors.add(snpError);
                    }
                });
            }
        }
        return ErrorProcessingService.checkForValidErrors(validationErrors);
    }

    /**
     * Run risk frequency checks on association and the risk alleles linked to a locus
     *
     * @param association row to be checked
     */
    public Collection<ValidationError> runRiskFrequencyChecks(Association association) {
        Collection<ValidationError> validationErrors = new ArrayList<>();

        // Check the association risk frequency
        validationErrors.add(errorCreationService.checkAssociationRiskFrequency(association));

        // Check risk frequency on alleles if its a SNP interaction association
        if (association.getSnpInteraction()) {
            for (Locus locus : association.getLoci()) {
                locus.getStrongestRiskAlleles().forEach(riskAllele -> {
                    validationErrors.add(errorCreationService.checkAlleleRiskFrequency(riskAllele));
                });
            }
        }

        return ErrorProcessingService.checkForValidErrors(validationErrors);
    }
}
