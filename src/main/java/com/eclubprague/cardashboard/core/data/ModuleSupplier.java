package com.eclubprague.cardashboard.core.data;

import com.eclubprague.cardashboard.core.modules.TestDisplayModule;
import com.eclubprague.cardashboard.core.modules.TestSimpleModule;
import com.eclubprague.cardashboard.core.modules.base.IModule;
import com.eclubprague.cardashboard.core.modules.base.models.resources.IconResource;
import com.eclubprague.cardashboard.core.modules.base.models.resources.StringResource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michael on 16. 7. 2015.
 * <p/>
 * Supplies module hierarchy. Currently for testing purposes only.
 */
public class ModuleSupplier {
    public static List<IModule> getHomeScreenModules() {
        List<IModule> modules = new ArrayList<>();
        String gm = "Google maps";
        StringBuilder sb = new StringBuilder();
        modules.add(new TestDisplayModule(
                null,
                StringResource.fromString("Speed"),
                IconResource.fromResourceId(com.eclubprague.cardashboard.core.R.drawable.ic_language_black_24dp),
                null,
                null,
                StringResource.fromString("kmph")
        ));
        for (int i = 0; i < 33; i++) {
            sb.append(gm.charAt(i % gm.length()));
            modules.add(new TestSimpleModule(null, StringResource.fromString(sb.toString()),
                    IconResource.fromResourceId(com.eclubprague.cardashboard.core.R.drawable.ic_language_black_24dp),
                    null, null));
        }
        return modules;
    }
}
