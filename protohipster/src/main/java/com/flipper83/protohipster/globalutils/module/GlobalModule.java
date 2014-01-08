package com.flipper83.protohipster.globalutils.module;


import com.flipper83.protohipster.globalutils.interfaces.Logger;
import com.flipper83.protohipster.feed.view.ui.ProtoActivity;

import dagger.Module;
import dagger.Provides;

@Module(injects = {ProtoActivity.class}, staticInjections = {LoggerProvider.class}, complete = false)
public class GlobalModule {

    @Provides
    Logger provideLogger() {
        return TLog.getLogger();
    }

}
