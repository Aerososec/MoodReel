package com.moodreel.data.tmdb.network;

import com.moodreel.data.tmdb.TmdbApiTokenProvider;
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
public final class AuthInterceptor_Factory implements Factory<AuthInterceptor> {
  private final Provider<TmdbApiTokenProvider> apiTokenProvider;

  public AuthInterceptor_Factory(Provider<TmdbApiTokenProvider> apiTokenProvider) {
    this.apiTokenProvider = apiTokenProvider;
  }

  @Override
  public AuthInterceptor get() {
    return newInstance(apiTokenProvider.get());
  }

  public static AuthInterceptor_Factory create(Provider<TmdbApiTokenProvider> apiTokenProvider) {
    return new AuthInterceptor_Factory(apiTokenProvider);
  }

  public static AuthInterceptor newInstance(TmdbApiTokenProvider apiTokenProvider) {
    return new AuthInterceptor(apiTokenProvider);
  }
}
