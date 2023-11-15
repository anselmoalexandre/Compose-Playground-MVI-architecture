package mz.co.bilheteira.data.repository;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import mz.co.bilheteira.database.dao.LocationDao;
import mz.co.bilheteira.network.data.api.LocationApiService;

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
    "KotlinInternalInJava"
})
public final class RepositoryImpl_Factory implements Factory<RepositoryImpl> {
  private final Provider<LocationDao> locationDaoProvider;

  private final Provider<LocationApiService> locationApiServiceProvider;

  public RepositoryImpl_Factory(Provider<LocationDao> locationDaoProvider,
      Provider<LocationApiService> locationApiServiceProvider) {
    this.locationDaoProvider = locationDaoProvider;
    this.locationApiServiceProvider = locationApiServiceProvider;
  }

  @Override
  public RepositoryImpl get() {
    return newInstance(locationDaoProvider.get(), locationApiServiceProvider.get());
  }

  public static RepositoryImpl_Factory create(Provider<LocationDao> locationDaoProvider,
      Provider<LocationApiService> locationApiServiceProvider) {
    return new RepositoryImpl_Factory(locationDaoProvider, locationApiServiceProvider);
  }

  public static RepositoryImpl newInstance(LocationDao locationDao,
      LocationApiService locationApiService) {
    return new RepositoryImpl(locationDao, locationApiService);
  }
}
