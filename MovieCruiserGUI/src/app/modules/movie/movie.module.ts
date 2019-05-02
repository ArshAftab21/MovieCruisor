import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { SharedModule } from '../shared/shared.module';
import { ThumbnailComponent } from './components/thumbnail/thumbnail.component';
import { ContainerComponent } from './components/container/container.component';
import { MovieDialogComponent } from './components/movie-dialog/movie-dialog.component';
import { SearchMovieComponent } from './components/search-movie/search-movie.component';
import { TmdbContainerComponent } from './components/tmdb-container/tmdb-container.component';
import { WatchlistComponent } from './components/watchlist/watchlist.component';
import { MovieService } from './movie-service.service';
import { MovieRouterModule } from './movie-router.module';
import { FormsModule } from '@angular/forms';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { TokenInterceptorService } from './token-interceptor.service';

@NgModule({
  declarations: [
    ThumbnailComponent,
    ContainerComponent,
    MovieDialogComponent,
    SearchMovieComponent,
    TmdbContainerComponent,
    WatchlistComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    HttpClientModule,
    MovieRouterModule,
    FormsModule
  ],
  entryComponents: [MovieDialogComponent],
  exports: [
    MovieRouterModule,
    ThumbnailComponent,
    MovieDialogComponent
  ],

  providers: [
    MovieService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptorService,
      multi: true
    }
  ]
})
export class MovieModule { }
