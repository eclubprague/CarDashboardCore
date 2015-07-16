package com.eclubprague.cardashboard.core.data;

import com.eclubprague.cardashboard.core.R;
import com.eclubprague.cardashboard.core.modules.TestDisplayModule;
import com.eclubprague.cardashboard.core.modules.TestSimpleModule;
import com.eclubprague.cardashboard.core.modules.base.IModule;
import com.eclubprague.cardashboard.core.modules.base.models.ModuleUpdateEvent;
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
        TestDisplayModule testDisplayModule;
        modules.add(testDisplayModule = new TestDisplayModule(
                null,
                StringResource.fromString("Speed"),
                IconResource.fromResourceId(R.drawable.ic_directions_car_black_24dp),
                null,
                null,
                StringResource.fromString("kmph")
        ));
        modules.add(testDisplayModule = new TestDisplayModule(
                null,
                StringResource.fromString("Settings"),
                IconResource.fromResourceId(R.drawable.ic_settings_black_24dp),
                null,
                null,
                StringResource.fromString("clicks")
        ));
        testDisplayModule.onEventMainThread(new ModuleUpdateEvent(Integer.toString(999)));
        modules.add(new TestSimpleModule(
                null,
                StringResource.fromString("Settings"),
                IconResource.fromResourceId(R.drawable.ic_settings_black_24dp),
                null, null));
        modules.add(new TestSimpleModule(
                null,
                StringResource.fromString("OBD"),
                IconResource.fromResourceId(R.drawable.ic_directions_car_black_24dp),
                null, null));
        modules.add(new TestSimpleModule(
                null,
                StringResource.fromString("Voice input"),
                IconResource.fromResourceId(R.drawable.ic_mic_black_24dp),
                null, null));
        modules.add(new TestSimpleModule(
                null,
                StringResource.fromString("Google maps"),
                IconResource.fromResourceId(R.drawable.ic_map_black_24dp),
                null, null));
        modules.add(new TestSimpleModule(
                null,
                StringResource.fromString("SMS"),
                IconResource.fromResourceId(R.drawable.ic_chat_black_24dp),
                null, null));
        modules.add(new TestSimpleModule(
                null,
                StringResource.fromString("Email"),
                IconResource.fromResourceId(R.drawable.ic_email_black_24dp),
                null, null));
        String gm = "Google maps";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 33; i++) {
            sb.append(gm.charAt(i % gm.length()));
            modules.add(new TestSimpleModule(null, StringResource.fromString(sb.toString()),
                    IconResource.fromResourceId(R.drawable.ic_settings_black_24dp),
                    null, null));
        }
        return modules;
    }
}
