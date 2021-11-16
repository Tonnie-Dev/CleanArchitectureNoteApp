package com.plcoding.cleanarchitecturenoteapp.di;

import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module

//scope this to the application lifecycle to live throughout the app life
@InstallIn(SingletonComponent.class)
public class AppModule {
}
