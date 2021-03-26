package org.pokecentral.fawedontsuggest;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.pokecentral.fawedontsuggest.fixes.FAWETransformer;

import java.util.Map;

@IFMLLoadingPlugin.MCVersion("1.12.2")
public class FAWEDontSuggest implements IFMLLoadingPlugin {
    @Override
    public String[] getASMTransformerClass() {
        return new String[] {
                FAWETransformer.class.getName()
        };
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {

    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }
}
