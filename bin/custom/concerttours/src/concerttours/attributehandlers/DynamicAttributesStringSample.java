package concerttours.attributehandlers;

import de.hybris.platform.servicelayer.model.attribute.DynamicAttributeHandler;
import de.hybris.tutorial.model.ClientNameModel;

public class DynamicAttributesStringSample implements DynamicAttributeHandler<String, ClientNameModel>
{
    public static final String VALUE_DELIMITER = " ";

    @Override
    public String get(final ClientNameModel item)
    {
        if (item == null)
        {
            throw new IllegalArgumentException("Item model is required");
        }
        return item.getFirstName() + VALUE_DELIMITER + item.getLastName();
    }

    @Override
    public void set(final ClientNameModel item, final String value)
    {
        if (item != null && value != null)
        {
            final String[] split = value.split(VALUE_DELIMITER);
            item.setFirstName(split[0]);
            item.setLastName(split[1]);
        }
    }
}
