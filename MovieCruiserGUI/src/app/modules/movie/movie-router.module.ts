import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ActivatedRoute } from '@angular/router';
import { ContainerComponent } from './components/container/container.component';
import { TmdbContainerComponent } from './components/tmdb-container/tmdb-container.component';
import { WatchlistComponent } from './components/watchlist/watchlist.component';
import { SearchMovieComponent } from './components/search-movie/search-movie.component';
import { AuthGuardService } from '../../auth-guard.service';

const movieRoutes: Routes = [
  {
    path: 'movies',
    children: [
      {
        path: '',
        redirectTo: '/movies/popular',
        pathMatch: 'full',
        canActivate: [AuthGuardService]
      },
      {
        path: 'popular',
        component: TmdbContainerComponent,
        canActivate: [AuthGuardService],
        data: {
          movieType: 'popular'
        },
      },
      {
        path: 'top-rated',
        canActivate: [AuthGuardService],
        component: TmdbContainerComponent,
        data: {
          movieType: 'top_rated'
        }
      },
      {
        path:'watchlist',
        canActivate: [AuthGuardService],
        component:WatchlistComponent,
      },
      {
        path:'search',
        canActivate: [AuthGuardService],
        component:SearchMovieComponent,
      }
    ]
  }
]

@NgModule({
  imports: [RouterModule.forRoot(movieRoutes)],
  exports: [RouterModule]
})
export class MovieRouterModule { }
