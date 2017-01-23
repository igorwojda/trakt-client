package com.hannesdorfmann.mosby.conductor.sample.dagger

import com.hannesdorfmann.mosby.conductor.sample.navigation.Navigator
import com.igorwojda.traktclient.core.dagger.ControllerScope
import dagger.Module
import dagger.Provides

@Module
class ControllerModule(private val navigator: Navigator) {

  @Provides
  @ControllerScope
  fun provideNavigator() = navigator
}