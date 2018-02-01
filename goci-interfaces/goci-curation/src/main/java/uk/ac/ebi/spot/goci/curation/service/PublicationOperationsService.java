package uk.ac.ebi.spot.goci.curation.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.ac.ebi.spot.goci.model.Author;
import uk.ac.ebi.spot.goci.model.Publication;
import uk.ac.ebi.spot.goci.model.Study;
import uk.ac.ebi.spot.goci.service.EuropepmcPubMedSearchService;
import uk.ac.ebi.spot.goci.service.PublicationService;
import uk.ac.ebi.spot.goci.service.StudyService;
import uk.ac.ebi.spot.goci.service.exception.PubmedLookupException;
import uk.ac.ebi.spot.goci.utils.EuropePMCData;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * PublicationOperationService provides a list of methods to add/update the publication and the relative authors.
 *
 * @author Cinzia
 * @date 23/10/17
 */


@Service
public class PublicationOperationsService {


    private PublicationService publicationService;

    private AuthorOperationsService authorOperationsService;

    private EuropepmcPubMedSearchService europepmcPubMedSearchService;

    @Autowired
    public PublicationOperationsService(PublicationService publicationService,
                                        AuthorOperationsService authorOperationsService,
                                        EuropepmcPubMedSearchService europepmcPubMedSearchService){
        this.publicationService = publicationService;
        this.europepmcPubMedSearchService = europepmcPubMedSearchService;
        this.authorOperationsService = authorOperationsService;
    }


    public List<String> listFirstAuthors() {
     return publicationService.findAllStudyAuthors();
    }


    public void addFirstAuthorToPublication(Publication publication, EuropePMCData europePMCResult) {
        Author firstAuthor = europePMCResult.getFirstAuthor();
        Author firstAuthorDB = authorOperationsService.findAuthorByFullname(firstAuthor.getFullname());
        publication.setFirstAuthor(firstAuthorDB);
        publicationService.save(publication);
    }

    @Transactional
    public Publication addPublication(String pubmedId, EuropePMCData europePMCResult, Boolean newImport)
            throws Exception {

        Publication publication = publicationService.createOrFindByPumedId(pubmedId);
        publication.setPubmedId(pubmedId);
        publication.setPublication(europePMCResult.getPublication().getPublication());
        publication.setTitle(europePMCResult.getPublication().getTitle());

        // The date was already curated. So we don't want to import again this data
        if (newImport) {
            publication.setPublicationDate(europePMCResult.getPublication().getPublicationDate());
        }

        publicationService.save(publication);
        authorOperationsService.addAuthorsToPublication(publication, europePMCResult);

        addFirstAuthorToPublication(publication, europePMCResult);

        return publication;
    }


    public Publication importSinglePublication(String pubmedId, Boolean newImport) throws PubmedLookupException {
        Publication addedPublication = null;
        try {
            EuropePMCData europePMCResult = europepmcPubMedSearchService.createStudyByPubmed(pubmedId);
            addedPublication = addPublication(pubmedId, europePMCResult, newImport);
        } catch(Exception exception) {
            throw new PubmedLookupException(exception.getCause().getMessage());

        }
        return addedPublication;
    }


    public Boolean importPublicationsWithoutFirstAuthor() {
        ArrayList<HashMap<String,String>> result = new ArrayList<>();
        String pubmedId;

        List<Publication> listPublications = publicationService.findByFirstAuthorIsNull();

        for (Publication publication : listPublications) {
            pubmedId = publication.getPubmedId();
            try {
                    Publication importedPublication = importSinglePublication(pubmedId, false);
            } catch (Exception exception) {
                System.out.println("Something went wrong "+ pubmedId );
                
            }
        }
        return true;

    }

    public Collection<Study> findStudiesByPubmedId(String pubmedId) {
        Collection<Study> studies = publicationService.findStudiesByPubmedId(pubmedId);
        return studies;
    }

    public Boolean changeFirstAuthorByStudyId(Long studyId, Long authorId) {
        Boolean success = false;
        Publication publication = publicationService.findByStudyId(studyId);
        if (publication != null) {
            Author author = authorOperationsService.findAuthorById(authorId);
            if (author != null) {
                publicationService.updatePublicationFirstAuthor(publication, author);
                success = true;
            }
        }
        return success;
    }

}
