package com.moodreel.data.tmdb;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation",
    "nullness:initialization.field.uninitialized"
})
public final class MovieRepositoryImpl_Factory implements Factory<MovieRepositoryImpl> {
  private final Provider<TmdbApi> tmdbApiProvider;

  public MovieRepositoryImpl_Factory(Provider<TmdbApi> tmdbApiProvider) {
    this.tmdbApiProvider = tmdbApiProvider;
  }

  @Override
  public MovieRepositoryImpl get() {
    return newInstance(tmdbApiProvider.get());
  }

  public static MovieRepositoryImpl_Factory create(Provider<TmdbApi> tmdbApiProvider) {
    return new MovieRepositoryImpl_Factory(tmdbApiProvider);
  }

  public static MovieRepositoryImpl newInstance(TmdbApi tmdbApi) {
    return new MovieRepositoryImpl(tmdbApi);
  }
}
