package com.moodreel.data.tmdb.network;

import com.moodreel.data.tmdb.TmdbApi;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import retrofit2.Retrofit;

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
public final class TmdbNetworkModule_ProvideTmdbApiFactory implements Factory<TmdbApi> {
  private final Provider<Retrofit> retrofitProvider;

  public TmdbNetworkModule_ProvideTmdbApiFactory(Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public TmdbApi get() {
    return provideTmdbApi(retrofitProvider.get());
  }

  public static TmdbNetworkModule_ProvideTmdbApiFactory create(
      Provider<Retrofit> retrofitProvider) {
    return new TmdbNetworkModule_ProvideTmdbApiFactory(retrofitProvider);
  }

  public static TmdbApi provideTmdbApi(Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(TmdbNetworkModule.INSTANCE.provideTmdbApi(retrofit));
  }
}
