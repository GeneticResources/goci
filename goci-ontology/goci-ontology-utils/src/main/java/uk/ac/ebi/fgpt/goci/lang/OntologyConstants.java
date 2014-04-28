package uk.ac.ebi.fgpt.goci.lang;

/**
 * Some constants for IRIs from the ontology
 *
 * @author Tony Burdett
 * Date 26/01/12
 */
public class OntologyConstants {
    // Ontology IRIs
    public static final String EFO_ONTOLOGY_SCHEMA_IRI = "http://www.ebi.ac.uk/efo";
    public static final String GWAS_ONTOLOGY_SCHEMA_IRI = "http://rdf.ebi.ac.uk/terms/gwas";
    public static final String GWAS_ONTOLOGY_BASE_IRI = "http://rdf.ebi.ac.uk/dataset/gwas";

    // Class IRIs
    public static final String STUDY_CLASS_IRI = GWAS_ONTOLOGY_SCHEMA_IRI + "/Study";
    public static final String TRAIT_ASSOCIATION_CLASS_IRI = GWAS_ONTOLOGY_SCHEMA_IRI + "/TraitAssociation";
    public static final String SNP_CLASS_IRI = GWAS_ONTOLOGY_SCHEMA_IRI + "/SingleNucleotidePolymorphism";
    public static final String CYTOGENIC_REGION_CLASS_IRI = GWAS_ONTOLOGY_SCHEMA_IRI + "/CytogeneticRegion";
    public static final String CHROMOSOME_CLASS_IRI = GWAS_ONTOLOGY_SCHEMA_IRI + "/Chromosome";
    public static final String EXPERIMENTAL_FACTOR_CLASS_IRI = "http://www.ebi.ac.uk/efo/EFO_0000001";

    // Data Property IRIs
    public static final String HAS_AUTHOR_PROPERTY_IRI = GWAS_ONTOLOGY_SCHEMA_IRI + "/EFO_GD00039";
    public static final String HAS_BP_POSITION_PROPERTY_IRI = GWAS_ONTOLOGY_SCHEMA_IRI + "/EFO_GD00032";
    public static final String HAS_LENGTH_PROPERTY_IRI = GWAS_ONTOLOGY_SCHEMA_IRI + "/EFO_GD00034";
    public static final String HAS_NAME_PROPERTY_IRI = GWAS_ONTOLOGY_SCHEMA_IRI + "/EFO_GD00030";
    public static final String HAS_P_VALUE_PROPERTY_IRI = GWAS_ONTOLOGY_SCHEMA_IRI + "/EFO_GD00036";
    public static final String HAS_PUBLICATION_DATE_PROPERTY_IRI = GWAS_ONTOLOGY_SCHEMA_IRI + "/EFO_GD00040";
    public static final String HAS_PUBMED_ID_PROPERTY_IRI = GWAS_ONTOLOGY_SCHEMA_IRI + "/EFO_GD00038";
    public static final String HAS_SNP_REFERENCE_ID_PROPERTY_IRI = GWAS_ONTOLOGY_SCHEMA_IRI + "/EFO_GD00031";
    public static final String HAS_GWAS_TRAIT_NAME_PROPERTY_IRI = GWAS_ONTOLOGY_SCHEMA_IRI + "/EFO_GD00041";

    // Object Property IRIs
    public static final String HAS_PART_PROPERTY_IRI = "http://www.obofoundry.org/ro/ro.owl#has_part";
    public static final String LOCATED_IN_PROPERTY_IRI = "http://www.obofoundry.org/ro/ro.owl#located_in";
    public static final String LOCATION_OF_PROPERTY_IRI =  "http://www.obofoundry.org/ro/ro.owl#location_of";
    public static final String ASSOCIATED_WITH_PROPERTY_IRI = GWAS_ONTOLOGY_SCHEMA_IRI + "/associated_with";
    public static final String IS_ABOUT_IRI = "http://purl.obolibrary.org/obo/IAO_0000136";
    public static final String HAS_ABOUT_IRI = GWAS_ONTOLOGY_SCHEMA_IRI + "/has_about";
    public static final String PART_OF_PROPERTY_IRI = "http://www.obofoundry.org/ro/ro.owl#part_of";

    //Datatype IRIs
    public static final String DATE_TIME_DATA_TYPE_IRI = "http://www.w3.org/2001/XMLSchema#dateTime";
}
