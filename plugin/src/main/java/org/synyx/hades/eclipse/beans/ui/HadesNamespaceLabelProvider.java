package org.synyx.hades.eclipse.beans.ui;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.graphics.Image;
import org.springframework.ide.eclipse.beans.core.model.IBean;
import org.springframework.ide.eclipse.beans.core.model.IBeansTypedString;
import org.springframework.ide.eclipse.beans.ui.BeansUIImages;
import org.springframework.ide.eclipse.beans.ui.namespaces.DefaultNamespaceLabelProvider;
import org.springframework.ide.eclipse.core.model.IModelElement;
import org.springframework.ide.eclipse.core.model.ISourceModelElement;
import org.synyx.hades.dao.query.QueryLookupStrategy;
import org.synyx.hades.eclipse.HadesUtils;


/**
 * Tweaks bean text to reflect the actual DAO interface rather than the factory.
 * Applies the defaults for properties, too, as they are not reflected at this
 * parsing stage.
 * 
 * @author Oliver Gierke - gierke@synyx.de
 */
public class HadesNamespaceLabelProvider extends DefaultNamespaceLabelProvider {

    private static final Map<String, Object> DEFAULTS;

    static {
        DEFAULTS = new HashMap<String, Object>();
        DEFAULTS.put("queryLookupStrategy", QueryLookupStrategy.getDefault());
    }


    /*
     * (non-Javadoc)
     * 
     * @seeorg.springframework.ide.eclipse.beans.ui.namespaces.
     * DefaultNamespaceLabelProvider
     * #getText(org.springframework.ide.eclipse.core.model.ISourceModelElement,
     * org.springframework.ide.eclipse.core.model.IModelElement, boolean)
     */
    @Override
    public String getText(ISourceModelElement element, IModelElement context,
            boolean isDecorating) {

        if (element instanceof IBean) {
            return HadesUtils.asText((IBean) element);
        }

        if (element instanceof IBeansTypedString) {

            String name = element.getElementParent().getElementName();
            String text = super.getText(element, context, isDecorating);

            boolean hasDefault = DEFAULTS.containsKey(name);
            boolean isNull = "null".equals(text);

            return hasDefault && isNull ? DEFAULTS.get(name).toString() : text;

        }

        return super.getText(element, context, isDecorating);
    }


    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.ide.eclipse.beans.ui.namespaces.
     * DefaultNamespaceLabelProvider
     * #getImage(org.springframework.ide.eclipse.core.model.ISourceModelElement,
     * org.springframework.ide.eclipse.core.model.IModelElement, boolean)
     */
    @Override
    public Image getImage(ISourceModelElement element, IModelElement context,
            boolean isDecorating) {

        if (element instanceof IBean) {
            return BeansUIImages.getImage(BeansUIImages.IMG_OBJS_BEAN);
        } else {
            return super.getImage(element, context, isDecorating);
        }
    }
}
