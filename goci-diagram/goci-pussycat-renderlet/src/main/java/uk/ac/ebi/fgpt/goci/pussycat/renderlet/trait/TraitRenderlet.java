package uk.ac.ebi.fgpt.goci.pussycat.renderlet.trait;

import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLOntology;
import uk.ac.ebi.fgpt.goci.pussycat.renderlet.Renderlet;
import uk.ac.ebi.fgpt.goci.pussycat.renderlet.RenderletNexus;

/**
 * Created by IntelliJ IDEA.
 * User: dwelter
 * Date: 06/03/12
 * Time: 13:51
 * To change this template use File | Settings | File Templates.
 */

abstract class TraitRenderlet implements Renderlet<OWLOntology, OWLIndividual> {

    /*
    * TraitRenderlet should retrieve the appropriate RGB colour for the trait being rendered from a hardcoded resource file
    *
    *
    * */

    @Override
    public String getName() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getDisplayName() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getDescription() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean canRender(RenderletNexus nexus, Object renderingContext, Object renderingEntity) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override public String render(RenderletNexus nexus, OWLOntology renderingContext, OWLIndividual renderingEntity) {
        return null;
    }
}
