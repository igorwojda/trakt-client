package com.igorwojda.traktclient.core.dagger

import com.igorwojda.traktclient.core.mvp.conductor.navigation.Navigator
import dagger.Module
import dagger.Provides

@Module
class ControllerModule(private val navigator: Navigator) {

  @Provides
  @ControllerScope
  fun provideNavigator() = navigator
}